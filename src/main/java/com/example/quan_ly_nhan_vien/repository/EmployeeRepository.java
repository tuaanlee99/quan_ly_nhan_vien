package com.example.quan_ly_nhan_vien.repository;

import com.example.quan_ly_nhan_vien.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select employee from Employee employee where " +
            "employee.tinh= (select tinh from Tinh tinh where tinh.name= :tinh)", nativeQuery = true)
    List<Employee> findByTinh(String tinh);

    @Query(value = "select employee from Employee employee where " +
            "employee.tinh=(select tinh from Tinh tinh where tinh.name= :tinh)" +
            "and  employee.huyen= (select huyen from Huyen huyen where huyen.name= :huyen))", nativeQuery = true)
    List<Employee> findByTinh_Huyen(String tinh, String huyen);

    @Query(value = "select employee from Employee employee where " +
            "employee.tinh=(select tinh from Tinh tinh where tinh.name= :tinh)" +
            "and  employee.huyen= (select huyen from Huyen huyen where huyen.name= :huyen)" +
            "and employee.xa= (select xa from Xa xa where xa.name = :xa)", nativeQuery = true)
    List<Employee> findByTinh_Huyen_Xa(String tinh, String huyen, String xa);

    @Modifying
    @Query("delete from Employee e where e.tinh = (select tinh from Tinh tinh where tinh.tinh_id = :tinh_id)")
    void deleteAllByTinh(@Param("tinh_id")int tinh_id);

    @Query("select vanbang.employee from VanBang vanbang where (select count(vanbang.employee) from VanBang vanbang) > 2")
    List<Employee> find_Van_Bang_more_2();

    @Query("select count(employee) from Employee employee")
    int sumEmployee();
}
