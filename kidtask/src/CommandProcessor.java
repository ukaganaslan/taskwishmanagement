import java.io.*;
import java.time.*;

public class CommandProcessor {
    private Child child;
    private Parent parent;
    private Teacher teacher;

    public CommandProcessor(Child child, Parent parent, Teacher teacher) {
        this.child = child;
        this.parent = parent;
        this.teacher = teacher;
    }

    public void processCommands(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] parts = splitCommand(line);
                String command = parts[0];

                switch (command) {
                    case "ADD_TASK1":
                        handleAddTask1(parts);
                        break;

                    case "ADD_TASK2":
                        handleAddTask2(parts);
                        break;

                    case "TASK_DONE":
                        handleTaskDone(parts);
                        break;

                    case "TASK_CHECKED":
                        handleTaskChecked(parts);
                        break;

                    case "ADD_WISH1":
                        handleAddWish1(parts);
                        break;

                    case "ADD_WISH2":
                        handleAddWish2(parts);
                        break;

                    case "WISH_CHECKED":
                        handleWishChecked(parts);
                        break;

                    case "ADD_BUDGET_COIN":
                        handleAddBudget(parts);
                        break;

                    case "PRINT_BUDGET":
                        System.out.println("Current points: " + child.getPoints());
                        break;

                    case "PRINT_STATUS":
                        System.out.println("Current level: " + child.getLevel());
                        break;

                    case "LIST_ALL_TASKS":
                        listAllTasks(parts);
                        break;

                    case "LIST_ALL_WISHES":
                        listAllWishes();
                        break;

                    case "LIST_CALENDAR_TASKS":
                        listCalendarTasks();
                        break;

                    default:
                        System.out.println("Unknown command: " + command);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading commands file: " + e.getMessage());
        }
    }

    private String[] splitCommand(String line) {
        return line.trim().split("\\s+");
    }

    private void handleAddTask1(String[] parts) {
        try {
            String userType = parts[1];
            int taskId = Integer.parseInt(parts[2]);
            String title = parts[3].replace("_", " ");
            String description = parts[4].replace("_", " ");
            LocalDate deadlineDate = LocalDate.parse(parts[5]);
            LocalTime deadlineTime = LocalTime.parse(parts[6]);
            int point = Integer.parseInt(parts[7]);

            Task1 task = new Task1(taskId, title, description, deadlineDate, deadlineTime, point);

            if (userType.equals("T")) {
                teacher.assignTask(child, task);
            } else {
                parent.assignTask(child, task);
            }

        } catch (Exception e) {
            System.out.println("Error processing ADD_TASK1: " + e.getMessage());
        }
    }

    private void handleAddTask2(String[] parts) {
        try {
            String userType = parts[1];
            int taskId = Integer.parseInt(parts[2]);
            String title = parts[3].replace("_", " ");
            String description = parts[4].replace("_", " ");
            LocalDate deadlineDate = LocalDate.parse(parts[5]);
            LocalTime deadlineTime = LocalTime.parse(parts[6]);

            LocalDateTime startTime = LocalDateTime.parse(parts[7] + "T" + parts[8]);
            LocalDateTime endTime = LocalDateTime.parse(parts[9] + "T" + parts[10]);

            int point = Integer.parseInt(parts[11]);

            Task2 task = new Task2(taskId, title, description, deadlineDate, deadlineTime, point, startTime, endTime);

            if (userType.equals("T")) {
                teacher.assignTask(child, task);
            } else {
                parent.assignTask(child, task);
            }

        } catch (Exception e) {
            System.out.println("Error processing ADD_TASK2: " + e.getMessage());
        }
    }

    private void handleTaskDone(String[] parts) {
        try {
            int taskId = Integer.parseInt(parts[1]);
            child.completeTask(taskId);
        } catch (Exception e) {
            System.out.println("Error processing TASK_DONE: " + e.getMessage());
        }
    }

    private void handleTaskChecked(String[] parts) {
        try {
            int taskId = Integer.parseInt(parts[1]);
            int rate = Integer.parseInt(parts[2]);

            for (Task task : child.getTasks()) {
                if (task.getId() == taskId && task.isCompletedByChild()) {
                    // KİM ATAMIŞSA, SADECE O PUAN VEREBİLİR
                    if (task.getAssignedBy().equals("PARENT") && parent != null) {
                        task.setChecked(true);
                        task.setRate(rate);
                        double earnedPoints = task.getPoint() * (rate / 5.0);
                        child.addPoints(earnedPoints);
                    } else if (task.getAssignedBy().equals("TEACHER") && teacher != null) {
                        task.setChecked(true);
                        task.setRate(rate);
                        double earnedPoints = task.getPoint() * (rate / 5.0);
                        child.addPoints(earnedPoints);
                    } else {
                        System.out.println("You are not authorized to check this task.");
                    }
                    return;
                }
            }

            System.out.println("Task not found or not completed yet.");

        } catch (Exception e) {
            System.out.println("Error processing TASK_CHECKED: " + e.getMessage());
        }
    }

    private void handleAddWish1(String[] parts) {
        try {
            String wishId = parts[1];
            String title = parts[2].replace("_", " ");
            String description = parts[3].replace("_", " ");

            Wish1 wish = new Wish1(wishId, title, description);
            child.addWish(wish);

        } catch (Exception e) {
            System.out.println("Error processing ADD_WISH1: " + e.getMessage());
        }
    }

    private void handleAddWish2(String[] parts) {
        try {
            String wishId = parts[1];
            String title = parts[2].replace("_", " ");
            String description = parts[3].replace("_", " ");
            String status = parts[4]; // APPROVED or REJECTED
            int requiredLevel = Integer.parseInt(parts[5]); // Gereken seviye

            // Güncellenmiş Wish2 constructor
            Wish2 wish = new Wish2(wishId, title, description, status, requiredLevel);
            child.addWish(wish);

        } catch (Exception e) {
            System.out.println("Error processing ADD_WISH2: " + e.getMessage());
        }
    }

    private void handleWishChecked(String[] parts) {
        try {
            String wishId = parts[1];
            String status = parts[2];

            for (Wish wish : child.getWishes()) {
                if (wish.getWishId().equals(wishId)) {
                    if (status.equals("APPROVED")) {
                        int requiredLevel = Integer.parseInt(parts[3]);
                        parent.approveWish(wish, requiredLevel);
                    } else if (status.equals("REJECTED")) {
                        parent.rejectWish(wish);
                    }
                    return;
                }
            }

            System.out.println("Wish not found: " + wishId);

        } catch (Exception e) {
            System.out.println("Error processing WISH_CHECKED: " + e.getMessage());
        }
    }

    private void handleAddBudget(String[] parts) {
        try {
            double amount = Double.parseDouble(parts[1]);
            parent.addBudgetCoin(child, amount);
        } catch (Exception e) {
            System.out.println("Error processing ADD_BUDGET_COIN: " + e.getMessage());
        }
    }

    private void listAllTasks(String[] parts) {
        String filter = parts.length > 1 ? parts[1] : "";

        System.out.println("All Tasks:");

        for (Task task : child.getTasks()) {
            if (filter.equals("D")) {
                if (task.deadlineDate.equals(LocalDate.now())) {
                    printTask(task);
                }
            } else if (filter.equals("W")) {
                LocalDate now = LocalDate.now();
                LocalDate in7days = now.plusDays(7);
                if (!task.deadlineDate.isBefore(now) && !task.deadlineDate.isAfter(in7days)) {
                    printTask(task);
                }
            } else {
                printTask(task);
            }
        }
    }

    private void printTask(Task task) {
        System.out.println("- [" + task.getType() + "] " + task.getTitle()
                + " | Deadline: " + task.deadlineDate + " " + task.deadlineTime
                + " | Completed: " + task.isCompletedByChild()
                + " | Checked: " + task.isChecked()
                + " | Rate: " + task.getRate());
    }

    private void listAllWishes() {
        System.out.println("All Wishes:");
        for (Wish wish : child.getWishes()) {
            System.out.println("- [" + wish.getType() + "] " + wish.getTitle()
                    + " | Status: " + wish.getStatus()
                    + " | Required Level: " + wish.getRequiredLevel());
        }
    }

    private void listCalendarTasks() {
        System.out.println("Calendar Tasks:");
        for (Task task : child.getCalendarTasks()) {
            System.out.println("- " + task.getTitle() + " | " + ((Task2) task).getActivityStart() + " to "
                    + ((Task2) task).getActivityEnd());
        }
    }

}
