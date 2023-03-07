package com.example.quan_ly_nhan_vien.service;

import com.example.quan_ly_nhan_vien.model.Tinh;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TinhService {
    List<Tinh> findAll();

    Optional<Tinh> findById(int id);

    Tinh save(int tinh_id, String name);

    Tinh create(String tinh_input);

    void delete(int tinh_id);
}
