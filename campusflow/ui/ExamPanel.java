package com.campusflow.ui;

import com.campusflow.dao.ExamDAO;
import com.campusflow.model.Exam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExamPanel extends JPanel {
    private JTextField subjectField, dateField, roomField;
    private JTable table;
    private DefaultTableModel tableModel;
    private ExamDAO examDAO = new ExamDAO();

    public ExamPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Schedule Exam"));

        formPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        formPanel.add(subjectField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Room:"));
        roomField = new JTextField();
        formPanel.add(roomField);

        JButton addButton = new JButton("Schedule Exam");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Subject", "Date", "Room"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(e -> addExam());

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<Exam> exams = examDAO.getAllExams();
            for (Exam ex : exams) {
                tableModel.addRow(new Object[]{ex.getId(), ex.getSubject(), ex.getDate(), ex.getRoom()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading exams: " + ex.getMessage());
        }
    }

    private void addExam() {
        try {
            examDAO.addExam(new Exam(subjectField.getText(), dateField.getText(), roomField.getText()));
            subjectField.setText(""); dateField.setText(""); roomField.setText("");
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error scheduling exam: " + ex.getMessage());
        }
    }
}