package TaskandWishManagementApplicationforChildren;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
	private static int totalPoints = 0;
	private static int level = 1;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void executeCommands(String commandFilePath, List<Task> tasks, List<Wish> wishes) {
        try (BufferedReader reader = new BufferedReader(new FileReader(commandFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                System.out.println("➡ Command: " + line);

               
                List<String> parts = parseCommandLine(line);

                String command = parts.get(0);

                switch (command) {
                    case "ADD_TASK1":
                        if (parts.size() < 8) {
                            System.out.println("False command format: " + line);
                            continue;
                        }
                        String id1 = parts.get(1);
                        String taskId1 = parts.get(2);
                        String title1 = parts.get(3);
                        String desc1 = parts.get(4);
                        String date1 = parts.get(5);
                        String time1 = parts.get(6);
                        int points1 = Integer.parseInt(parts.get(7));

                        LocalDateTime deadline = LocalDateTime.parse(date1 + " " + time1, formatter);
                        Task task1 = new Task(taskId1, title1, desc1, deadline, 1);
                        task1.completeTask(points1); 
                        tasks.add(task1);
                        break;
                        
                    case "ADD_TASK2":
                        try {
                            String id2 = parts.get(2);
                            String title2 = parts.get(3);
                            String desc2 = parts.get(4);
                            String startDate = parts.get(5);
                            String startTime = parts.get(6);
                            String endDate = parts.get(7);
                            String endTime = parts.get(8);
                            int points2 = Integer.parseInt(parts.get(9));

                            LocalDateTime start = LocalDateTime.parse(startDate + " " + startTime, formatter);
                            LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, formatter);

                            Task task2 = new Task(id2, title2, desc2, end, 2); 
                            task2.completeTask(points2); 
                            tasks.add(task2);
                        } catch (Exception e) {
                            System.out.println("ADD_TASK2 command's error: " + e.getMessage());
                        }
                        break;


                    case "ADD_WISH1":
                        if (parts.size() < 4) {
                            System.out.println("False WISH format: " + line);
                            continue;
                        }
                        String wid = parts.get(1);
                        String wtitle = parts.get(2);
                        String wdesc = parts.get(3);
                        Wish wish = new Wish(wid, wtitle, wdesc);
                        wishes.add(wish);
                        break;
                        
                    case "LIST_ALL_TASKS":
                        System.out.println("ALL TASKS:");
                        for (Task task : tasks) {
                            String status = task.isCompleted() ? "Completed" : "Not Completed";
                            System.out.println("- " + task.getTitle() + " | Status: " + status + " | Last Date: " + task.getDeadlineDate());
                        }
                        break;
                        
                        
                    case "TASK_DONE":
                        String doneTaskId = parts.get(1);
                        for (Task task : tasks) {
                            if (task.getId().equals(doneTaskId)) {
                                task.completeTask(0);
                                totalPoints += task.getPoints();
                                level = totalPoints / 100 + 1;

                                System.out.println("✅ Task mark as completed: " + task.getTitle());
                                break;
                            }
                        }
                        break;
                        
                    case "TASK_CHECKED":
                        String checkedTaskId = parts.get(1);
                        int rating = Integer.parseInt(parts.get(2));
                        for (Task task : tasks) {
                            if (task.getId().equals(checkedTaskId) && task.isCompleted()) {
                                task.approveTask(rating);
                                totalPoints += task.getPoints();
                                level = totalPoints / 100 + 1;

                                System.out.println("🟢 Task is approved and " + rating + " given points " + task.getTitle());
                                break;
                            }
                        }
                        break;
                        
                        
                    case "WISH_CHECKED":
                        String wishId = parts.get(1);
                        String status = parts.get(2); 

                        Wish foundWish = null;
                        for (Wish w : wishes) {
                            if (w.getId().equals(wishId)) {
                                foundWish = w;
                                break;
                            }
                        }

                        if (foundWish != null && status.equals("APPROVED")) {
                            
                            int totalPoints = 0;
                            for (Task task : tasks) {
                                if (task.isCompleted() && task.getPoints() > 0) {
                                    totalPoints += task.getPoints();
                                }
                            }

                            if (totalPoints >= foundWish.getRequiredPoints()) {
                                System.out.println("🎁 Available from Level 3 " + foundWish.getTitle());
                                
                            } else {
                                System.out.println("❌ insufficient score, wish not fulfilled.");
                            }
                        }
                        break;
                        
                    case "PRINT_BUDGET":
                    	int totalPoints = CommandProcessor.getTotalPoints(tasks);
                    	System.out.println("💰 Total Points Earned: " + totalPoints);
                        break;
                        
                    case "PRINT_STATUS":
                        
                        System.out.println("\n📊 TASK STATUS:");
                        for (Task task : tasks) {
                            String taskStatus = task.isCompleted() ? "Completed" : "Not Completed";
                            String taskApproval = task.isCompleted() && task.getPoints() > 0 ? "Approved" : "Waiting";
                            System.out.println("- " + task.getTitle() + " | Status: " + taskStatus + " | Approval: " + taskApproval); 
                        }

                        totalPoints = CommandProcessor.getTotalPoints(tasks);
                        level = totalPoints / 100 + 1;

                        
                        System.out.println("\n🎁 WISH STATUS:"); 
                        for (Wish w : wishes) { 
                            String wishStatus = totalPoints >= w.getRequiredPoints() ? "Can take ✅" : "Can not take ❌";
                            System.out.println("- " + w.getTitle() + " | Required Score: " + w.getRequiredPoints() + " → " + wishStatus);
                        }

                       
                        System.out.println("\n CHİLD Points: " + totalPoints + " | Level: " + level);
                        break;




                    default:
                        System.out.println("⚠ unknown command: " + command);
                }
            }

        } catch (IOException e) {
            System.out.println("❌ command file  it could not be read: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ it can be any error: " + e.getMessage());
        }
    }

    
    private static List<String> parseCommandLine(String line) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1)); 
            } else {
                tokens.add(matcher.group(2));
            }
        }
        return tokens;
    }


    public static int getTotalPoints(List<Task> tasks) {
        int totalPoints = 0;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                totalPoints += task.getPoints();
            }
        }
        return totalPoints;
    }

}
