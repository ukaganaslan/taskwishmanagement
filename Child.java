import java.util.ArrayList;
import java.util.List;

public class Child extends User {
    private float totalPoints;
    private int level;
    private List<Integer> ratings;

    public Child(String userId) {
        super(userId);
        this.totalPoints = 0;
        this.level = 1;
        this.ratings = new ArrayList<>();
    }

    public void completeTask(Task task) {
        task.setStatus("Completed");
        System.out.println("Child " + userId + " marked task " + task.getTaskName() + " as completed.");
    }

    public void addWish(Wish wish) {
        wish.setStatus("Pending");
        System.out.println("Child " + userId + " added wish: " + wish.getWishName());
    }

    public void addPoints(float points) {
        this.totalPoints += points;
        System.out.println("Child " + userId + " now has " + totalPoints + " points.");
    }

    public void addRating(int rating) {
        this.ratings.add(rating);
        updateLevel();
    }

    private void updateLevel() {
        if (ratings.isEmpty()) {
            this.level = 1;
            return;
        }
        double averageRating = ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        if (averageRating >= 4.0) {
            this.level = 4;
        } else if (averageRating >= 3.0) {
            this.level = 3;
        } else if (averageRating >= 2.0) {
            this.level = 2;
        } else {
            this.level = 1;
        }
    }

    public int getLevel() {
        return level;
    }

    public float getTotalPoints() {
        return totalPoints;
    }
}