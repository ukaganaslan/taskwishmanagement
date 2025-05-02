public class Parent extends User {
    public Parent(String name) {
        super(name);
    }

    // Görev atama
    public void assignTask(Child child, Task task) {
        task.setAssignedBy("PARENT");
        child.receiveTask(task);
    }

    // Görevi kontrol etme ve puanı verme
    public void checkTask(Task task, int rate, Child child) {
        task.setRate(rate);
        double earned = task.getPoint() * (rate / 5.0);
        child.addPoints(earned);
    }

    // Ekstra puan verme
    public void addBudgetCoin(Child child, double amount) {
        child.addPoints(amount);
    }

    // Seviye yükseltme
    public void promoteLevel(Child child, int newLevel) {
        child.setLevel(newLevel);
    }

    // Dilek onaylama
    public void approveWish(Wish wish, int requiredLevel) {
        wish.setStatus("APPROVED");
        wish.setRequiredLevel(requiredLevel);
    }

    // Dilek reddetme
    public void rejectWish(Wish wish) {
        wish.setStatus("REJECTED");
    }

    @Override
    public String getRole() {
        return "Parent";
    }
}
