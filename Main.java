package TaskandWishManagementApplicationforChildren;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       
        List<Task> tasks = FileOperations.readTasksFromFile("C:\\Users\\LENOVO\\Desktop\\272\\Task.txt");
        List<Wish> wishes = FileOperations.readWishesFromFile("C:\\Users\\LENOVO\\Desktop\\272\\Wishes.txt");

        
        CommandProcessor.executeCommands("C:\\Users\\LENOVO\\Desktop\\272\\Commands.txt", tasks, wishes);

        
        int totalPoints = CommandProcessor.getTotalPoints(tasks);

       
        System.out.println("\n🔹 LOADED TASK");
        for (Task task : tasks) {
            String status = task.isCompleted() ? "Completed" : "Not Completed";
            String approval = task.isCompleted() && task.getPoints() > 0 ? "Approved" : "Waiting";
            System.out.println("- " + task.getTitle() + " | Status: " + status + " | Approval: " + approval);

            if (task.isCompleted() && task.getPoints() > 0) {
                System.out.println("   + Points earned from this task: " + task.getPoints());
            }
        }

        System.out.println("\n⭐ Total child's point: " + totalPoints);

        
        System.out.println("\n🔹 LOADED WİSHES:");
        for (Wish wish : wishes) {
            String status = totalPoints >= wish.getRequiredPoints() ? "CAN TAKE ✅" : "Insufficient points ❌";
            System.out.println("- " + wish.getTitle() + " | Required point: " + wish.getRequiredPoints() + " → " + status);
        } 
    }
}
