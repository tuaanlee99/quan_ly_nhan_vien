package com.example.quan_ly_nhan_vien.service;

import com.example.quan_ly_nhan_vien.exception.ResourceBeUsedException;
import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    List<Employee> findAll();

    String createEmployee(String name, int age, String code, String email, String phone, String tinh_name, String huyen_name, String xa_name) throws ResourceNotFoundException, ResourceBeUsedException;

    String updateEmployee(int id, String name, int age, String code, String email, String phone, String tinh_name, String huyen_name, String xa_name) throws ResourceNotFoundException, ResourceBeUsedException;

    Optional<Employee> findById(int id);

    String delete(int id);
}
