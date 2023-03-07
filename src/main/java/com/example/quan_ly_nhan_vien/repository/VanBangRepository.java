package com.example.quan_ly_nhan_vien.repository;

import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.model.VanBang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VanBangRepository extends JpaRepository<VanBang, Integer> {


    @Query(value = "select vanBang from VanBang vanBang where " +
            "vanBang.tinh = (select tinh from Tinh tinh where tinh.name= :tinh)", nativeQuery = true)
    List<VanBang> findByTinh(String tinh);



    @Query(value = "select count(vanBang) from VanBang vanBang where " +
            "vanBang.employee = :employee and vanBang.name = :name and vanBang.han_hieu_luc > current_date", nativeQuery = true)
    int sumVanBangByEmployee_LoaiVanBang(Employee employee, String name);

    @Query("select count(vanbang) from VanBang vanbang where vanbang.employee = :employee and vanbang.noi_cap = :tinh and vanbang.han_hieu_luc > current_date ")
    int sumVangBangByEmployee_Tinh(Employee employee, Tinh tinh);

    @Modifying
    @Query("delete from VanBang  vb where vb.noi_cap = (select tinh from Tinh tinh where tinh.tinh_id = :tinh_id)")
    void deleteAllByTinh(@Param("tinh_id")int tinh_id);
}
