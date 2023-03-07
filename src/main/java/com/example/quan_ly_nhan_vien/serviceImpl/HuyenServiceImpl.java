package com.example.quan_ly_nhan_vien.serviceImpl;

import com.example.quan_ly_nhan_vien.model.Huyen;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.repository.HuyenRepository;
import com.example.quan_ly_nhan_vien.repository.TinhRepository;
import com.example.quan_ly_nhan_vien.service.HuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class HuyenServiceImpl implements HuyenService {

    @Autowired
    private HuyenRepository huyenRepository;

    @Autowired
    private TinhRepository tinhRepository;

    @Override
    public List<Huyen> findAll(){
        return huyenRepository.findAll();
    }
    @Override
    public Huyen createHuyen(int tinh_id, String huyen_name){
        Tinh tinh = tinhRepository.findById(tinh_id).get();
        Huyen huyen = new Huyen();
        huyen.setTinh(tinh);
        huyen.setName(huyen_name);
        huyenRepository.save(huyen);
        return huyen;
    }

    @Override
    public List<Huyen> findAllByTinh_id(int tinh_id){
        Tinh tinh = tinhRepository.findById(tinh_id).get();
        List<Huyen> huyenDTOList = tinh.getHuyenList();
        return huyenDTOList;
    }

    @Override
    public Optional<Huyen> findById(int id){
        return huyenRepository.findById(id);
    }

    @Override
    public Huyen updateById(int id, String tinh_name, String name){
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);

        Huyen huyen = huyenRepository.findById(id).get();
        huyen.setTinh(tinh);
        huyen.setName(name);
        huyenRepository.save(huyen);

        return huyen;
    }

    @Override
    public void delete(Huyen huyen){
        huyenRepository.delete(huyen);
    }
}
