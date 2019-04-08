package pl.sda.spring;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeRepositoryService implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeRepositoryService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateEmployee(int oldEmployeeId, Employee newEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(oldEmployeeId);
        optionalEmployee.ifPresent(employee -> {
            String namePatch = newEmployee.getName();
            if (namePatch != null) {
                employee.setName(namePatch);
            }
            Collection<Address> addressesPatch = newEmployee.getAddresses();
            if (!addressesPatch.isEmpty()) {
                employee.setAddresses(addressesPatch);
            }
            employeeRepository.save(employee);
        });
    }

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployee(int employeeId) {
        return employeeRepository.findById(employeeId);
    }
}
