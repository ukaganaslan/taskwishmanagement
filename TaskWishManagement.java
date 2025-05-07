import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskWishManagement {
    private List<Task> tasks;
    private List<Wish> wishes;
    private Child child;
    private Parent parent;
    private Teacher teacher;
    private Filereader filereader;

    public TaskWishManagement() {
        tasks = new ArrayList<>();
        wishes = new ArrayList<>();
        child = new Child("C001");
        parent = new Parent("P001");
        teacher = new Teacher("T001");
        filereader = new Filereader();
    }

    public void executeCommands(String commandFile) {
        List<String> commands = filereader.readCommands(commandFile);
        for (String command : commands) {
            String[] parts = parseCommand(command);
            if (parts.length == 0) continue;

            switch (parts[0]) {
                case "ADD_TASK1":
                    new Task1(parts, teacher, parent).process(tasks);
                    break;
                case "ADD_TASK2":
                    new Task2(parts, teacher, parent).process(tasks);
                    break;
                case "LIST_ALL_TASKS":
                    listAllTasks();
                    break;
                case "ADD_WISH1":
                    new Wish1(parts, child).process(wishes);
                    break;
                case "ADD_WISH2":
                    new Wish2(parts, child).process(wishes);
                    break;
                case "LIST_ALL_WISHES":
                    listAllWishes();
                    break;
                case "TASK_DONE":
                    completeTask(parts);
                    break;
                case "TASK_CHECKED":
                    checkTask(parts);
                    break;
                case "WISH_CHECKED":
                    checkWish(parts);
                    break;
                case "ADD_BUDGET_COIN":
                    addBudgetCoin(parts);
                    break;
                case "PRINT_BUDGET":
                    System.out.println("Child's total points: " + child.getTotalPoints());
                    break;
                case "PRINT_STATUS":
                    System.out.println("Child's level: " + child.getLevel());
                    break;
                case "SHOW_CALENDAR":
                    showCalendar();
                    break;
                default:
                    System.out.println("Unknown command: " + parts[0]);
                    break;
            }
        }
    }

    // Çocuğa puan (coin) ekler
    private void addBudgetCoin(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Error: Missing amount for ADD_BUDGET_COIN.");
            return;
        }
        try {
            int points = Integer.parseInt(parts[1]);
            child.addPoints(points);
            System.out.println(" Added " + points + " points to child.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid point format for ADD_BUDGET_COIN.");
        }
    }

    // Takvim görünümünü gösterir
    private void showCalendar() {
        CalendarView.showCalendar(tasks, wishes);
    }

    // Görevleri listeler
    private void listAllTasks() {
        System.out.println("Listing all tasks:");
        for (Task task : tasks) {
            System.out.println(task.getTaskName() + " - " + task.getStatus());
        }
    }

    // Dilekleri listeler
    private void listAllWishes() {
        System.out.println("Listing all wishes:");
        for (Wish wish : wishes) {
            System.out.println(wish.getWishName() + " - " + wish.getStatus() + " - Level: " + wish.getLevel());
        }
    }

    // Görevi tamamlar
    private void completeTask(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Error: Missing task name for TASK_DONE.");
            return;
        }

        String taskName = parts[1];
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                child.completeTask(task);
                System.out.println(" Task completed: " + taskName);
                return;
            }
        }
        System.out.println("Error: Task not found: " + taskName);
    }

    // Görevi kontrol eder
    private void checkTask(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Error: Missing task name or rating for TASK_CHECKED.");
            return;
        }

        String taskName = parts[1];
        try {
            int rating = Integer.parseInt(parts[2]);
            for (Task task : tasks) {
                if (task.getTaskName().equals(taskName)) {
                    teacher.manageTaskData(task);
                    child.addPoints(task.getPoints());
                    child.addRating(rating);
                    System.out.println(" Task checked: " + taskName + " - Rating: " + rating);
                    return;
                }
            }
            System.out.println("Error: Task not found: " + taskName);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid rating format for TASK_CHECKED.");
        }
    }

    // Dileği kontrol eder
    private void checkWish(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Error: Missing wish name or status for WISH_CHECKED.");
            return;
        }

        String wishName = parts[1];
        String status = parts[2];
        for (Wish wish : wishes) {
            if (wish.getWishName().equals(wishName)) {
                if (status.equals("APPROVED") && parts.length > 3) {
                    int requiredLevel = Integer.parseInt(parts[3]);
                    parent.approveWish(wish, requiredLevel);
                    System.out.println(" Wish approved: " + wishName);
                } else if (status.equals("REJECTED")) {
                    wishes.remove(wish);
                    System.out.println(" Wish rejected: " + wishName);
                }
                return;
            }
        }
        System.out.println("Error: Wish not found: " + wishName);
    }

    // Komutları çift tırnak içindeki metinleri birleştirerek ayırır
    private String[] parseCommand(String commandLine) {
        List<String> parts = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"[^\"]*\"|\\S+").matcher(commandLine);
        while (matcher.find()) {
            String part = matcher.group();
            if (part.startsWith("\"") && part.endsWith("\"")) {
                parts.add(part.substring(1, part.length() - 1)); // Çift tırnakları kaldır
            } else {
                parts.add(part);
            }
        }
        return parts.toArray(new String[0]);
    }

    public static void main(String[] args) {
        TaskWishManagement app = new TaskWishManagement();
        app.executeCommands("commands.txt");
    }
}
