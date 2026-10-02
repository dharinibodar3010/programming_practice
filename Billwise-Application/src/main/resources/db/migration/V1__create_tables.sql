CREATE TABLE admin (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(50),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE invoice (
    id BIGSERIAL PRIMARY KEY,
    invoice_number VARCHAR(100) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    description TEXT,
    reference VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_invoice_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE scheduled_job (
    id BIGSERIAL PRIMARY KEY,
    job_name VARCHAR(255) NOT NULL UNIQUE,
    cron_expression VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    last_run_at TIMESTAMP,
    next_run_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE job_execution (
    id BIGSERIAL PRIMARY KEY,
    scheduled_job_id BIGINT NOT NULL,
    started_at TIMESTAMP NOT NULL,
    completed_at TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    found_count INT DEFAULT 0,
    processed_count INT DEFAULT 0,
    success_count INT DEFAULT 0,
    failed_count INT DEFAULT 0,
    skipped_count INT DEFAULT 0,
    error_message TEXT,
    CONSTRAINT fk_execution_job FOREIGN KEY (scheduled_job_id) REFERENCES scheduled_job(id)
);

CREATE TABLE reminder_attempt (
    id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    reminder_type VARCHAR(50) NOT NULL,
    reminder_period DATE NOT NULL,
    attempted_at TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    failure_reason TEXT,
    notification_message TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_reminder_invoice FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);

-- Crucial unique constraint for duplicate prevention:
-- An invoice cannot have multiple successful or pending reminder attempts for the same type and period.
-- To allow retries of FAILED reminders, we might use a partial unique index in Postgres.
-- However, H2 compatibility might be an issue. A standard unique constraint on (invoice_id, reminder_type, reminder_period)
-- means we can only insert one row for that period, period. If it fails, we UPDATE the row instead of inserting a new one.
-- Updating the same attempt row is cleaner and fully idempotent in any database.
ALTER TABLE reminder_attempt ADD CONSTRAINT uk_reminder_attempt_duplicate 
    UNIQUE (invoice_id, reminder_type, reminder_period);
