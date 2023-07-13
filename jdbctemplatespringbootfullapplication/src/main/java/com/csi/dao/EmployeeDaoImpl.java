package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    JdbcTemplate jdbcTemplate;


    String INSERT_SQL="insert into employee (empid,empname,empaddress,empcontactnumber,empsalary,empdob,empemailid,emppassword)values(?,?,?,?,?,?,?,?)";

    String SELECT_ALL_DATA="select * from employee";

    String SELECT_DATA_BY_ID="select * from employee where empid=?";

    String UPDATE_DATA="update employee set empname=?,empaddress=?,empcontactnumber=?,empsalary=?,empdob=?,empemailid=?,emppassword=? where empid=?";

    String DELETE_DATA_BY_ID="delete from employee where empid=?";

    String DELETE_ALL_DATA="truncate table employee";

    String PARTIAL_UPDATE="update employee set empaddress=? where empid=?";

    private Employee employee(ResultSet resultSet,int numRow) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empContactNumber(resultSet.getLong(4)).empSalary(resultSet.getDouble(5)).empDOB(resultSet.getDate(6)).empEmailId(resultSet.getString(7)).empPassword(resultSet.getString(8)).build();
    }
    @Override
    public void signUp(Employee employee) {

        jdbcTemplate.update(INSERT_SQL,employee.getEmpId(),employee.getEmpName(),employee.getEmpAddress(),employee.getEmpContactNumber(),employee.getEmpSalary(),employee.getEmpDOB(),employee.getEmpEmailId(),employee.getEmpPassword());

    }

    @Override
    public void saveAllData(List<Employee> employeeList) {

       for (Employee employee:employeeList){
           jdbcTemplate.update(INSERT_SQL,employee.getEmpId(),employee.getEmpName(),employee.getEmpAddress(),employee.getEmpContactNumber(),employee.getEmpSalary(),employee.getEmpDOB(),employee.getEmpEmailId(),employee.getEmpPassword());
       }

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;
        for (Employee employee : getAllData()) {
            flag = false;
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<Employee> getAllData() {
        return jdbcTemplate.query(SELECT_ALL_DATA,this::employee);
    }

    @Override
    public Employee getDataById(int empId) {
        return jdbcTemplate.query(SELECT_DATA_BY_ID,this::employee,empId).get(0);
    }

    @Override
    public List<Employee> getDataByName(String empName) {
        return getAllData().stream().filter(emp->emp.getEmpName().equals(empName)).collect(Collectors.toList());
    }

    @Override
    public Employee getDataByEmailId(String empEmailId) {
        return getAllData().stream().filter(emp->emp.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Employee> getDataByDOB(String empDOB) {

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");

        List<Employee>employeeList=new ArrayList<>();
for (Employee employee :getAllData()) {
    String empDOBDB = simpleDateFormat.format(employee.getEmpDOB());

    if(empDOBDB.equals(empDOB)){
        employeeList.add(employee);
    }

}
        return employeeList;
    }

    @Override
    public Employee getDataByContactNumber(long empContactNumber) {
        return getAllData().stream().filter(emp->emp.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Employee> sortById() {
        return getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortbydob() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortByName() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortBySalary() {
        return getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> filterDataBySalary(double empSalary) {
        return getAllData().stream().filter(emp->emp.getEmpSalary()>=empSalary).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getDataByAnyInput(String input) {

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        List<Employee>employeeList=new ArrayList<>();
     for (Employee employee: getAllData()){
         String empDOBDB=simpleDateFormat.format(employee.getEmpDOB());
             if(empDOBDB.equals(input)
                 || String.valueOf(employee.getEmpId()).equals(input)
                     || employee.getEmpName().equals(input)
                     || employee.getEmpAddress().equals(input)
                     || String.valueOf(employee.getEmpContactNumber()).equals(input)
                     || String.valueOf(employee.getEmpSalary()).equals(input)){
             employeeList.add(employee);
         }
     }


        return employeeList;
    }

    @Override
    public void partialUpdation(int empId, Employee employee) {

jdbcTemplate.update(PARTIAL_UPDATE,employee.getEmpAddress(),empId);

    }

    @Override
    public boolean checkLoanEligibility(int empId) {
        boolean flag=false;
        for (Employee employee:getAllData()){
            if(employee.getEmpId()==empId && employee.getEmpSalary()<=30000){
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public void updateData(int empId, Employee employee) {

jdbcTemplate.update(UPDATE_DATA,employee.getEmpName(),employee.getEmpAddress(),employee.getEmpContactNumber(),employee.getEmpSalary(),employee.getEmpDOB(),employee.getEmpEmailId(),employee.getEmpPassword(),empId);

    }

    @Override
    public void deleteDataById(int empId) {
     jdbcTemplate.update(DELETE_DATA_BY_ID,empId);
    }

    @Override
    public void deleteAllData() {
  jdbcTemplate.update(DELETE_ALL_DATA);
    }
}
