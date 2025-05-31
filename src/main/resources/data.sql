-- Insert sample loan applications
INSERT INTO loan_application (applicant_name, applicant_id_number, amount, term_in_months, status, created_at, credit_score)
VALUES 
('Vishesh Kumar', 'ID001', 10000.00, 12, 'PENDING', NOW(), 700),
('Alice Tan', 'ID002', 25000.00, 24, 'APPROVED', NOW(), 750),
('John Lim', 'ID003', 5000.00, 6, 'REJECTED', NOW(), 620),
('Mary Chong', 'ID004', 15000.00, 18, 'PENDING', NOW(), 680);
