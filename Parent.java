public class Parent extends User {
    public Parent(String userId) {
        super(userId);
    }

    public void manageTasks(Task task) {
        System.out.println("Parent " + userId + " is managing task: " + task.getTaskName());
    }

    public void approveWish(Wish wish, int requiredLevel) {
        wish.setStatus("Approved");
        wish.setLevel(requiredLevel);
        System.out.println("Wish " + wish.getWishName() + " approved by Parent, required level: " + requiredLevel);
    }
}
