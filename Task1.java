import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Task1 {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String[] parts;
    private Teacher teacher;
    private Parent parent;

    public Task1(String[] parts, Teacher teacher, Parent parent) {
        this.parts = parts;
        this.teacher = teacher;
        this.parent = parent;
    }

    public void process(List<Task> tasks) {
        try {
            if (parts.length != 8) {
                System.out.println("Error: Invalid number of arguments for ADD_TASK1. Expected 8, got " + parts.length);
                return;
            }
            String assigner = parts[1];
            String taskName = parts[3];
            String dateTime = parts[5] + " " + parts[6];
            LocalDateTime deadline = LocalDateTime.parse(dateTime, formatter);
            int points = Integer.parseInt(parts[7]);
            Task task = new Task(taskName, points, deadline, null, null);
            tasks.add(task);
            if (assigner.equals("T")) {
                teacher.manageTaskData(task);
            } else if (assigner.equals("F")) {
                parent.manageTasks(task);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Error: Invalid format in ADD_TASK1: " + e.getMessage());
        }
    }
}
