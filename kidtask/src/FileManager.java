import java.io.*;
import java.time.*;
import java.util.*;

public class FileManager {

    // =====================
    // GÖREVLERİ YAZ
    // =====================
    public void saveTasks(List<Task> tasks, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                if (task instanceof Task1) {
                    writer.write(String.format("TASK1 %d %s %s %s %s %d %b %b %d %s\n",
                            task.getId(),
                            task.getTitle().replace(" ", "_"),
                            task.getDescription().replace(" ", "_"),
                            task.deadlineDate,
                            task.deadlineTime,
                            task.getPoint(),
                            task.isCompletedByChild(),
                            task.isChecked(),
                            task.getRate(),
                            task.getAssignedBy())); // assignedBy alanı eklendi

                } else if (task instanceof Task2) {
                    Task2 t2 = (Task2) task;
                    writer.write(String.format("TASK2 %d %s %s %s %s %s %s %d %b %b %d %s\n",
                            t2.getId(),
                            t2.getTitle().replace(" ", "_"),
                            t2.getDescription().replace(" ", "_"),
                            t2.deadlineDate,
                            t2.deadlineTime,
                            t2.getActivityStart(),
                            t2.getActivityEnd(),
                            t2.getPoint(),
                            t2.isCompletedByChild(),
                            t2.isChecked(),
                            t2.getRate(),
                            t2.getAssignedBy())); // assignedBy alanı eklendi
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }

    // =====================
    // GÖREVLERİ OKU
    // =====================
    public void loadTasks(String filename, Child child) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String type = parts[0];

                if (type.equals("TASK1")) {
                    int id = Integer.parseInt(parts[1]);
                    String title = parts[2].replace("\"", "").replace("_", " ");
                    String description = parts[3].replace("\"", "").replace("_", " ");
                    LocalDate date = LocalDate.parse(parts[4]);
                    LocalTime time = LocalTime.parse(parts[5]);
                    int point = Integer.parseInt(parts[6]);
                    boolean completed = Boolean.parseBoolean(parts[7]);
                    boolean checked = Boolean.parseBoolean(parts[8]);
                    int rate = Integer.parseInt(parts[9]);
                    String assignedBy = parts[10];

                    Task1 task1 = new Task1(id, title, description, date, time, point);
                    task1.setCompletedByChild(completed);
                    task1.setChecked(checked);
                    task1.setRate(rate);
                    task1.setAssignedBy(assignedBy); // assignedBy alanı eklendi
                    child.receiveTask(task1);

                } else if (type.equals("TASK2")) {
                    int id = Integer.parseInt(parts[1]);
                    String title = parts[2].replace("\"", "").replace("_", " ");
                    String description = parts[3].replace("\"", "").replace("_", " ");
                    LocalDate date = LocalDate.parse(parts[4]);
                    LocalTime time = LocalTime.parse(parts[5]);
                    LocalDateTime start = LocalDateTime.parse(parts[6]);
                    LocalDateTime end = LocalDateTime.parse(parts[7]);
                    int point = Integer.parseInt(parts[8]);
                    boolean completed = Boolean.parseBoolean(parts[9]);
                    boolean checked = Boolean.parseBoolean(parts[10]);
                    int rate = Integer.parseInt(parts[11]);
                    String assignedBy = parts[12];

                    Task2 task2 = new Task2(id, title, description, date, time, point, start, end);
                    task2.setCompletedByChild(completed);
                    task2.setChecked(checked);
                    task2.setRate(rate);
                    task2.setAssignedBy(assignedBy); // assignedBy alanı eklendi
                    child.receiveTask(task2);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
    }

    // =====================
    // DİLEKLERİ YAZ
    // =====================
    public void saveWishes(List<Wish> wishes, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Wish wish : wishes) {
                if (wish instanceof Wish1) {
                    writer.write(String.format("WISH1 %s %s %s %s %d\n",
                            wish.getWishId(),
                            wish.getTitle().replace(" ", "_"),
                            wish.getDescription().replace(" ", "_"),
                            wish.getStatus(),
                            wish.getRequiredLevel()));
                } else if (wish instanceof Wish2) {
                    Wish2 w2 = (Wish2) wish;
                    writer.write(String.format("WISH2 %s %s %s %s %s %s %d\n",
                            w2.getWishId(),
                            w2.getTitle().replace(" ", "_"),
                            w2.getDescription().replace(" ", "_"),
                            w2.getStatus(),
                            w2.getRequiredLevel()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing wishes: " + e.getMessage());
        }
    }

    // =====================
    // DİLEKLERİ OKU
    // =====================
    public void loadWishes(String filename, Child child) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String type = parts[0];

                if (type.equals("WISH1")) {
                    String id = parts[1];
                    String title = parts[2].replace("\"", "").replace("_", " ");
                    String desc = parts[3].replace("\"", "").replace("_", " ");
                    String status = parts[4];
                    int level = Integer.parseInt(parts[5]);

                    Wish1 w1 = new Wish1(id, title, desc);
                    w1.setStatus(status);
                    w1.setRequiredLevel(level);
                    child.addWish(w1);

                } else if (type.equals("WISH2")) {
                    String id = parts[1];
                    String title = parts[2].replace("\"", "").replace("_", " ");
                    String desc = parts[3].replace("\"", "").replace("_", " ");
                    String status = parts[4];
                    int level = Integer.parseInt(parts[5]);

                    Wish2 w2 = new Wish2(id, title, desc, status, level);
                    child.addWish(w2);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading wishes: " + e.getMessage());
        }
    }
}
