package entities;

public class DbTicket {
    private int ticketId;
    private boolean approved;
    private String date;
    private String description;
    private int amount;
    private int employeeID;


    //empty constructor
    public DbTicket() {};

    //generated constructor
    public DbTicket(int ticketId, boolean approved, String date, String description, int amount, int employeeID) {
        this.ticketId = ticketId;
        this.approved = approved;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.employeeID = employeeID;
    }

    //generated constructor without id;
    public DbTicket(boolean approved, String date, String description, int amount, int employeeID) {

        this.approved = approved;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.employeeID = employeeID;
    }

    //generated getters and setters


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    //generated toString

    @Override
    public String toString() {
        return "DbTicket{" +
                "ticketId=" + ticketId +
                ", approved=" + approved +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", employeeID=" + employeeID +
                '}';
    }
}
