package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee){
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("signup done successfully");
    }

    @PostMapping("/savealldata")
    public ResponseEntity<String> saveAllData(@RequestBody List<Employee> employeeList){
        employeeServiceImpl.saveAllData(employeeList);
        return ResponseEntity.ok("saved all data successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean>signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getDataById/{empId}")
    public ResponseEntity<Employee>getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getDataByName/{empName}")
    public ResponseEntity<List<Employee>>getDataByName(@PathVariable String empName){
        return ResponseEntity.ok(employeeServiceImpl.getDataByName(empName));
    }

    @GetMapping("/getDataByEmailId/{empEmailId}")
    public ResponseEntity<Employee>getDataByEmailId(@PathVariable String empEmailId){
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmailId(empEmailId));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee>getDataByContactNumber(@PathVariable long empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabydob/{empDOB}")
    public ResponseEntity <List<Employee>>getDataByDOB(@PathVariable String empDOB){
        return  ResponseEntity.ok(employeeServiceImpl.getDataByDOB(empDOB));
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>>sortById(){
        return ResponseEntity.ok(employeeServiceImpl.sortById());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>>sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.sortByName());
    }

    @GetMapping("/sorBySalary")
    public ResponseEntity<List<Employee>>sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.sortBySalary());
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>>sortByDOB(){
        return ResponseEntity.ok(employeeServiceImpl.sortbydob());
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>>filterDataBySalary(@PathVariable double empSalary){
        return ResponseEntity.ok(employeeServiceImpl.filterDataBySalary(empSalary));
    }

    @GetMapping("/getdatabyanyinput")
    public ResponseEntity<List<Employee>>getDataByAnyInput(@PathVariable String input){
        return ResponseEntity.ok(employeeServiceImpl.getDataByAnyInput(input));
    }

    @PatchMapping("/addressupdation/{empId}")
    public ResponseEntity<String>partialUpdation(@PathVariable int empId,@RequestBody Employee employee){
        employeeServiceImpl.partialUpdation(empId,employee);
        return ResponseEntity.ok("Data updated successfully");
    }
@PutMapping("/updatedata/{empId}")
    public ResponseEntity<String>updateData(@PathVariable int empId,@RequestBody Employee employee) {
    boolean flag = false;
    for (Employee employee1 : employeeServiceImpl.getAllData()) {
        if (employee1.getEmpId() == empId) {
            flag = true;
            employeeServiceImpl.updateData(empId, employee);
        }
    }
        if(!flag) {
            throw new RecordNotFoundException("Id does not exist");
        }
        return ResponseEntity.ok("data updated successfully");
    }


@GetMapping("/checkloaneligibility/{empId}")
public ResponseEntity<String> checkLoanEligibity(@PathVariable int empId){
        String msg="";
        if(employeeServiceImpl.checkLoanEligibility(empId)){
            msg="Eligible for loan";
        }else{
            msg="Not eligible for loan";
        }
        return ResponseEntity.ok(msg);
}


@DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("DATA deleted successfully");
}

@DeleteMapping("/deletealldata")
    public ResponseEntity<String>deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("data deleted successfully");
}

}
