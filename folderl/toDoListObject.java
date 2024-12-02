import java.time.LocalDate;

public class toDoListObject {
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    public toDoListObject(String description, LocalDate dueDate){
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }
    
    //getters
    public String getDescription(){
        return description;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    //mark task as completed
    public void markAsCompleted(){
        this.isCompleted = true;
    }
}
