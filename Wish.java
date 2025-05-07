import java.time.LocalDateTime;

public class Wish {
    private String wishName;
    private String status;
    private int level;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Wish(String wishName, LocalDateTime startTime, LocalDateTime endTime) {
        this.wishName = wishName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "Pending";
        this.level = 0;
    }

    public String getWishName() {
        return wishName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // ✅ Başlangıç ve bitiş zamanları için getter metodları eklendi
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
