package TaskandWishManagementApplicationforChildren;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileOperations {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static List<Task> readTasksFromFile(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", -1); 
                if (parts.length >= 8) {
                    
                    String id = parts[2];
                    String title = parts[3].replace("\"", "");  
                    String description = parts[4].replace("\"", ""); 
                    String date = parts[5];
                    String time = parts[6];
                    int points = Integer.parseInt(parts[7]);

                   
                    if (parts[0].equals("ADD_TASK2") && parts.length >= 12) {
                        String startDate = parts[8];
                        String startTime = parts[9];
                        String endDate = parts[10];
                        String endTime = parts[11];

                       
                        LocalDateTime start = LocalDateTime.parse(startDate + " " + startTime, dateTimeFormatter);
                        LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, dateTimeFormatter);

                        
                        Task task = new Task(id, title, description, start, 1); 
                        
                        tasks.add(task);
                    } else {
                       
                        LocalDateTime deadline = LocalDateTime.parse(date + " " + time, dateTimeFormatter);
                        Task task = new Task(id, title, description, deadline, 1);
                        task.completeTask(points); 
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(": " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }
        return tasks;
    }

    public static List<Wish> readWishesFromFile(String filePath) {
        List<Wish> wishes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", -1);
                if (parts.length >= 4) {
                    String id = parts[1];
                    String title = parts[2].replace("\"", "");
                    String description = parts[3].replace("\"", "");
                    Wish wish = new Wish(id, title, description);
                    wishes.add(wish);
                }
            }
        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
        }
        return wishes;
    }

    
    public static void writeTasksToFile(String filePath, List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
}
