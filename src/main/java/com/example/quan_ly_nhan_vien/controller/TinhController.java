package com.example.quan_ly_nhan_vien.controller;

import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.service.TinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("tinhs")
public class TinhController {

    @Autowired
    private TinhService tinhService;

    @GetMapping
    public List<Tinh> findAll(){
        return tinhService.findAll();
    }

    @PostMapping
    public Tinh create(String tinh){
        return tinhService.create(tinh);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tinh> findbyId(@PathVariable int id) throws ResourceNotFoundException {
        Tinh tinhDTO = tinhService.findById(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        return ResponseEntity.ok(tinhDTO);
    }


    @PutMapping("{id}")
    public ResponseEntity<Tinh> update(@PathVariable int id, @Param("name") String name) throws ResourceNotFoundException {
        Tinh tinhDTO = tinhService.findById(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        if ((tinhDTO.getTinh_id() ==id)){
            tinhDTO.setName(name);
            tinhService.save(id, name);
        }
        return ResponseEntity.ok(tinhDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id) throws ResourceNotFoundException {
        Tinh tinhDTO = tinhService.findById(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        if(tinhDTO.getTinh_id() == id){
            tinhService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
