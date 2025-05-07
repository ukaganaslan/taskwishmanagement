import java.time.LocalDateTime;

public class Task {
    private String taskName;
    private int points;
    private LocalDateTime deadline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public Task(String taskName, int points, LocalDateTime deadline, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.points = points;
        this.deadline = deadline;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "Pending";
    }

    public String getTaskName() {
        return taskName;
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

    public String getStatus() {  // BU METODU EKLEDİM
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
