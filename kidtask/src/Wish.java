public abstract class Wish {
    protected String wishId;
    protected String title;
    protected String description;
    protected String status = "PENDING"; // varsayılan başlangıç durumu
    protected int requiredLevel = 1; // default: herkes erişebilir

    public Wish(String wishId, String title, String description) {
        this.wishId = wishId;
        this.title = title;
        this.description = description;
    }

    public String getWishId() {
        return wishId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("PENDING") || status.equals("APPROVED") || status.equals("REJECTED")) {
            this.status = status;
        }
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int level) {
        if (level >= 1) {
            this.requiredLevel = level;
        }
    }

    public abstract String getType(); // WISH1 or WISH2
}
