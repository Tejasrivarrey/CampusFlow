package com.campusflow.model;

public class LeaveRequest {
    private int requestId;
    private int studentId;
    private String reason;
    private String status;

    public LeaveRequest(int requestId, int studentId, String reason, String status) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.reason = reason;
        this.status = status;
    }

    public LeaveRequest(int studentId, String reason) {
        this.studentId = studentId;
        this.reason = reason;
        this.status = "Pending";
    }

    public int getRequestId() { return requestId; }
    public int getStudentId() { return studentId; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
}