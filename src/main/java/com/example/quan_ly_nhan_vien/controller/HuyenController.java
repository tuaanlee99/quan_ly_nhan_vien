package com.example.quan_ly_nhan_vien.controller;

import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Huyen;
import com.example.quan_ly_nhan_vien.service.HuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class HuyenController {

    @Autowired
    private HuyenService huyenService;

    @GetMapping("huyens")
    public List<Huyen> findAll(){
        return huyenService.findAll();
    }

    @PostMapping("{tinh_id}/huyens")
    public Huyen createHuyen(@PathVariable int tinh_id,@Param("huyen_name") String huyen_name){
        return huyenService.createHuyen(tinh_id, huyen_name);
    }

    @GetMapping("{tinh_id}/huyens")
    public List<Huyen> findAllByTinh_id(@PathVariable int tinh_id){
        return huyenService.findAllByTinh_id(tinh_id);
    }

    @GetMapping("huyens/{id}")
    public ResponseEntity<Huyen> findbyId(@PathVariable int id) throws ResourceNotFoundException {
        Huyen huyenDTO = huyenService.findById(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        return ResponseEntity.ok(huyenDTO);
    }

    @PutMapping("huyens/{id}")
    public ResponseEntity<Huyen> updateById(@PathVariable int id,@Param("tinh") String tinh,@Param("name") String name){
        Huyen huyenDTO = huyenService.updateById(id, tinh, name);
        return ResponseEntity.ok(huyenDTO);
    }
    @DeleteMapping("huyens/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable int id) throws ResourceNotFoundException {
        Huyen huyenDTO = huyenService.findById(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        if(huyenDTO.getHuyen_id() == id){
            huyenService.delete(huyenDTO);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
