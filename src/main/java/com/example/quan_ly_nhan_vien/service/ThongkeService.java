package com.example.quan_ly_nhan_vien.service;

import com.example.quan_ly_nhan_vien.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ThongkeService {
    List<Employee> findByVanBangMore2();


    Map<String, Integer> findSumEmployeeByTinh();

    int findSumEmployeeBy1Tinh(String tinh);

    Map<String, Integer> findSumEmployeeByHuyenOfTinh(String tinh_name);

    Map<String, String> findPhanTramEmployee();

    abstract Map<String, String> findPhanTramEmployeeByTinh(String tinh_name);

    Map<String, Integer> findTongEmployeeByVanBang();

    Map<String, Integer> findTongEmployeeByVanBangAndTinh(String tinh_name);

    List<Map<String, Integer>> findTongEmployeeByVanBangAndHuyenByTinh(String tinh_name);

    int sumEmployee();
}
