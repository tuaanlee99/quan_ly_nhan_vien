package com.example.quan_ly_nhan_vien.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "xa")
public class Xa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int xa_id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "tinh_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tinh tinh;

    @ManyToOne
    @JoinColumn(name = "huyen_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Huyen huyen;

    @OneToMany(mappedBy = "xa")
    private List<Employee> employeeList;

}
