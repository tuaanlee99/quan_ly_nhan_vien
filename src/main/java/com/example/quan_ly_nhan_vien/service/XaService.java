package com.example.quan_ly_nhan_vien.service;

import com.example.quan_ly_nhan_vien.model.Xa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface XaService {
    List<Xa> findAll();

    Xa createByTinh_Huyen(int tinh_id, int huyen_id, String name);

    Xa createXa_Tinh_Huyen(String tinh_name, String huyen_name, String name);

    Optional<Xa> findByID(int id);

    Xa updateXa(int id, String tinh_name, String huyen_name, String name);

    void deleteXa(int id);
}
