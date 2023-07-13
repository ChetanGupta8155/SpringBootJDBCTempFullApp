package com.csi.service;

import com.csi.dao.EmployeeDao;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDaoImpl;
    @Override
    public void signUp(Employee employee) {
        employeeDaoImpl.signUp(employee);
    }

    @Override
    public void saveAllData(List<Employee> employeeList) {
    employeeDaoImpl.saveAllData(employeeList);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        return employeeDaoImpl.signIn(empEmailId,empPassword);
    }

    @Override
    public List<Employee> getAllData() {
        return employeeDaoImpl.getAllData();
    }

    @Override
    public Employee getDataById(int empId) {
        return employeeDaoImpl.getDataById(empId);
    }

    @Override
    public List<Employee> getDataByName(String empName) {
        return employeeDaoImpl.getDataByName(empName);
    }

    @Override
    public Employee getDataByEmailId(String empEmailId) {
        return employeeDaoImpl.getDataByEmailId(empEmailId);
    }

    @Override
    public List<Employee> getDataByDOB(String empDOB) {
        return employeeDaoImpl.getDataByDOB(empDOB);
    }

    @Override
    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeDaoImpl.getDataByContactNumber(empContactNumber);
    }

    @Override
    public List<Employee> sortById() {
        return employeeDaoImpl.sortById();
    }

    @Override
    public List<Employee> sortbydob() {
        return employeeDaoImpl.sortbydob();
    }

    @Override
    public List<Employee> sortByName() {
        return employeeDaoImpl.sortByName();
    }

    @Override
    public List<Employee> sortBySalary() {
        return employeeDaoImpl.sortBySalary();
    }

    @Override
    public List<Employee> filterDataBySalary(double empSalary) {
        return employeeDaoImpl.filterDataBySalary(empSalary);
    }

    @Override
    public List<Employee> getDataByAnyInput(String input) {
        return employeeDaoImpl.getDataByAnyInput(input);
    }

    @Override
    public void partialUpdation(int empId, Employee employee) {
employeeDaoImpl.partialUpdation(empId,employee);
    }

    @Override
    public boolean checkLoanEligibility(int empId) {
        return employeeDaoImpl.checkLoanEligibility(empId);
    }

    @Override
    public void updateData(int empId, Employee employee) {
employeeDaoImpl.updateData(empId,employee);
    }

    @Override
    public void deleteDataById(int empId) {
employeeDaoImpl.deleteDataById(empId);
    }

    @Override
    public void deleteAllData() {
employeeDaoImpl.deleteAllData();
    }
}
