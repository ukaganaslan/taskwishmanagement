package TaskandWishManagementApplicationforChildren;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarView {

    public static void showCalendar(List<Task> tasks, List<Wish> wishes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\n📅 child schedule: (only have the time) :");

        for (Task task : tasks) {
            if (task.getDeadlineDate() != null && task.getDeadlineDate().toLocalTime() != null) {
                System.out.println("📝 task: " + task.getTitle() + " | Tarih: " + task.getDeadlineDate().format(formatter));
            }
        }

        for (Wish wish : wishes) {
            if (wish instanceof Wish2) {
                Wish2 w2 = (Wish2) wish;
                System.out.println("🎈 Aktivite: " + w2.getTitle() +
                        " | start: " + w2.getStartDateTime().format(formatter) +
                        " | finish " + w2.getEndDateTime().format(formatter));
            }
        }
    }
}
