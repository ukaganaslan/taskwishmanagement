import java.util.List;

public class Wish1 {
    private String[] parts;
    private Child child;

    public Wish1(String[] parts, Child child) {
        this.parts = parts;
        this.child = child;
    }

    public void process(List<Wish> wishes) {
        if (parts.length != 4) {
            System.out.println("Error: Invalid number of arguments for ADD_WISH1. Expected 4, got " + parts.length);
            return;
        }
        String wishName = parts[2];
        Wish wish = new Wish(wishName, null, null);
        wishes.add(wish);
        child.addWish(wish);
    }
}
