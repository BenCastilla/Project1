package Interfaces;

public interface Employees {

    public void addEmployee(Employee e);

    public void deleteEmployee(int id);

    public Employee getEmployee(int id);

    public void updateEmployee(Employee e, int id);
}
