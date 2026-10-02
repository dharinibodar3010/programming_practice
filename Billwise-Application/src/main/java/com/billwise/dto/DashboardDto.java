package com.billwise.dto;

public class DashboardDto {
    private long totalInvoices;
    private long dueToday;
    private long dueTomorrow;
    private long overdue;
    private long paid;

    // Getters and Setters
    public long getTotalInvoices() { return totalInvoices; }
    public void setTotalInvoices(long totalInvoices) { this.totalInvoices = totalInvoices; }
    public long getDueToday() { return dueToday; }
    public void setDueToday(long dueToday) { this.dueToday = dueToday; }
    public long getDueTomorrow() { return dueTomorrow; }
    public void setDueTomorrow(long dueTomorrow) { this.dueTomorrow = dueTomorrow; }
    public long getOverdue() { return overdue; }
    public void setOverdue(long overdue) { this.overdue = overdue; }
    public long getPaid() { return paid; }
    public void setPaid(long paid) { this.paid = paid; }
}
