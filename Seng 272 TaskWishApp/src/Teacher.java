public class Teacher extends User {
    public Teacher(String userId) {
        super(userId);
    }

    public void manageTaskData(Task task) {
        System.out.println("Teacher " + userId + " is managing task: " + task.getTaskName());
    }

    public void approveTask(Task task, int rating) {
        task.setRating(rating);
        task.setStatus("Approved");
        System.out.println("Task " + task.getTaskName() + " approved by Teacher with rating: " + rating);
    }
}