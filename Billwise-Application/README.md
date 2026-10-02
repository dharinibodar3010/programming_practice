# BillWise Application

BillWise is an invoice management and business notification platform. This monolithic full-stack application allows an Admin to manage invoices, and it uses scheduled jobs to simulate sending reminders for upcoming, due today, and overdue invoices.

## Features
- **Admin Dashboard**: View total, due today, overdue, and paid invoice statistics.
- **Invoice Management**: List, change status, and mark invoices as PAID.
- **Scheduled Jobs**: Manage reminder jobs. Enable/disable, view execution history, and trigger jobs manually ("Run Now").
- **Strict Duplicate Prevention**: Uses PostgreSQL unique constraints and Spring transactions to prevent duplicate reminders even under concurrent "Run Now" execution.
- **Failed Reminder Retry**: The system elegantly ignores successful reminders but will retry sending failed reminders on subsequent runs.
- **Idempotency**: Guaranteed exactly-once successful execution per invoice reminder period.

## Technology Stack
- **Backend**: Java 17, Spring Boot 3.2, Spring Data JPA, Spring Security, Flyway.
- **Database**: PostgreSQL (Production) / H2 (Tests).
- **Frontend**: React 18, Vite, Bootstrap 5, Axios, React Router.

## Setup Instructions

### Prerequisites
- Java 17
- Node.js 18+ and npm
- PostgreSQL 15+

### PostgreSQL Setup
Create the database:
```sql
CREATE DATABASE billwise;
```

### Environment Variables
Copy `.env.example` to your system environment variables, or update `src/main/resources/application.properties` directly. The required variables are:
- `DB_HOST` (default: localhost)
- `DB_PORT` (default: 5432)
- `DB_NAME` (default: billwise)
- `DB_USERNAME` (default: postgres)
- `DB_PASSWORD` (set this to your password)

### Default Admin Credentials
The database is seeded with a default Admin account via Flyway `V2__seed_data.sql`:
- **Username**: admin
- **Password**: admin123

---

## How to Run

### Backend
Navigate to the root directory `Billwise-Application`:
```bash
# Run tests
mvn clean test

# Run the application
mvn spring-boot:run
```
The backend will run on `http://localhost:8080`.

### Frontend
Navigate to the `frontend` directory:
```bash
cd frontend

# Install dependencies
npm install

# Run the React development server
npm run dev
```
The frontend will run on `http://localhost:5173`.

---

## Scheduler & Duplicate Prevention Explanation

### The Business Logic
Invoices are checked by the `ReminderSchedulerService` via cron schedules. The system determines eligibility:
- **Upcoming**: Before the due date.
- **Due Today**: On the due date.
- **Overdue**: After the due date.
- **Paid**: Always skipped.

### Duplicate Prevention (Idempotency)
A critical requirement was idempotency. The system guarantees AT MOST ONE successful reminder attempt per invoice per reminder type and period.

**How it works**:
1. **Database Constraint**: A `UNIQUE` constraint exists on the `reminder_attempt` table covering `(invoice_id, reminder_type, reminder_period)`.
2. **Spring Propagation**: `ReminderProcessingService.processReminderSafely` runs in a `REQUIRES_NEW` transaction. 
3. **Exception Handling**: If two instances (or repeated "Run Now" clicks) try to process the same reminder at the exact same millisecond, the database throws a `DataIntegrityViolationException`. The code catches this gracefully, skipping the duplicate without crashing the job.
4. **Retry Logic**: If a reminder attempt fails (status `FAILED`), the unique row is *updated* on the next run instead of inserted, allowing for safe retries without breaking constraints.

---

## Testing Instructions
Run backend tests using:
```bash
mvn clean test
```
The suite includes tests for `ReminderEligibilityService` (checking dates and PAID statuses) and `ReminderProcessingServiceTest` (verifying mock behavior for concurrent constraint violations and successful retries).

## Demo Steps
1. Navigate to `http://localhost:5173`.
2. Login with `admin` / `admin123`.
3. View the **Dashboard** for overall statistics.
4. Go to **Invoices**. Observe the seeded test data (Acme Pvt Ltd & Global Corp).
5. Mark an invoice as `PAID`.
6. Go to **Scheduled Jobs**.
7. Click "Run Now" on a job (e.g., Upcoming or Overdue). 
8. Click "Run Now" multiple times very quickly. Observe that no duplicates are created.
9. Go to **Dashboard** and review the **Recent Job Executions** table at the bottom to see how many were processed, succeeded, or skipped.
