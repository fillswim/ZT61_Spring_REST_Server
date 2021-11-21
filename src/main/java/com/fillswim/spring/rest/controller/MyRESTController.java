package com.fillswim.spring.rest.controller;

import com.fillswim.spring.rest.entity.Employee;
import com.fillswim.spring.rest.exception_handling.NoSuchEmployeeException;
import com.fillswim.spring.rest.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Это контроллер, который управляет REST запросами и ответами
@RequestMapping("/api")
public class MyRESTController {

    private final EmployeeService employeeService;

    public MyRESTController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Метод GET        /api/employees                  Получение всех работников
    // Метод GET        /api/employees{employeeID}      Получение одного работника
    // Метод POST       /api/employees                  Добавление работника
    // Метод PUT        /api/employees                  Изменение работника
    // Метод DELETE     /api/employees{employeeID}      Удаление работника

    // Метод GET        /api/employees                  Получение всех работников
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {

        List<Employee> allEmployees = employeeService.getAllEmployees();

        return allEmployees;
    }

    // Метод GET        /api/employees{employeeID}      Получение одного работника
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        // Выбрасывание исключения, если работник не найден
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }

        return employee;
    }

    // Метод POST       /api/employees                  Добавление работника
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {

        // Сохраняем пришедшего работника
        employeeService.saveEmployee(employee);
        // Возвращаем сохраненного работника, но уже с id
        return employee;

    }

    // Метод PUT        /api/employees                  Изменение работника
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        // Изменение в БД уже существующего работника
        employeeService.saveEmployee(employee);
        // Возвращается тот же работник
        return employee;
    }

    // Метод DELETE     /api/employees{employeeID}      Удаление работника
    @DeleteMapping("/employees/{id}")
    public String deleteEmployees(@PathVariable int id) {

        // Проверка наличия работника в БД
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database");
        }

        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + " was deleted";
    }


}
