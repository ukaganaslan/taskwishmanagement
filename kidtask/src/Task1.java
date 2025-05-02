import java.time.LocalDate;
import java.time.LocalTime;

public class Task1 extends Task {

    public Task1(int id, String title, String description, LocalDate deadlineDate, LocalTime deadlineTime, int point) {
        super(id, title, description, deadlineDate, deadlineTime, point);
    }

    @Override
    public String getType() {
        return "TASK1";
    }
}
