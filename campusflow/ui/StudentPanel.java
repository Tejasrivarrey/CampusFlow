package com.campusflow.ui;

import com.campusflow.dao.StudentDAO;
import com.campusflow.model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {
    private JTextField nameField, emailField, deptField;
    private JTable table;
    private DefaultTableModel tableModel;
    private StudentDAO studentDAO = new StudentDAO();

    public StudentPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(248, 250, 252));

        // --- FORM PANEL ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);

        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(labelFont);
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(nameField, gbc);

        // Row 1: Email
        gbc.gridx = 2; gbc.weightx = 0;
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 3; gbc.weightx = 1.0;
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(emailField, gbc);

        // Row 2: Department
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(labelFont);
        formPanel.add(deptLabel, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        deptField = new JTextField();
        deptField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(deptField, gbc);

        // Row 2: Action Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);

        JButton addButton = createStyledButton("Add Student", new Color(37, 99, 235), Color.WHITE);
        JButton deleteButton = createStyledButton("Delete Selected", new Color(220, 38, 38), Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 2; gbc.gridy = 1; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.NORTH);

        // --- TABLE PANEL ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Student Name", "Email Address", "Department"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(Color.BLACK);
        table.setShowGrid(true);
        table.setGridColor(new Color(241, 245, 249));

        // Header Styling
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(241, 245, 249));
        header.setForeground(new Color(30, 41, 59));
        header.setPreferredSize(new Dimension(0, 35));

        // Center Align Column 0 (ID)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        add(scrollPane, BorderLayout.CENTER);

        // Button Listeners
        addButton.addActionListener(e -> addStudent());
        deleteButton.addActionListener(e -> deleteStudent());

        loadData();
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 32));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<Student> students = studentDAO.getAllStudents();
            for (Student s : students) {
                tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getDepartment()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent() {
        if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || deptField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            studentDAO.addStudent(new Student(nameField.getText().trim(), emailField.getText().trim(), deptField.getText().trim()));
            nameField.setText(""); emailField.setText(""); deptField.setText("");
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student record to delete.", "Selection Required", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selected, 0);
        try {
            studentDAO.deleteStudent(id);
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}