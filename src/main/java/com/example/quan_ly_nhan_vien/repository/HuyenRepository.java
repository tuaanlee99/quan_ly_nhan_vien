package com.example.quan_ly_nhan_vien.repository;

import com.example.quan_ly_nhan_vien.model.Huyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuyenRepository extends JpaRepository<Huyen, Integer> {

    @Query(value = "select huyen from Huyen huyen where " +
            "huyen.tinh = (select tinh from Tinh tinh where tinh.id= :tinh_id)", nativeQuery = true)
    List<Huyen> findByTinh(@Param("tinh_id") int tinh_id);

    @Query("select huyen from Huyen huyen where huyen.tinh = (select tinh from Tinh tinh where  tinh.name= :tinh) and huyen.name = :huyen")
    Huyen findByName_Tinh(@Param("tinh")String tinh, @Param("huyen")String huyen);


    @Modifying
    @Query("delete from Huyen huyen where huyen.tinh = (select tinh from Tinh tinh where tinh.tinh_id = :tinh_id)")
    void deleteAllByTinh(@Param("tinh_id")int tinh_id);

}
