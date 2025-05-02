public class Wish1 extends Wish {

    public Wish1(String wishId, String title, String description) {
        super(wishId, title, description);
    }

    @Override
    public String getType() {
        return "WISH1";
    }
}
