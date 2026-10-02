-- Seed Admin
-- Password 'admin123' using noop encoder
INSERT INTO admin (username, password, created_at, updated_at) 
VALUES ('admin', '{noop}admin123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Seed Jobs
INSERT INTO scheduled_job (job_name, cron_expression, enabled, created_at, updated_at)
VALUES 
('Upcoming Invoice Reminder Job', '0 0 8 * * *', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Due Today Invoice Reminder Job', '0 15 8 * * *', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Overdue Invoice Reminder Job', '0 30 8 * * *', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Seed Customer
INSERT INTO customer (id, name, email, phone, created_at, updated_at)
VALUES 
(1, 'Acme Pvt Ltd', 'contact@acme.com', '123-456-7890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Global Corp', 'billing@global.com', '098-765-4321', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Reset sequence for customer so new inserts don't fail
ALTER SEQUENCE customer_id_seq RESTART WITH 3;

-- Seed Invoices for September 6, 2026 scenario testing
INSERT INTO invoice (invoice_number, customer_id, invoice_date, due_date, amount, status, description, created_at, updated_at)
VALUES
('INV-1001', 1, '2026-09-01', '2026-09-07', 15000.00, 'PENDING', 'Software License', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-1002', 1, '2026-09-01', '2026-09-06', 5000.00, 'PENDING', 'Consulting', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-1003', 2, '2026-08-20', '2026-09-05', 8000.00, 'OVERDUE', 'Hardware Support', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-1004', 2, '2026-09-01', '2026-09-15', 12000.00, 'NEW', 'Monthly Retainer', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-1005', 1, '2026-08-15', '2026-09-02', 2000.00, 'OVERDUE', 'Ad-hoc Fixes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

