package com.campusflow.dao;

import com.campusflow.config.DatabaseConfig;
import com.campusflow.model.LeaveRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveDAO {

    public List<LeaveRequest> getAllLeaves() throws SQLException {
        List<LeaveRequest> leaves = new ArrayList<>();
        String sql = "SELECT * FROM leave_requests";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leaves.add(new LeaveRequest(
                    rs.getInt("request_id"),
                    rs.getInt("student_id"),
                    rs.getString("reason"),
                    rs.getString("status")
                ));
            }
        }
        return leaves;
    }

    public void addLeave(LeaveRequest leave) throws SQLException {
        String sql = "INSERT INTO leave_requests (student_id, reason, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, leave.getStudentId());
            pstmt.setString(2, leave.getReason());
            pstmt.setString(3, leave.getStatus());
            pstmt.executeUpdate();
        }
    }
}