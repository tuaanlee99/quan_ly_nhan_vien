package com.example.quan_ly_nhan_vien.service;

import com.example.quan_ly_nhan_vien.model.Huyen;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HuyenService {
    List<Huyen> findAll();

    Huyen createHuyen(int tinh_id, String huyen_name);

    List<Huyen> findAllByTinh_id(int tinh_id);

    Optional<Huyen> findById(int id);

    Huyen updateById(int id, String tinh_name, String name);

    void delete(Huyen huyenDTO);
}
