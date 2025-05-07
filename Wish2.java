import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Wish2 {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String[] parts;
    private Child child;

    public Wish2(String[] parts, Child child) {
        this.parts = parts;
        this.child = child;
    }

    public void process(List<Wish> wishes) {
        try {
            if (parts.length != 8) {
                System.out.println("Error: Invalid number of arguments for ADD_WISH2. Expected 8, got " + parts.length);
                return;
            }
            String wishName = parts[2];
            LocalDateTime startTime = LocalDateTime.parse(parts[4] + " " + parts[5], formatter);
            LocalDateTime endTime = LocalDateTime.parse(parts[6] + " " + parts[7], formatter);
            Wish wish = new Wish(wishName, startTime, endTime);
            wishes.add(wish);
            child.addWish(wish);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date/time format in ADD_WISH2: " + e.getMessage());
        }
    }
}
