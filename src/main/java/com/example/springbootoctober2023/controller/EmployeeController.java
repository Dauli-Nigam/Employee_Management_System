package com.example.springbootoctober2023.controller;

import com.example.springbootoctober2023.model.Employee;
import com.example.springbootoctober2023.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired

    private EmployeeService service;

    @GetMapping("/all_emp")
    public List<Employee> getAllEmployees() {

        return service.getAllEmployees();


    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // CREATE MODEL ATTRIBUTE TO BIND FROM DATA...
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "new_employee";


    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // GET EMPLOYEE FROM THE SERVICE
        Employee employee = service.getEmployeeById(id);
        // SET EMPLOYEE AS A ATTRIBUTE TO PRE-POPULATE THE FORM
        model.addAttribute("employee", employee);
        return "update_employee";


    }

    @PostMapping("/save_emp")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/";


    }

    @DeleteMapping("/delete_emp/{id}")

    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.service.deleteEmployeeById(id);

        return "redirect:/";


    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);


    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 5;
        Page<Employee> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("empList", listEmployees);

        return "index";

    }


}
