package com.example.quan_ly_nhan_vien.repository;

import com.example.quan_ly_nhan_vien.model.Xa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XaRepository extends JpaRepository<Xa, Integer> {


    @Query(value = "select xa from Xa xa where " +
            "xa.tinh= (select tinh from Tinh tinh where tinh.name= :tinh)", nativeQuery = true)
    List<Xa> findByTinh(String tinh);


    @Query(value = "select xa from Xa  xa where " +
            "xa.tinh = (select tinh from Tinh tinh where tinh.name= :tinh)" +
            "and xa.huyen = (select huyen from Huyen huyen where huyen.name= :huyen)")
    List<Xa> findByTinh_Huyen(@Param("tinh")String tinh, @Param("huyen")String huyen);

    @Query(value = "select xa from Xa xa where " +
            "xa.tinh = (select tinh from Tinh tinh where tinh.name= :tinh)" +
            "and xa.huyen = (select huyen from Huyen huyen where huyen.name= :huyen)" +
            "and xa.name= :xa", nativeQuery = true)
    Xa findByTinh_Huyen_Xa(@Param("tinh")String tinh, @Param("huyen")String huyen, @Param("xa")String xa);

    @Modifying
    @Query("delete from Xa xa where xa.tinh = (select tinh from Tinh tinh where tinh.tinh_id = :tinh_id)")
    void deleteAllByTinh(@Param("tinh_id")int tinh_id);

}
