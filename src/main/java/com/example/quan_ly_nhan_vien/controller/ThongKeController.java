package com.example.quan_ly_nhan_vien.controller;

import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.service.ThongkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("ThongKe")
public class ThongKeController {

    @Autowired
    private ThongkeService thongkeService;

    @GetMapping("employee_van_bang_more_2")
    public List<Employee> findByVanBangMore2(){
        return thongkeService.findByVanBangMore2();
    }

    @GetMapping("sumEmployeeByTinh")
    public Map<String, Integer> findSumEmployeeByTinh(){
        return thongkeService.findSumEmployeeByTinh();
    }

    @GetMapping("sumEmployeeBy1Tinh")
    public int findSumEmployeeBy1Tinh(@Param("tinh") String tinh){
        return thongkeService.findSumEmployeeBy1Tinh(tinh);
    }

    @GetMapping("sumEmployeeByHuyenOfTinh")
    public Map<String, Integer> findSumEmployeeByHuyenOfTinh(@Param("tinh") String tinh){
        return thongkeService.findSumEmployeeByHuyenOfTinh(tinh);
    }
    @GetMapping("findTongEmployeeByVanBang")
    public  Map<String, Integer> findTongEmployeeByVanBang(){
        return thongkeService.findTongEmployeeByVanBang();
    }

    @GetMapping("findTongEmployeeByVanBangAndTinh")
    public Map<String, Integer> findTongEmployeeByVanBangAndTinh(@Param("tinh") String tinh){
        return thongkeService.findTongEmployeeByVanBangAndTinh(tinh);
    }


    @GetMapping("findPhanTramEmployee")
    public Map<String, String> findPhanTramEmployee(){
        return thongkeService.findPhanTramEmployee();
    }

    @GetMapping("findPhanTramEmployeeByTinh")
    public Map<String, String> findPhanTramEmployeeByTinh(@Param("tinh") String tinh){
        return thongkeService.findPhanTramEmployeeByTinh(tinh);
    }

    @GetMapping("findTongEmployeeByVanBangAndHuyenOfTinh")
    public List<Map<String, Integer>> findTongEmployeeByVanBangAndHuyenOfTinh(@Param("tinh") String tinh){
        return thongkeService.findTongEmployeeByVanBangAndHuyenByTinh(tinh);
    }

    @GetMapping("sumEmployee")
    public int sumEmployee(){
        return thongkeService.sumEmployee();
    }

}
