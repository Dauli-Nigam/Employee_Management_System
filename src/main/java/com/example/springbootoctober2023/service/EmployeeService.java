package com.example.springbootoctober2023.service;

import com.example.springbootoctober2023.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

   Page<Employee>  findPaginated(int pageNo,int pageSize);
    Page<Employee>  findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);

    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

}
