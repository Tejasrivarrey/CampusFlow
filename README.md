# CampusFlow – University Management System

CampusFlow is a desktop-based University Management System developed using **Java Swing** and **MySQL**. It simplifies campus administrative tasks by providing modules for student management, examination scheduling, and leave request management. The application follows the **DAO (Data Access Object)** design pattern and connects to a cloud-hosted **Aiven MySQL** database for secure and reliable data storage.

---

## 📌 Features

### 🎓 Student Management
- Register new students.
- View student details.
- Store student contact and department information.
- Perform CRUD (Create, Read, Update, Delete) operations.

### 📝 Examination Management
- Schedule examinations.
- Manage subjects and exam dates.
- Assign examination halls.
- Update and delete exam records.

### 📄 Leave Management
- Submit student leave requests.
- Track leave reasons and duration.
- Approve or reject leave requests.
- Update leave status dynamically.

### ☁️ Cloud Database Integration
- Connects securely to **Aiven Cloud MySQL**.
- Uses SSL encryption for database communication.
- Stores data persistently in the cloud.

---

## 🛠 Tech Stack

| Technology | Description |
|------------|-------------|
| Java | Programming Language (JDK 17+) |
| Java Swing | GUI Framework |
| MySQL | Relational Database |
| Aiven Cloud | Cloud Database Hosting |
| JDBC | Database Connectivity |
| DAO Pattern | Software Design Pattern |
| MySQL Connector/J | JDBC Driver |

---

## 📂 Project Structure

```text
CampusFlow/
│
├── src/
│   └── com/
│       └── campusflow/
│           ├── config/
│           │   └── DatabaseConfig.java
│           │
│           ├── dao/
│           │   ├── StudentDAO.java
│           │   ├── ExamDAO.java
│           │   └── LeaveDAO.java
│           │
│           ├── model/
│           │   ├── Student.java
│           │   ├── Exam.java
│           │   └── LeaveRequest.java
│           │
│           ├── ui/
│           │   ├── MainFrame.java
│           │   ├── StudentPanel.java
│           │   ├── ExamPanel.java
│           │   └── LeavePanel.java
│           │
│           └── Main.java
│
├── lib/
│   └── mysql-connector-j-26.7.0.jar
│
├── schema.sql
├── manifest.txt
└── README.md
```

---

## ⚙️ Prerequisites

Before running the project, make sure you have:

- Java Development Kit (JDK 17 or later)
- Visual Studio Code / IntelliJ IDEA / Eclipse
- MySQL Workbench (optional)
- Git

---

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Naga-durga79/CampusFlow.git
```

Move into the project folder:

```bash
cd CampusFlow
```

---

### 2. Database Setup

Create a MySQL database and execute the SQL script.

```sql
CREATE DATABASE campusflow;

USE campusflow;

-- Execute schema.sql
```

Or import the provided `schema.sql` file into your MySQL/Aiven database.

---

### 3. Configure Database Connection

Open:

```
src/com/campusflow/config/DatabaseConfig.java
```

Update the credentials:

```java
private static final String URL =
"jdbc:mysql://<HOST>:<PORT>/campusflow?sslMode=REQUIRED";

private static final String USER = "YOUR_USERNAME";

private static final String PASSWORD = "YOUR_PASSWORD";
```

---

### 4. Add MySQL Connector

Ensure the following driver exists inside the **lib** folder.

```
mysql-connector-j-26.7.0.jar
```

Add it to your project's build path.

---

### 5. Run the Application

Open the project in your IDE.

Run:

```
src/com/campusflow/Main.java
```

The CampusFlow desktop application will launch.

---

## 💾 Database Tables

The application uses the following tables:

- students
- exams
- leave_requests

---

## 🏗 Design Pattern

CampusFlow follows the **DAO (Data Access Object)** pattern.

Benefits include:

- Separation of business logic and database logic
- Better code maintainability
- Easy database management
- Improved scalability

---

## 🔒 Security

- Secure MySQL connection using SSL.
- Input validation for all forms.
- Prepared Statements used to prevent SQL Injection.
- Cloud-hosted database for reliable storage.

---

## 📸 Application Modules

- Student Registration
- Student Records
- Exam Scheduling
- Leave Request Management
- Dashboard (Main Window)

---

## 📖 Future Enhancements

- Faculty Management
- Attendance System
- Result Management
- User Authentication (Admin Login)
- PDF Report Generation
- Email Notifications
- Student Search & Filters

---

## 👨‍💻 Author

**Nalla Naga Durga**

- GitHub: https://github.com/Naga-durga79

---

## 📜 License

This project is developed for educational and learning purposes.
Feel free to use and modify it for academic projects.

---

⭐ If you found this project useful, don't forget to star the repository!
