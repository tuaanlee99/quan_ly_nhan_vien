package com.example.quan_ly_nhan_vien.service;

import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.model.VanBang;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface VanBangService {
    List<VanBang> findAll();

    String createVanBang(String name, Date hanHieuLuc, Tinh tinh, Employee employee);
}
