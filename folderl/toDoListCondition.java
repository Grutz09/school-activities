import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class toDoListCondition {
    private ArrayList<Task> tasks;

    //constructor
    public toDoListCondition(){
        tasks = new ArrayList<>();
    }

    // add new tasks
    public void addTask(String description, LocalDate dueDate){
        tasks.add(new Task(description, dueDate));
    }

    //mark task completed 
    public void markTaskCompleted(int index){
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
            System.out.println("Task is marked as Completed");

            //missing the function to remove the finished task in JText Field
            // ??


        }else {
            System.out.println("Invalid task index."); 
        }
    }

    //remove tasks 
    public void removeTask(int index){
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task has been removed!");
        }else {
            System.out.println("Invalid Task Index.");
        }
    }

    // counts how many tasks there are
    public int getRowCount(){
        return tasks.size();
    };

    //get Tasks
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    // displays tasks due today, tomorrow and next week
    public void displayTaskByCategory(){
        String input =JOptionPane.showInputDialog("Enter the deadline (yyyy-MM-dd):");
        LocalDate deadline;

        try {
            deadline = (LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (DateTimeParseException e) {
            //handle invalid input
            JOptionPane.showMessageDialog(null,"Invalid date format. Please use the format yyyy-MM-dd.");
            return;
        }

        StringBuilder tasksUntilDeadline = new StringBuilder("Tasks Due Til Deadline: " + deadline + "\n");
        for(Task task : tasks){
            if (task.getDueDate().isEqual(deadline) && !task.isCompleted()) {
                tasksUntilDeadline.append("- ").append(task.getDescription()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, tasksUntilDeadline.toString() + "\n");
    }


}
