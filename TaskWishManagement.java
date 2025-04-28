import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskWishManagement() {
        tasks = new ArrayList<>();
        wishes = new ArrayList<>();
        child = new Child("C001");
        parent = new Parent("P001");
        teacher = new Teacher("T001");
        filereader = new Filereader();

        tasks = filereader.readTasks("Tasks.txt");
        wishes = filereader.readWishes("Wishes.txt");
    }

  
    private String[] parseCommand(String commandLine) {
        List<String> parts = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"[^\"]*\"|[^\\s\"]+").matcher(commandLine);
        while (matcher.find()) {
            String part = matcher.group();
            if (part.startsWith("\"") && part.endsWith("\"")) {
                part = part.substring(1, part.length() - 1); 
            }
            parts.add(part);
        }
        return parts.toArray(new String[0]);
    }

    public void executeCommands(String commandFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(commandFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = parseCommand(line); 
                if (parts.length == 0) continue;

                String command = parts[0];

                switch (command) {
                    case "ADD_TASK1":
                        try {
                            if (parts.length != 8) {
                                System.out.println("Error: Invalid number of arguments for ADD_TASK1. Expected 8, got " + parts.length);
                                break;
                            }
                            String assigner1 = parts[1];
                            String taskName1 = parts[3];
                            String dateTime1 = parts[5] + " " + parts[6];
                            LocalDateTime deadline1 = LocalDateTime.parse(dateTime1, formatter);
                            int points1 = Integer.parseInt(parts[7]);
                            Task task1 = new Task(taskName1, points1, deadline1, null, null);
                            tasks.add(task1);
                            if (assigner1.equals("T")) {
                                teacher.manageTaskData(task1);
                            } else if (assigner1.equals("F")) {
                                parent.manageTasks(task1);
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Error: Invalid date/time format in ADD_TASK1: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid points in ADD_TASK1: " + e.getMessage());
                        }
                        break;

                    case "ADD_TASK2":
                        try {
                            if (parts.length != 12) {
                                System.out.println("Error: Invalid number of arguments for ADD_TASK2. Expected 12, got " + parts.length);
                                break;
                            }
                            String assigner2 = parts[1];
                            String taskName2 = parts[3];
                            String dateTime2 = parts[5] + " " + parts[6];
                            LocalDateTime deadline2 = LocalDateTime.parse(dateTime2, formatter);
                            String startDateTime = parts[7] + " " + parts[8];
                            String endDateTime = parts[9] + " " + parts[10];
                            LocalDateTime startTime = LocalDateTime.parse(startDateTime, formatter);
                            LocalDateTime endTime = LocalDateTime.parse(endDateTime, formatter);
                            int points2 = Integer.parseInt(parts[11]);
                            Task task2 = new Task(taskName2, points2, deadline2, startTime, endTime);
                            tasks.add(task2);
                            if (assigner2.equals("T")) {
                                teacher.manageTaskData(task2);
                            } else if (assigner2.equals("F")) {
                                parent.manageTasks(task2);
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Error: Invalid date/time format in ADD_TASK2: " + e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid points in ADD_TASK2: " + e.getMessage());
                        }
                        break;

                    case "LIST_ALL_TASKS":
                        System.out.println("Listing all tasks:");
                        LocalDate today = LocalDate.now();
                        LocalDate startOfWeek = today.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
                        LocalDate endOfWeek = startOfWeek.plusDays(6);

                        for (Task t : tasks) {
                            LocalDate taskDate = t.getDeadline().toLocalDate();
                            boolean showTask = true;

                            if (parts.length > 1) {
                                String filter = parts[1];
                                if (filter.equals("D") && !taskDate.equals(today)) {
                                    showTask = false;
                                } else if (filter.equals("W") && (taskDate.isBefore(startOfWeek) || taskDate.isAfter(endOfWeek))) {
                                    showTask = false;
                                }
                            }

                            if (showTask) {
                                System.out.print(t.getTaskName() + " - " + t.getStatus() + " - Deadline: " + t.getDeadline().format(formatter));
                                if (t.getStartTime() != null && t.getEndTime() != null) {
                                    System.out.print(" - Activity Time: " + t.getStartTime().format(formatter) + " to " + t.getEndTime().format(formatter));
                                }
                                System.out.println();
                            }
                        }
                        break;

                    case "ADD_WISH1":
                        if (parts.length != 4) {
                            System.out.println("Error: Invalid number of arguments for ADD_WISH1. Expected 4, got " + parts.length);
                            break;
                        }
                        String wishName1 = parts[2];
                        Wish wish1 = new Wish(wishName1, null, null);
                        wishes.add(wish1);
                        child.addWish(wish1);
                        break;

                    case "ADD_WISH2":
                        try {
                            if (parts.length != 8) {
                                System.out.println("Error: Invalid number of arguments for ADD_WISH2. Expected 8, got " + parts.length);
                                break;
                            }
                            String wishName2 = parts[2];
                            String wishStartDateTime = parts[4] + " " + parts[5];
                            String wishEndDateTime = parts[6] + " " + parts[7];
                            LocalDateTime wishStartTime = LocalDateTime.parse(wishStartDateTime, formatter);
                            LocalDateTime wishEndTime = LocalDateTime.parse(wishEndDateTime, formatter);
                            Wish wish2 = new Wish(wishName2, wishStartTime, wishEndTime);
                            wishes.add(wish2);
                            child.addWish(wish2);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error: Invalid date/time format in ADD_WISH2: " + e.getMessage());
                        }
                        break;

                    case "LIST_ALL_WISHES":
                        System.out.println("Listing all wishes:");
                        for (Wish w : wishes) {
                            System.out.print(w.getWishName() + " - " + w.getStatus() + " - Required Level: " + w.getLevel());
                            if (w.getStartTime() != null && w.getEndTime() != null) {
                                System.out.print(" - Activity Time: " + w.getStartTime().format(formatter) + " to " + w.getEndTime().format(formatter));
                            }
                            System.out.println();
                        }
                        break;

                    case "TASK_DONE":
                        if (parts.length != 2) {
                            System.out.println("Error: Invalid number of arguments for TASK_DONE. Expected 2, got " + parts.length);
                            break;
                        }
                        String taskIdDone = parts[1];
                        boolean taskFound = false;
                        for (Task t : tasks) {
                            if (t.getTaskName().equals(taskIdDone)) {
                                child.completeTask(t);
                                taskFound = true;
                                break;
                            }
                        }
                        if (!taskFound) {
                            System.out.println("Error: Task not found: " + taskIdDone);
                        }
                        break;

                    case "TASK_CHECKED":
                        try {
                            if (parts.length != 3) {
                                System.out.println("Error: Invalid number of arguments for TASK_CHECKED. Expected 3, got " + parts.length);
                                break;
                            }
                            String taskIdChecked = parts[1];
                            int rating = Integer.parseInt(parts[2]);
                            if (rating < 1 || rating > 5) {
                                System.out.println("Error: Rating must be between 1 and 5.");
                                break;
                            }
                            boolean taskFoundForCheck = false;
                            for (Task t : tasks) {
                                if (t.getTaskName().equals(taskIdChecked)) {
                                    teacher.approveTask(t, rating);
                                    child.addPoints(t.getPoints());
                                    child.addRating(rating);
                                    taskFoundForCheck = true;
                                    break;
                                }
                            }
                            if (!taskFoundForCheck) {
                                System.out.println("Error: Task not found: " + taskIdChecked);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid rating in TASK_CHECKED: " + e.getMessage());
                        }
                        break;

                    case "WISH_CHECKED":
                        try {
                            if (parts.length != 4 && parts.length != 3) {
                                System.out.println("Error: Invalid number of arguments for WISH_CHECKED. Expected 3 or 4, got " + parts.length);
                                break;
                            }
                            String wishIdChecked = parts[1];
                            String status = parts[2];
                            boolean wishFound = false;
                            for (Wish w : wishes) {
                                if (w.getWishName().equals(wishIdChecked)) {
                                    if (status.equals("APPROVED")) {
                                        int requiredLevel = Integer.parseInt(parts[3]);
                                        parent.approveWish(w, requiredLevel);
                                    } else if (status.equals("REJECTED")) {
                                        w.setStatus("Rejected");
                                        wishes.remove(w);
                                    }
                                    wishFound = true;
                                    break;
                                }
                            }
                            if (!wishFound) {
                                System.out.println("Error: Wish not found: " + wishIdChecked);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid required level in WISH_CHECKED: " + e.getMessage());
                        }
                        break;

                    case "ADD_BUDGET_COIN":
                        try {
                            if (parts.length != 2) {
                                System.out.println("Error: Invalid number of arguments for ADD_BUDGET_COIN. Expected 2, got " + parts.length);
                                break;
                            }
                            int points = Integer.parseInt(parts[1]);
                            child.addPoints(points);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid points in ADD_BUDGET_COIN: " + e.getMessage());
                        }
                        break;

                    case "PRINT_BUDGET":
                        System.out.println("Child's total points: " + child.getTotalPoints());
                        break;

                    case "PRINT_STATUS":
                        System.out.println("Child's level: " + child.getLevel());
                        break;

                    default:
                        System.out.println("Error: Unknown command: " + command);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading commands: " + e.getMessage());
        }

    
        filereader.writeTasks("Tasks.txt", tasks);
        filereader.writeWishes("Wishes.txt", wishes);
    }

    public static void main(String[] args) {
        TaskWishManagement app = new TaskWishManagement();
        app.executeCommands("Commands.txt");
    }
}