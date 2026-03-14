package model;

public class WorkoutPlan {
    private int id;
    private int memberId;
    private String cardio;
    private String strength;
    private String flexibility;

    public WorkoutPlan() {}

    public WorkoutPlan(int memberId, String cardio, String strength, String flexibility) {
        this.memberId = memberId;
        this.cardio = cardio;
        this.strength = strength;
        this.flexibility = flexibility;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public String getCardio() { return cardio; }
    public void setCardio(String cardio) { this.cardio = cardio; }
    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
    public String getFlexibility() { return flexibility; }
    public void setFlexibility(String flexibility) { this.flexibility = flexibility; }
}
