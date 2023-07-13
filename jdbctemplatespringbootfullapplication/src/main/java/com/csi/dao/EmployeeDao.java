package com.csi.dao;


import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public void saveAllData(List<Employee> employeeList);

    public boolean signIn(String empEmailId,String empPassword);

    public  List<Employee>getAllData();

    public Employee getDataById(int empId);

    public List<Employee> getDataByName(String empName);

    public Employee getDataByEmailId(String empEmailId);


    List<Employee> getDataByDOB(String empDOB);

    public Employee getDataByContactNumber(long empContactNumber);

    public List<Employee> sortById();

    public List<Employee>sortbydob();

    public List<Employee>sortByName();

    public List<Employee>sortBySalary();

    public List<Employee>filterDataBySalary(double empSalary);

    public List<Employee>getDataByAnyInput(String input);

    public void partialUpdation(int empId, Employee employee);

    public boolean checkLoanEligibility(int empId);

    public void updateData(int empId,Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();
}
