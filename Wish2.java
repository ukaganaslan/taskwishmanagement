package TaskandWishManagementApplicationforChildren;

import java.time.LocalDateTime;

public class Wish2 extends Wish {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Wish2(String id, String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(id, title, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toString() { 
        return super.toString() +
                " | Aktivite Başlangıç: " + startDateTime +
                " | Aktivite Bitiş: " + endDateTime;
    }
}
