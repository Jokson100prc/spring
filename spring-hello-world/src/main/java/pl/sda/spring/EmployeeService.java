package pl.sda.spring;

import java.util.Optional;

public interface EmployeeService {
    /**
     * Adds a new employee to the database.
     *
     * @param employee
     * @return the added employee (the id might be changed)
     */
    Employee addEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    void updateEmployee(int oldEmployeeId, Employee newEmployee);

    Iterable<Employee> getAllEmployees();

    Optional<Employee> getEmployee(int employeeId);
}
