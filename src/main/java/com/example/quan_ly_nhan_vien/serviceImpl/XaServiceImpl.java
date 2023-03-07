package com.example.quan_ly_nhan_vien.serviceImpl;

import com.example.quan_ly_nhan_vien.model.Huyen;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.model.Xa;
import com.example.quan_ly_nhan_vien.repository.HuyenRepository;
import com.example.quan_ly_nhan_vien.repository.TinhRepository;
import com.example.quan_ly_nhan_vien.repository.XaRepository;
import com.example.quan_ly_nhan_vien.service.XaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class XaServiceImpl implements XaService {

    @Autowired
    private XaRepository xaRepository;
    @Autowired
    private TinhRepository tinhRepository;
    @Autowired
    private HuyenRepository huyenRepository;

    @Override
    public List<Xa> findAll(){
        return xaRepository.findAll();
    }

    @Override
    public Xa createByTinh_Huyen(int tinh_id, int huyen_id, String name){
        Tinh tinh =tinhRepository.findById(tinh_id).get();
        Huyen huyen = huyenRepository.findById(huyen_id).get();
        Xa xa = new Xa();
        xa.setTinh(tinh);
        xa.setHuyen(huyen);
        xa.setName(name);
        xaRepository.save(xa);
        return xa;
    }


    @Override
    public Xa createXa_Tinh_Huyen(String tinh_name, String huyen_name, String name){
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        if(tinh == null){
            tinh = new Tinh();
            tinh.setName(tinh_name);
            tinh = tinhRepository.save(tinh);
        }
        Huyen huyen = huyenRepository.findByName_Tinh(tinh_name, huyen_name);
        if(huyen == null){
            huyen = new Huyen();
            huyen.setTinh(tinh);
            huyen.setName(huyen_name);
            huyenRepository.save(huyen);
        }
        Xa xa = new Xa();
        xa.setTinh(tinh);
        xa.setHuyen(huyen);
        xa.setName(name);


        return xaRepository.save(xa);
    }

    @Override
    public Optional<Xa> findByID(int id){
        return xaRepository.findById(id);
    }
    @Override
    public Xa updateXa(int id, String tinh_name, String huyen_name, String name){
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        if(tinh == null){
            tinh = new Tinh();
            tinh.setName(tinh_name);
            tinh = tinhRepository.save(tinh);
        }
        Huyen huyen = huyenRepository.findByName_Tinh(tinh_name, huyen_name);
        if(huyen == null){
            huyen = new Huyen();
            huyen.setTinh(tinh);
            huyen.setName(huyen_name);
            huyenRepository.save(huyen);
        }
        Xa xa = xaRepository.findById(id).get();
        xa.setTinh(tinh);
        xa.setHuyen(huyen);
        xa.setName(name);


        return xaRepository.save(xa);
    }
    @Override
    public void deleteXa(int id){
        Xa xa = xaRepository.findById(id).get();
        xaRepository.delete(xa);
    }

}
