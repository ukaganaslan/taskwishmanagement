import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Filereader {
    // Komutları dosyadan okur ve liste olarak döndürür
    public List<String> readCommands(String filename) {
        List<String> commands = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    commands.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading commands: " + e.getMessage());
        }
        return commands;
    }

    // Görevleri dosyaya yazar
    public void writeTasks(String filename, List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                bw.write(task.getTaskName() + "," + task.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks to " + filename + ": " + e.getMessage());
        }
    }

    // Dilekleri dosyaya yazar
    public void writeWishes(String filename, List<Wish> wishes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Wish wish : wishes) {
                bw.write(wish.getWishName() + "," + wish.getStatus() + "," + wish.getLevel());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing wishes to " + filename + ": " + e.getMessage());
        }
    }
}
