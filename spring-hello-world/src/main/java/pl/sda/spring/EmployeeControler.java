package pl.sda.spring;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeControler {

    private EmployeeService employeeService;
    // private EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PatchMapping("/{id}")
    public void updateEmployee(@PathVariable Integer id, @RequestBody Employee employeePatch) {
        employeeService.updateEmployee(id, employeePatch);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Integer id) {
        return employeeService.getEmployee(id);
    }
}
