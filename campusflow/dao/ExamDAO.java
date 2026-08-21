package com.campusflow.dao;

import com.campusflow.config.DatabaseConfig;
import com.campusflow.model.Exam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {

    public List<Exam> getAllExams() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                exams.add(new Exam(
                    rs.getInt("exam_id"),
                    rs.getString("subject"),
                    rs.getString("exam_date"),
                    rs.getString("room")
                ));
            }
        }
        return exams;
    }

    public void addExam(Exam exam) throws SQLException {
        String sql = "INSERT INTO exams (subject, exam_date, room) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, exam.getSubject());
            pstmt.setString(2, exam.getDate());
            pstmt.setString(3, exam.getRoom());
            pstmt.executeUpdate();
        }
    }
}