package com.example.RESTfulAPI;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/without-db-employees")
public class WithoutdbEmployeeController {

    private List<Employee> employees = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();


    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        long newId = idCounter.incrementAndGet();
        employee.setId(newId);
        employees.add(employee);
        return employee;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                emp.setName(employeeDetails.getName());
                emp.setDesignation(employeeDetails.getDesignation());
                return emp;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employees.removeIf(emp -> emp.getId().equals(id));
    }
}
