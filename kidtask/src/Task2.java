import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Task2 extends Task {
    private LocalDateTime activityStart;
    private LocalDateTime activityEnd;

    public Task2(int id, String title, String description, LocalDate deadlineDate, LocalTime deadlineTime, int point,
            LocalDateTime activityStart, LocalDateTime activityEnd) {

        super(id, title, description, deadlineDate, deadlineTime, point);
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
    }

    public LocalDateTime getActivityStart() {
        return activityStart;
    }

    public LocalDateTime getActivityEnd() {
        return activityEnd;
    }

    @Override
    public String getType() {
        return "TASK2";
    }
}
