package com.example.quan_ly_nhan_vien.serviceImpl;

import com.example.quan_ly_nhan_vien.exception.ResourceBeUsedException;
import com.example.quan_ly_nhan_vien.exception.ResourceNotFoundException;
import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.model.Huyen;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.model.Xa;
import com.example.quan_ly_nhan_vien.repository.EmployeeRepository;
import com.example.quan_ly_nhan_vien.repository.HuyenRepository;
import com.example.quan_ly_nhan_vien.repository.TinhRepository;
import com.example.quan_ly_nhan_vien.repository.XaRepository;
import com.example.quan_ly_nhan_vien.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TinhRepository tinhRepository;
    @Autowired
    private HuyenRepository huyenRepository;
    @Autowired
    private XaRepository xaRepository;

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public String createEmployee(String name, int age, String code, String email, String phone, String tinh_name, String huyen_name, String xa_name) throws ResourceNotFoundException, ResourceBeUsedException {
        StringBuilder result = new StringBuilder();

        List<Employee> employeeList = employeeRepository.findAll();

        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        if(tinh == null){
            result.append("tinh không tồn tại");
        }
        Huyen huyen = huyenRepository.findByName_Tinh(tinh_name, huyen_name);
        if(huyen == null){
            result.append("huyen không tồn tại");
        }
        Xa xa = xaRepository.findByTinh_Huyen_Xa(tinh_name, huyen_name, xa_name);
        if(xa == null){
            result.append("xa không tồn tại");
        }
       if(tinh != null && huyen !=null && xa != null){
           Employee employee = new Employee();
           employee.setName(name);
           employee.setAge(age);
           employee.setCode(code);
           employee.setEmail(email);
           employee.setPhone(phone);
           employee.setTinh(tinh);
           employee.setHuyen(huyen);
           employee.setXa(xa);

           for (Employee employee1: employeeList) {
               if (employee == employee1){
                   result.append("employee" + employee1.getName() + "đã tồn tại");
                   throw new ResourceBeUsedException("Error:"+ result.toString(), employee1);
               }
           }

           employeeRepository.save(employee);
       }else {
           throw new ResourceNotFoundException("Error:" + result.toString());
       }
       return "employee được thêm thành công!";
    }

    @Override
    public String updateEmployee(int id, String name, int age, String code, String email, String phone, String tinh_name, String huyen_name, String xa_name) throws ResourceNotFoundException, ResourceBeUsedException {
        StringBuilder result = new StringBuilder();

        List<Employee> employeeList = employeeRepository.findAll();

        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        if(tinh == null){
            result.append("tinh không tồn tại");
        }
        Huyen huyen = huyenRepository.findByName_Tinh(tinh_name, huyen_name);
        if(huyen == null){
            result.append("huyen không tồn tại");
        }
        Xa xa = xaRepository.findByTinh_Huyen_Xa(tinh_name, huyen_name, xa_name);
        if(xa == null){
            result.append("xa không tồn tại");
        }
        if(tinh != null && huyen !=null && xa != null){
            Employee employee = employeeRepository.findById(id).get();
            employee.setName(name);
            employee.setAge(age);
            employee.setCode(code);
            employee.setEmail(email);
            employee.setPhone(phone);
            employee.setTinh(tinh);
            employee.setHuyen(huyen);
            employee.setXa(xa);

            for (Employee employee1: employeeList) {
                if (employee == employee1){
                    result.append("employee" + employee1.getName() + "đã tồn tại");
                    throw new ResourceBeUsedException("Error:"+ result.toString(), employee1);
                }
            }

            employeeRepository.save(employee);
        }else {
            throw new ResourceNotFoundException("Error:" + result.toString());
        }
        return "employee được update thành công!";
    }

    @Override
    public Optional<Employee> findById(int id){
        return employeeRepository.findById(id);
    }

    @Override
    public String delete(int id){
        Employee employee = employeeRepository.findById(id).get();
        if(employee == null){
            return "employee không tồn tại";
        }else {
            employeeRepository.delete(employee);
            return "delete thành công!";
        }
    }
}
