import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarView {

    // Takvim görünümünü gösterir
    public static void showCalendar(List<Task> tasks, List<Wish> wishes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\n Child's Schedule: (Showing time-sensitive tasks and wishes):");

        // Görevleri takvime ekle
        for (Task task : tasks) {
            if (task.getDeadline() != null) {
                System.out.println(" Task: " + task.getTaskName() +
                        " | Deadline: " + task.getDeadline().format(formatter));
            }
        }

        // Dilekleri takvime ekle
        for (Wish wish : wishes) {
            if (wish.getStartTime() != null && wish.getEndTime() != null) {
                System.out.println(" Wish: " + wish.getWishName() +
                        " | Start: " + wish.getStartTime().format(formatter) +
                        " | Finish: " + wish.getEndTime().format(formatter));
            }
        }
    }
}
