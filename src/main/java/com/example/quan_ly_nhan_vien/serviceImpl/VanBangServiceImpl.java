package com.example.quan_ly_nhan_vien.serviceImpl;

import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.model.VanBang;
import com.example.quan_ly_nhan_vien.repository.VanBangRepository;
import com.example.quan_ly_nhan_vien.service.VanBangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class VanBangServiceImpl implements VanBangService {
    @Autowired
    private VanBangRepository vanBangRepository;

    @Override
    public List<VanBang> findAll(){
        return vanBangRepository.findAll();
    }

    @Override
    public String createVanBang(String name, Date hanHieuLuc, Tinh tinh, Employee employee){
        String result;
        int sumVanBangByTinhAndEmployee = vanBangRepository.sumVangBangByEmployee_Tinh(employee, tinh);
        int sumVanBangByEmployeeAndHanHieuLuc = vanBangRepository.sumVanBangByEmployee_LoaiVanBang(employee, name);
        if(sumVanBangByTinhAndEmployee >= 3){
            result = "So luong van bang còn hiệu lực của"+employee.getName()+"do"+ tinh.getName()+ "cung cấp đã đầy";
        }
        if (sumVanBangByEmployeeAndHanHieuLuc == 0){
            VanBang vanBang = new VanBang();
            vanBang.setName(name);
            vanBang.setHan_hieu_luc(hanHieuLuc);
            vanBang.setNoi_cap(tinh);
            vanBang.setEmployee(employee);
            vanBangRepository.save(vanBang);
            result = "Thêm văn bằng thành công";
        }else {
            result = "Van bang"+name+"của"+employee.getName()+"do"+tinh.getName()+"cung cấp còn hiệu lực";
        }
        return result;
    }
}
