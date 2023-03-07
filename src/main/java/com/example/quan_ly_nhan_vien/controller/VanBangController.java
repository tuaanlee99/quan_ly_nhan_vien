package com.example.quan_ly_nhan_vien.controller;

import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.model.VanBang;
import com.example.quan_ly_nhan_vien.repository.EmployeeRepository;
import com.example.quan_ly_nhan_vien.repository.TinhRepository;
import com.example.quan_ly_nhan_vien.service.VanBangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("vanBang")
public class VanBangController {
    @Autowired
    private VanBangService vanBangService;
    @Autowired
    private TinhRepository tinhRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<VanBang> findAll(){
        return vanBangService.findAll();
    }
    @PostMapping
    public String createVangBAng(@Param("name") String name,@Param("hanHieuLuc") Date hanHieuLuc,@Param("tinh_id") int tinh_id,@Param("employee_id") int employee_id) throws ResourceNotFoundException {
        Tinh tinh = tinhRepository.findById(tinh_id).orElseThrow(()->new ResourceNotFoundException("Không tồn tại tinh với id là"+ tinh_id));
        Employee employee = employeeRepository.findById(employee_id).orElseThrow(()->new ResourceNotFoundException("Không tồn tại employee với id là"+ employee_id));

        return vanBangService.createVanBang(name, hanHieuLuc, tinh, employee);
    }
}
