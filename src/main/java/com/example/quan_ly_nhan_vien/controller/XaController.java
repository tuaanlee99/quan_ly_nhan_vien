package com.example.quan_ly_nhan_vien.controller;

import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Xa;
import com.example.quan_ly_nhan_vien.service.XaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class XaController {
    @Autowired
    private XaService xaService;

    @GetMapping("xas")
    public List<Xa> findAll(){
        return xaService.findAll();
    }

    @PostMapping("xas")
    public Xa createXa_Huyen_Tinh(@Param("tinh") String tinh,@Param("huyen") String huyen,@Param("name") String name){
        return xaService.createXa_Tinh_Huyen(tinh, huyen, name);
    }

    @PostMapping("{tinh_id}/{huyen_id}/xas")
    public Xa createXaByTinh_Huyen(@PathVariable int tinh_id, @PathVariable int huyen_id, String name){
        return xaService.createByTinh_Huyen(tinh_id, huyen_id, name);
    }

    @GetMapping("xas/{id}")
    public ResponseEntity<Xa> findByID(@PathVariable int id) throws ResourceNotFoundException {
        Xa xaDTO = xaService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        return ResponseEntity.ok(xaDTO);
    }

    @PutMapping("xas/{id}")
    public ResponseEntity<Xa> findByID(@PathVariable int id, String tinh, String huyen, String name) {
        Xa xaDTO = xaService.updateXa(id,tinh,huyen,name);
        return ResponseEntity.ok(xaDTO);
    }

    @DeleteMapping("xas/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) throws ResourceNotFoundException {
        Xa xaDTO = xaService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("tinh not exist with id:" + id));
        if(xaDTO != null){
            xaService.deleteXa(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}