package com.campusflow.ui;

import com.campusflow.dao.LeaveDAO;
import com.campusflow.model.LeaveRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LeavePanel extends JPanel {
    private JTextField studentIdField, reasonField;
    private JTable table;
    private DefaultTableModel tableModel;
    private LeaveDAO leaveDAO = new LeaveDAO();

    public LeavePanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Submit Leave Request"));

        formPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        formPanel.add(studentIdField);

        formPanel.add(new JLabel("Reason:"));
        reasonField = new JTextField();
        formPanel.add(reasonField);

        JButton addButton = new JButton("Submit Request");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Request ID", "Student ID", "Reason", "Status"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(e -> addLeave());

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<LeaveRequest> leaves = leaveDAO.getAllLeaves();
            for (LeaveRequest l : leaves) {
                tableModel.addRow(new Object[]{l.getRequestId(), l.getStudentId(), l.getReason(), l.getStatus()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading leaves: " + ex.getMessage());
        }
    }

    private void addLeave() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            leaveDAO.addLeave(new LeaveRequest(studentId, reasonField.getText()));
            studentIdField.setText(""); reasonField.setText("");
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding leave: " + ex.getMessage());
        }
    }
}