public class Wish2 extends Wish {
    // activityStart ve activityEnd alanları kaldırıldı

    public Wish2(String wishId, String title, String description, String status, int requiredLevel) {
        super(wishId, title, description);
        this.status = status;
        this.requiredLevel = requiredLevel;
    }

    @Override
    public String getType() {
        return "WISH2";
    }
}
