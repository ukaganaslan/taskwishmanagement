import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Wish {
    private String wishName;
    private String status;
    private int level;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(wishName).append(",")
          .append(status).append(",")
          .append(level);
        if (startTime != null && endTime != null) {
            sb.append(",").append(startTime.format(formatter))
              .append(",").append(endTime.format(formatter));
        }
        return sb.toString();
    }

    public static Wish fromString(String line) {
        String[] parts = line.split(",");
        String wishName = parts[0];
        String status = parts[1];
        int level = Integer.parseInt(parts[2]);
        LocalDateTime startTime = null, endTime = null;
        if (parts.length > 3) {
            startTime = LocalDateTime.parse(parts[3], formatter);
            endTime = LocalDateTime.parse(parts[4], formatter);
        }
        Wish wish = new Wish(wishName, startTime, endTime);
        wish.setStatus(status);
        wish.setLevel(level);
        return wish;
    }
}