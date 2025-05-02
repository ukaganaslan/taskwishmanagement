import java.util.*;
import java.util.stream.Collectors;

public class Child extends User {
    private int level = 1;
    private double points = 0;
    private List<Task> tasks = new ArrayList<>();
    private List<Wish> wishes = new ArrayList<>();

    public Child(String name) {
        super(name);
    }

    // Görev alma
    public void receiveTask(Task task) {
        tasks.add(task);
    }

    // Görev tamamlama
    public void completeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setCompletedByChild(true);
                break;
            }
        }
    }

    // Takvim görünümü (sadece TASK2)
    public List<Task> getCalendarTasks() {
        return tasks.stream()
                .filter(task -> task instanceof Task2)
                .collect(Collectors.toList());
    }

    // Görevleri listeleme
    public List<Task> getTasks() {
        return tasks;
    }

    // Puan işlemleri
    public void addPoints(double p) {
        points += p;
    }

    public double getPoints() {
        return points;
    }

    // Seviye işlemleri
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        if (points < 40)
            return 1;
        else if (points < 60)
            return 2;
        else if (points < 80)
            return 3;
        else
            return 4;
    }

    // Dilek işlemleri
    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    @Override
    public String getRole() {
        return "Child";
    }
}
