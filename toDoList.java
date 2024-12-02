import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class toDoList extends JFrame {
    private toDoListCondition actionPlan;
    private JTable taskDisplay;
    private DefaultTableModel tableModel;
    private JTextField descriptionField;
    private JTextField dueDateField;

    public toDoList() {
        actionPlan = new toDoListCondition();

        // sestup frame
        setTitle("Action Planner");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(10, 50, 5));
        setVisible(true);

        // create table
        tableModel = new DefaultTableModel(new Object[] { "Task Description", "Deadline" }, 0);
        taskDisplay = new JTable(tableModel);
        taskDisplay.setFillsViewportHeight(true);
        taskDisplay.setBackground(Color.lightGray);

        // set table appearance
        JScrollPane scrollPane = new JScrollPane(taskDisplay);
        scrollPane.setBackground(new Color(30, 60, 50));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.setBackground(Color.ORANGE);
        inputPanel.add(new JLabel("Task Description"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Deadline (yyyy-mm-dd: )"));
        dueDateField = new JTextField();
        inputPanel.add(dueDateField);

        JLabel titleJLabel = new JLabel("Action Planner", JLabel.CENTER);
        titleJLabel.setForeground(Color.white);
        add(titleJLabel, BorderLayout.NORTH);

        // buttons
        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton completeButton = new JButton("Complete Task");
        JButton displayButton = new JButton("Display All Tasks");

        addButton.addActionListener(new AddTaskListener());
        removeButton.addActionListener(new RemoveTaskListener());
        completeButton.addActionListener(new CompleteTaskListener());
        displayButton.addActionListener(new DisplayTaskListener());

        // adding the buttons to JOptionPane
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(completeButton);
        inputPanel.add(displayButton);

        // adding components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    // inner class for adding tasks
    private class AddTaskListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            String description = descriptionField.getText();
            String dateStr = dueDateField.getText();

            try {
                LocalDate dueDate = LocalDate.parse(dateStr);
                actionPlan.addTask(description, dueDate);

                // add to the table
                tableModel.addRow(new Object[] { description, dueDate });
                descriptionField.setText("");
                dueDateField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format. Please use the required format.");
            }
        }
    }

    private class RemoveTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String indexStr = JOptionPane.showInputDialog("Enter task number to remove: ");
            try {
                int index = Integer.parseInt(indexStr);

                if (index < 0 || index >= tableModel.getRowCount()) {
                    JOptionPane.showMessageDialog(null, "Invalid task number. Please enter the correct index.");
                    return;
                }

                actionPlan.removeTask(index);
                tableModel.removeRow(index);
                JOptionPane.showMessageDialog(null, "Task " + index + " removed.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid task number.");
            }
        }
    }

    // inner class for marking tasks complete then removing
    // the existing tasks after marking
    private class CompleteTaskListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {
            String indexStr = JOptionPane.showInputDialog("Enter task number: "); // input is marked as completed

            try {
                int index = Integer.parseInt(indexStr);

                if (index < 0 || index >= tableModel.getRowCount()) {
                    JOptionPane.showMessageDialog(null, "Invalid task number. Please enter the correct index.");
                    return;
                }

                actionPlan.markTaskCompleted(index);

                tableModel.removeRow(index);
                JOptionPane.showMessageDialog(null, "Task " + index + " completed.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please Follow the required format.");
            }
        }
    }

    private class DisplayTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // clear table before displaying
            tableModel.setRowCount(0);

            if (actionPlan.getTasks().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Currently no task.");
            } else {
                // add all task to table
                for (Task task : actionPlan.getTasks()) {
                    tableModel.addRow(new Object[] { task.getDescription(), task.getDueDate() });
                    JOptionPane.showMessageDialog(null, "Task displayed successfully.");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            toDoList gui = new toDoList();
            gui.setVisible(true);
        });
    }

}
