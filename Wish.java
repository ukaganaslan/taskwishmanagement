package TaskandWishManagementApplicationforChildren;
import java.time.LocalDateTime;

public class Wish {
    private String id;
    private String title;
    private String description;
    private int requiredPoints;
    private LocalDateTime startDateTime; 
    private LocalDateTime endDateTime;

    public Wish(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredPoints = 8; 
    }
    public void setActivityTime(LocalDateTime start, LocalDateTime end) {
        this.startDateTime = start;
        this.endDateTime = end;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }
    public LocalDateTime getStartDateTime() { return startDateTime; }
    public LocalDateTime getEndDateTime() { return endDateTime; }
    
    public void setRequiredLevel(int level) {
        this.requiredPoints = level;
    }

}
