public class Teacher extends User {
    public Teacher(String userId) {
        super(userId);
    }

    public void manageTaskData(Task task) {
        System.out.println("Teacher " + userId + " is managing task: " + task.getTaskName());
    }
}
