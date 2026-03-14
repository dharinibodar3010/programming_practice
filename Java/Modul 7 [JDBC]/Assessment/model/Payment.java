package model;
import java.time.LocalDate;

public class Payment {
    private int id;
    private int memberId;
    private LocalDate paymentDate;
    private double membershipFee;
    private double trainerFee;
    private double totalAmount;

    public Payment() {}

    public Payment(int memberId, LocalDate paymentDate, double membershipFee, double trainerFee) {
        this.memberId = memberId;
        this.paymentDate = paymentDate;
        this.membershipFee = membershipFee;
        this.trainerFee = trainerFee;
        this.totalAmount = membershipFee + trainerFee;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public double getMembershipFee() { return membershipFee; }
    public void setMembershipFee(double membershipFee) { this.membershipFee = membershipFee; }
    public double getTrainerFee() { return trainerFee; }
    public void setTrainerFee(double trainerFee) { this.trainerFee = trainerFee; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
