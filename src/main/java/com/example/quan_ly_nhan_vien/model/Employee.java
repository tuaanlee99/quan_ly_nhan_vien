package com.example.quan_ly_nhan_vien.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    @NotNull(message = "name khong the de trong")
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    @NotBlank(message = "name khong the de trong")
    private String code;

    @Column(name="email")
    @Email(message = "ivaial email adressed")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^\\d{10}$", message ="chỉ chứa ký tự số và không dài quá 11 chữ số")
    @Size(max = 11)
    private String phone;

    @Column(name = "age")
    @Min(value = 18, message = "age không nhỏ hơn 18")
    private int age;

    @ManyToOne
    @JoinColumn(name = "tinh_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tinh tinh;

    @ManyToOne
    @JoinColumn(name = "huyen_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Huyen huyen;

    @ManyToOne
    @JoinColumn(name ="xa_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Xa xa;

    @OneToMany(mappedBy = "employee")
    private List<VanBang> vanBangList;

}
