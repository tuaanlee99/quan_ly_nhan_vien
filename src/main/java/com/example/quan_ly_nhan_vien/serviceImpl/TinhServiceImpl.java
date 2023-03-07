package com.example.quan_ly_nhan_vien.serviceImpl;

import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.repository.EmployeeRepository;
import com.example.quan_ly_nhan_vien.repository.HuyenRepository;
import com.example.quan_ly_nhan_vien.repository.TinhRepository;
import com.example.quan_ly_nhan_vien.repository.XaRepository;
import com.example.quan_ly_nhan_vien.service.TinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TinhServiceImpl implements TinhService {

    @Autowired
    private TinhRepository tinhRepository;
    @Autowired
    private HuyenRepository huyenRepository;
    @Autowired
    private XaRepository xaRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Tinh> findAll() {
        return tinhRepository.findAll();
    }

    @Override
    public Tinh create(String tinh_input) {
        Tinh tinh = new Tinh();
        tinh.setName(tinh_input);
        return tinhRepository.save(tinh);
    }

    @Override
    public Optional<Tinh> findById(int id){
        return tinhRepository.findById(id);
    }

    @Override
    public void delete(int tinh_id){
        Tinh tinh = tinhRepository.findById(tinh_id).get();
        huyenRepository.deleteAllByTinh(tinh_id);
        tinhRepository.delete(tinh);
        xaRepository.deleteAllByTinh(tinh_id);
        employeeRepository.deleteAllByTinh(tinh_id);
    }

    @Override
    public Tinh save(int tinh_id, String name){
        Tinh tinh = new Tinh();
        tinh.setTinh_id(tinh_id);
        tinh.setName(name);

        return tinhRepository.save(tinh);
    }

}
