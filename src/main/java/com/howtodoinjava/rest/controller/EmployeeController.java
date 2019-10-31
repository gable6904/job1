package com.howtodoinjava.rest.controller;

import java.net.URI;
import java.util.List;

import com.howtodoinjava.rest.dao.EmployeeDAO;
import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
// import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.DeleteMapping;
  

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDao;

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees() {
        return employeeDao.getAllEmployees();
    }

    @GetMapping(path = "/getObj", produces = "application/json")
    public List<Object> getObject() {
        return employeeDao.getAllObjects();
    }

    @GetMapping(path = "", produces = "application/json", params = "id")
    public Employees getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    // @RequestMapping(path="", produces = "application/json", params =
    // "id",method=RequestMethod.DELETE)
    @DeleteMapping(path = "/delete", produces = "application/json", params ="id")
    public Employees deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
            @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody Employee employee) throws Exception {
        // Generate resource id
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        // add resource
        employeeDao.addEmployee(employee);

        // Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId())
                .toUri();

        // Send location in response
        return ResponseEntity.created(location).build();
    }
}
