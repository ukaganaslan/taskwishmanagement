import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Task2 {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String[] parts;
    private Teacher teacher;
    private Parent parent;

    public Task2(String[] parts, Teacher teacher, Parent parent) {
        this.parts = parts;
        this.teacher = teacher;
        this.parent = parent;
    }

    public void process(List<Task> tasks) {
        try {
            if (parts.length != 12) {
                System.out.println("Error: Invalid number of arguments for ADD_TASK2. Expected 12, got " + parts.length);
                return;
            }
            String assigner = parts[1];
            String taskName = parts[3];
            String dateTime = parts[5] + " " + parts[6];
            LocalDateTime deadline = LocalDateTime.parse(dateTime, formatter);
            LocalDateTime startTime = LocalDateTime.parse(parts[7] + " " + parts[8], formatter);
            LocalDateTime endTime = LocalDateTime.parse(parts[9] + " " + parts[10], formatter);
            int points = Integer.parseInt(parts[11]);
            Task task = new Task(taskName, points, deadline, startTime, endTime);
            tasks.add(task);
            if (assigner.equals("T")) {
                teacher.manageTaskData(task);
            } else if (assigner.equals("F")) {
                parent.manageTasks(task);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Error: Invalid format in ADD_TASK2: " + e.getMessage());
        }
    }
}
