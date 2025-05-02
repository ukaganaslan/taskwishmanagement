import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Task {
    protected int id;
    protected String title;
    protected String description;
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;
    protected int point;
    protected String assignedBy; // "PARENT" veya "TEACHER"

    protected boolean isCompletedByChild = false;
    protected boolean isChecked = false;
    protected int rate = 0; // 1–5 arasında

    public Task(int id, String title, String description, LocalDate deadlineDate, LocalTime deadlineTime, int point) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getPoint() {
        return point;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setCompletedByChild(boolean completed) {
        this.isCompletedByChild = completed;
    }

    public boolean isCompletedByChild() {
        return isCompletedByChild;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setRate(int rate) {
        if (rate >= 1 && rate <= 5) {
            this.rate = rate;
        }
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public abstract String getType(); // TASK1 / TASK2
}
