import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String taskName;
    private int rating;
    private String status;
    private float takenPoints;
    private int points;
    private LocalDateTime deadline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String taskName, int points, LocalDateTime deadline, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.points = points;
        this.deadline = deadline;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "Pending";
        this.rating = 0;
        this.takenPoints = 0;
    }

    public void completeTask() {
        this.status = "Completed";
    }

    public String getTaskName() {
        return taskName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTakenPoints() {
        return takenPoints;
    }

    public void setTakenPoints(float takenPoints) {
        this.takenPoints = takenPoints;
    }

    public int getPoints() {
        return points;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(taskName).append(",")
          .append(points).append(",")
          .append(status).append(",")
          .append(rating).append(",")
          .append(deadline.format(formatter));
        if (startTime != null && endTime != null) {
            sb.append(",").append(startTime.format(formatter))
              .append(",").append(endTime.format(formatter));
        }
        return sb.toString();
    }

    public static Task fromString(String line) {
        String[] parts = line.split(",");
        String taskName = parts[0];
        int points = Integer.parseInt(parts[1]);
        String status = parts[2];
        int rating = Integer.parseInt(parts[3]);
        LocalDateTime deadline = LocalDateTime.parse(parts[4], formatter);
        LocalDateTime startTime = null, endTime = null;
        if (parts.length > 5) {
            startTime = LocalDateTime.parse(parts[5], formatter);
            endTime = LocalDateTime.parse(parts[6], formatter);
        }
        Task task = new Task(taskName, points, deadline, startTime, endTime);
        task.setStatus(status);
        task.setRating(rating);
        return task;
    }
}