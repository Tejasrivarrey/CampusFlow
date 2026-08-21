USE defaultdb;

DROP TABLE IF EXISTS leave_requests;
DROP TABLE IF EXISTS exams;
DROP TABLE IF EXISTS students;

CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS exams (
    exam_id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(100) NOT NULL,
    exam_date DATE NOT NULL,
    room VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS leave_requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    reason TEXT NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

INSERT INTO students (student_id, name, email, department) VALUES 
(1, 'Alice Smith', 'alice@univ.edu', 'Computer Science'),
(2, 'Bob Jones', 'bob@univ.edu', 'Electrical Eng');

INSERT INTO exams (subject, exam_date, room) VALUES 
('Data Structures', '2026-03-15', 'Hall A'),
('Circuit Analysis', '2026-03-18', 'Lab 2');

INSERT INTO leave_requests (student_id, reason, status) VALUES 
(1, 'Medical Emergency', 'PENDING'),
(2, 'Family Function', 'APPROVED');