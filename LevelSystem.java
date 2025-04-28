package TaskandWishManagementApplicationforChildren;

public class LevelSystem {
    public static int calculateLevel(int averagePoints) {
        if (averagePoints <= 40) return 1;
        else if (averagePoints <= 60) return 2;
        else if (averagePoints <= 80) return 3;
        else return 4;
    }
}