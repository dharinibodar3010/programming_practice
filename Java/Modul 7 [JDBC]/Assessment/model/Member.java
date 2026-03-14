package model;

import java.time.LocalDate;

//GymManagerApp part - 2

public class Member {
    private int id;
    private String name;
    private int age;
    private String contact;
    private String planType;
    private LocalDate registrationDate;
    private int branchId;

    public Member() {}

    public Member(String name, int age, String contact, String planType, LocalDate registrationDate) 
    {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.planType = planType;
        this.registrationDate = registrationDate;
        //this.branchId = branchId;
    }

    // Getters and Setters
    public int getId() 
    { 
    	return id; 
    }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public void setBranchId(int branchId) { this.branchId = branchId; }
    public int getBranchId() {return branchId;}
    
}

