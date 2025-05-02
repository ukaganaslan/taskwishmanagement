import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Filereader {
    public List<Task> readTasks(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Task task = Task.fromString(line);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from " + filename + ": " + e.getMessage());
        }
        return tasks;
    }

    public List<Wish> readWishes(String filename) {
        List<Wish> wishes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Wish wish = Wish.fromString(line);
                    wishes.add(wish);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading wishes from " + filename + ": " + e.getMessage());
        }
        return wishes;
    }

    public void writeTasks(String filename, List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                bw.write(task.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks to " + filename + ": " + e.getMessage());
        }
    }

    public void writeWishes(String filename, List<Wish> wishes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Wish wish : wishes) {
                bw.write(wish.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing wishes to " + filename + ": " + e.getMessage());
        }
    }
}