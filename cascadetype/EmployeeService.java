package com.example.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.controller.Logging;
import com.example.model.AccountEntityTest;
import com.example.model.EmployeeDetailEntity;
import com.example.model.EmployeeEntity;
import com.example.model.EmployeeEntityTest;
import com.example.repository.EmployeeDetailRepository;
import com.example.repository.EmployeeRepository;
import com.example.repository.EmployeeTestRepository;

@ComponentScan 
@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
    
    @Autowired
    EmployeeTestRepository testrepository;
    
    
    @Autowired
    EmployeeDetailRepository detailRepository;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     
    public EmployeeEntity getEmployeeById(Long id) throws Exception 
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new Exception("Employee Not Present to Fetch");
        }
    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws Exception 
    {
        
    	Logging.info(" New Record : "+entity.toString());
    	Optional<EmployeeEntity> employee = repository.findById(entity.getEmpId());
        
    	Logging.info("Before inserting New Record");
        
        if(employee.isPresent()) 
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmpName(entity.getEmpName());
            newEntity.setEmpExperience(entity.getEmpExperience());
            newEntity.setEmpJoinedDate(entity.getEmpJoinedDate());
            
            Logging.info("Employee Record Updated ");
            
            
            EmployeeDetailEntity employeDetailEntity = new EmployeeDetailEntity(); 
            employeDetailEntity.setEmpExperience(1);
            employeDetailEntity.setEmpPreviousCompany("TCS");
            //employeDetailEntity =  detailRepository.save(employeDetailEntity); 
            newEntity.setEmployeeDetail(employeDetailEntity);
            employeDetailEntity.setEmployee(newEntity);
            ; 
            
            Logging.info("Employee Record Before Insertion :  Employee ID : "+entity.getEmpId()+" ,EmployeeDetail :  "+employeDetailEntity.getEmpId());
            newEntity = repository.save(newEntity); // To update the Recod into the table
            
            Logging.info("Employee Detail Record Updated ");
            
            return newEntity;
        } else {
            
            
            EmployeeDetailEntity employeDetailEntity = new EmployeeDetailEntity(); 
            employeDetailEntity.setEmpExperience(1);
            employeDetailEntity.setEmpPreviousCompany("TCS");
            entity.setEmployeeDetail(employeDetailEntity);
            Logging.info("Employee Record Before Insertion :  Employee ID : "+entity.getEmpId()+" ,EmployeeDetail :  "+employeDetailEntity.getEmpId());
            
            entity = repository.save(entity);
            
            Logging.info("New Record Inserted ");
            return entity;
        }
        
        
    } 
     
    public void deleteEmployeeById(Long id) throws Exception 
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new Exception("No employee available to Delete");
        }
    }
    
    public EmployeeEntityTest createEmployee(EmployeeEntity entity) throws Exception 
    {
        AccountEntityTest account = new AccountEntityTest();
        account.setAccountNumber("123-345-65454");

        Logging.info("Employee Record Before Insertion Test :"); 
        
        // Add new Employee object
        EmployeeEntityTest emp = new EmployeeEntityTest();
        emp.setEmail("demo-user@mail.com");
        emp.setFirstName("demo");
        emp.setLastName("user");

        emp.setAccount(account);
        account.setEmployee(emp);

        Logging.info("Employee Record Before Insertion Test2 :");
        // Save Employee
        emp = testrepository.save(emp);
        Logging.info("Employee Record Before Insertion Test3 :");
        
        //Delete Record
        this.deleteEmployeeTestById(41);
        
        
		return emp;

    }
    
    
    public void deleteEmployeeTestById(int id) throws Exception 
    {
        Optional<EmployeeEntityTest> employee = testrepository.findById(id);
         
        if(employee.isPresent()) 
        {
        	testrepository.deleteById(id);
        } else {
            throw new Exception("No employee Test available to Delete");
        }
        
        Logging.info("Employee Record Deleted Test :"); 

    }
   
    
}