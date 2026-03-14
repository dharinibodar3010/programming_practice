package model;

public class Branch {

    private int branchId;
    private String branchName;
    private String location;

    // No-argument constructor
    public Branch() {
    }

    // Parameterized constructor
    public Branch(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
    }

    // Getters and Setters
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
