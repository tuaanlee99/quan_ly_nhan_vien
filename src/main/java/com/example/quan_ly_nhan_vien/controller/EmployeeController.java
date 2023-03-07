package com.example.quan_ly_nhan_vien.controller;

import com.example.quan_ly_nhan_vien.exception.ResourceBeUsedException;
import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PostMapping("employees")
    public String createEmployee(@Param("name") String name,@Param("age") int age,@Param("code") String code,@Param("email") String email,
                                 @Param("phone") String phone,@Param("tinh") String tinh_name,@Param("huyen") String huyen_name,@Param("xa") String xa_name)
            throws ResourceNotFoundException, ResourceBeUsedException {
        return employeeService.createEmployee(name,age,code,email,phone,tinh_name,huyen_name,xa_name);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> findByID(@PathVariable int id) throws ResourceNotFoundException {
        Employee employeeDTO = employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee not exist with id:" + id));
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping("employees/{id}")
    public String updateEmployee(@PathVariable int id, @Param("name") String name,@Param("age") int age,@Param("code") String code,@Param("email") String email,
                                 @Param("phone") String phone,@Param("tinh") String tinh_name,@Param("huyen") String huyen_name,@Param("xa") String xa_name)
            throws ResourceBeUsedException, ResourceNotFoundException {
        return employeeService.updateEmployee(id,name,age,code,email,phone,tinh_name,huyen_name,xa_name);
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable int id) throws  ResourceNotFoundException{
        Employee employee = employeeService.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee not exist with id:" + id));
        return employeeService.delete(id);
    }


}
