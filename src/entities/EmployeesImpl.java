package entities;

import Interfaces.Employee;
import Interfaces.Employees;
import daos.EmployeeDao;
import daos.EmployeeDaoImpl;
import dataStructures.CustomDataStructure;

public class EmployeesImpl implements Employees {
    EmployeeDao ed= new EmployeeDaoImpl();
    @Override
    public int addEmployee(Employee e) {
        return ed.insert(e);
    }

    @Override
    public void deleteEmployee(int id) {
        ed.delete(getEmployee(id));
    }

    @Override
    public Employee getEmployee(int id) {
        return ed.get(id);
    }

    @Override
    public void updateEmployee(Employee e, int id) {
        ed.update(e, id);
    }

    @Override
    public CustomDataStructure<Employee> getAll() {
        return ed.getAll();
    }
}
