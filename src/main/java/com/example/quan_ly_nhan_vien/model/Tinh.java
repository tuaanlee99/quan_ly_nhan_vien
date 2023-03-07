package com.example.quan_ly_nhan_vien.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name="tinh")
public class Tinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tinh_id;

    @Column(name = "name", unique = true)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "tinh")
    private List<Huyen> huyenList;

    @OneToMany(mappedBy = "tinh")
    private List<Xa> xaList;

    @OneToMany(mappedBy = "tinh")
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "noi_cap")
    private List<VanBang> vanBangList;

}
