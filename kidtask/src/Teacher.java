public class Teacher extends User {
    public Teacher(String name) {
        super(name);
    }

    // Görev atama
    public void assignTask(Child child, Task task) {
        task.setAssignedBy("TEACHER");
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

    @Override
    public String getRole() {
        return "Teacher";
    }
}
