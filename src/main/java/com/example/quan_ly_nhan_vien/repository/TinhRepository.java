package com.example.quan_ly_nhan_vien.repository;

import com.example.quan_ly_nhan_vien.model.Tinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhRepository extends JpaRepository<Tinh, Integer> {


    @Query("select tinh from Tinh tinh where tinh.name = :name ")
    Tinh getTinhByName(@Param("name") String name);
}
