package com.example.quan_ly_nhan_vien.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "van_bang")
public class VanBang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int van_bang_id;

    @Column(name ="name")
    private String name;

    @Column(name = "han_hieu_luc")
    @DateTimeFormat
    private Date han_hieu_luc;

    @ManyToOne
    @JoinColumn(name = "tinh_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tinh noi_cap;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

}
