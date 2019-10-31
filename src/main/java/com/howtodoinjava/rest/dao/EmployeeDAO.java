package com.howtodoinjava.rest.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;

@Repository
public class EmployeeDAO 
{
    private static Employees list = new Employees();
    private static Employees list1 = new Employees();
    
    static 
    {
        list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));

        list1.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
        list1.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list1.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
        list1.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
    }

    public List<Object> getAllObjects() 
    {
        List<Object> listObjects = new ArrayList<>();
        listObjects.addAll(list.getEmployeeList());
        listObjects.addAll(list1.getEmployeeList());
        return listObjects;
    }

    
    public Employees getAllEmployees() 
    {
        return list;
    }
    
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }

    public Employees getEmployeeById(int id){
        Employees lists = new Employees();
        lists.getEmployeeList().add(list.getEmployeeList().get(id-1));
        return lists;
    }

    public Employees deleteEmployee(int id){
        list.getEmployeeList().remove(id-1);   
        return list;
    }

}
