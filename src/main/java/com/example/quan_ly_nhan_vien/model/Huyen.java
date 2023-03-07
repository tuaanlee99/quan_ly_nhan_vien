package com.example.quan_ly_nhan_vien.model;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "huyen")
public class Huyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int huyen_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "huyen")
    private List<Xa> xaList;

    @OneToMany(mappedBy = "huyen")
    private List<Employee> employeeList;
    @ManyToOne
    @JoinColumn(name = "tinh_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tinh tinh;
}
