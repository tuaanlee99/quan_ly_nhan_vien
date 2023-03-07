package com.example.quan_ly_nhan_vien.serviceImpl;

import com.example.quan_ly_nhan_vien.model.Employee;
import com.example.quan_ly_nhan_vien.model.Huyen;
import com.example.quan_ly_nhan_vien.model.Tinh;
import com.example.quan_ly_nhan_vien.repository.EmployeeRepository;
import com.example.quan_ly_nhan_vien.repository.TinhRepository;
import com.example.quan_ly_nhan_vien.service.ThongkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ThongKeServiceImpl implements ThongkeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TinhRepository tinhRepository;

    @Override
    public List<Employee> findByVanBangMore2(){
        return employeeRepository.find_Van_Bang_more_2();
    }

    @Override
    public Map<String, Integer> findSumEmployeeByTinh(){
        Map<String, Integer> map = new HashMap<>();
        List<Tinh> tinhList = tinhRepository.findAll();
        for(Tinh tinh: tinhList){
            map.put(tinh.getName(), tinh.getEmployeeList().size());
        }
        return map;
    }

    @Override
    public int findSumEmployeeBy1Tinh(String tinh){
        Map<String, Integer> map = this.findSumEmployeeByTinh();
        int result = 0;
        if(map.containsKey(tinh)){
            result = map.get(tinh);
        }
        return result;
    }

    @Override
    public Map<String, Integer> findSumEmployeeByHuyenOfTinh(String tinh_name){
        Map<String, Integer> map = new HashMap<>();
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        List<Huyen> huyenList = tinh.getHuyenList();
        for (Huyen huyen : huyenList){
            map.put(huyen.getName(), huyen.getEmployeeList().size());
        }
        return map;
    }

    @Override
    public Map<String, String> findPhanTramEmployee(){
        Map<String, String> map = new HashMap<>();
        int sumEmployee = 0;
        int sum1=0, sum2 = 0, sum0 = 0, sum3 =0;
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee: employeeList){
            sumEmployee++;
            switch (employee.getVanBangList().size()){
                case 0:
                    sum0++;
                    break;
                case 1:
                    sum1++;
                    break;
                case 2:
                    sum2++;
                    break;
                default:
                    sum3++;
            }
        }
        double sum = sumEmployee;
        double sum00 = sum0;
        double sum01 = sum1;
        double sum02 = sum2;
        double sum03 = sum3;
        String patternPercent ="###.##%";
        DecimalFormat numberFormat = new DecimalFormat(patternPercent);

        map.put("employee có số văn bằng là 0", numberFormat.format(sum00/sum));
        map.put("employee có số văn bằng là 1", numberFormat.format(sum01/sum));
        map.put("employee có số văn bằng là 2", numberFormat.format(sum02/sum));
        map.put("employee có số văn lớn hơn 2", numberFormat.format(sum03/sum));

        return  map;
    }

    @Override
    public Map<String, String> findPhanTramEmployeeByTinh(String tinh_name){
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        Map<String, String> map = new HashMap<>();
        int sumEmployee = 0;
        int sum1=0, sum2 = 0, sum0 = 0, sum3 =0;
        List<Employee> employeeList = tinh.getEmployeeList();
        for (Employee employee: employeeList){
            sumEmployee++;
            switch (employee.getVanBangList().size()){
                case 0:
                    sum0++;
                    break;
                case 1:
                    sum1++;
                    break;
                case 2:
                    sum2++;
                    break;
                default:
                    sum3++;
            }
        }
        double sum = sumEmployee;
        double sum00 = sum0;
        double sum01 = sum1;
        double sum02 = sum2;
        double sum03 = sum3;
        String patternPercent ="###.##%";
        DecimalFormat numberFormat = new DecimalFormat(patternPercent);
        map.put("employee có số văn bằng là 0", numberFormat.format(sum00/sum));
        map.put("employee có số văn bằng là 1", numberFormat.format(sum01/sum));
        map.put("employee có số văn bằng là 2", numberFormat.format(sum02/sum));
        map.put("employee có số văn lớn hơn 2", numberFormat.format(sum03/sum));

        return  map;
    }

    @Override
    public Map<String, Integer> findTongEmployeeByVanBang(){
        Map<String, Integer> map = new HashMap<>();
        int sum1=0, sum2 = 0, sum0 = 0, sum3 =0;
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee: employeeList){
            switch (employee.getVanBangList().size()){
                case 0:
                    sum0++;
                    break;
                case 1:
                    sum1++;
                    break;
                case 2:
                    sum2++;
                    break;
                default:
                    sum3++;
            }
        }

        map.put("employee có số văn bằng là 0", sum0);
        map.put("employee có số văn bằng là 1", sum1);
        map.put("employee có số văn bằng là 2", sum2);
        map.put("employee có số văn lớn hơn 2", sum3);

        return  map;
    }

    @Override
    public Map<String, Integer> findTongEmployeeByVanBangAndTinh(String tinh_name){
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        Map<String, Integer> map = new HashMap<>();
        int sum1=0, sum2 = 0, sum0 = 0, sum3 =0;
        List<Employee> employeeList = tinh.getEmployeeList();
        for (Employee employee: employeeList){
            switch (employee.getVanBangList().size()){
                case 0:
                    sum0++;
                    break;
                case 1:
                    sum1++;
                    break;
                case 2:
                    sum2++;
                    break;
                default:
                    sum3++;
            }
        }

        map.put("employee có số văn bằng là 0", sum0);
        map.put("employee có số văn bằng là 1", sum1);
        map.put("employee có số văn bằng là 2", sum2);
        map.put("employee có số văn lớn hơn 2", sum3);

        return  map;
    }

    @Override
    public List<Map<String, Integer>> findTongEmployeeByVanBangAndHuyenByTinh(String tinh_name){
        Tinh tinh = tinhRepository.getTinhByName(tinh_name);
        List<Map<String, Integer>> mapList = new ArrayList<>();
        List<Huyen> huyenList = tinh.getHuyenList();
        for (Huyen huyen: huyenList) {
            List<Employee> employeeList = huyen.getEmployeeList();
            Map<String, Integer> map = new HashMap<>();
            int sum1=0, sum2 = 0, sum0 = 0, sum3 =0;
            for (Employee employee: employeeList){
                switch (employee.getVanBangList().size()){
                    case 0:
                        sum0++;
                        break;
                    case 1:
                        sum1++;
                        break;
                    case 2:
                        sum2++;
                        break;
                    default:
                        sum3++;
                }
            }

            map.put("employee có số văn bằng là 0", sum0);
            map.put("employee có số văn bằng là 1", sum1);
            map.put("employee có số văn bằng là 2", sum2);
            map.put("employee có số văn lớn hơn 2", sum3);
            mapList.add(map);
        }
        return mapList;

    }
    @Override
    public int sumEmployee(){
        return employeeRepository.sumEmployee();
    }
}
