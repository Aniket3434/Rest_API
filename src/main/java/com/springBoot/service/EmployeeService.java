package com.springBoot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.entity.Employee;
import com.springBoot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	// for insert employees

	public Employee insertEmployee(Employee employee) {
		Employee emp = this.repository.save(employee);
		System.out.println("Employee Added" + emp);
		return emp;

	}

	// get employee by id

	public Optional<Employee> getById(int id) {
		System.out.println("Getting By id");
		return this.repository.findById(id);

	}

	// update employee

	public Employee updateEmployee(Employee employee) {
		if (this.repository.existsById(employee.getEmpId())) {
			return this.repository.save(employee);
		}
		throw new EntityNotFoundException("Employee With the given id not existss " + employee.getEmpId());
	}

	// get all employees

	public List<Employee> getAllEmployee() {
		System.out.println("Get All employees");

		List<Employee> employees = new ArrayList<Employee>();
		this.repository.findAll().forEach(employees::add);
		return employees;
	}

	// Delete Employee By Id
	public boolean deleteEmployee(int empId) {
		if (this.repository.existsById(empId)) {
			this.repository.deleteById(empId);
			return true;
		}

		throw new EntityNotFoundException("Employee With the given id not existss " + empId);
	}

	// get employee name by id
	public String getNameById(int id) {
		System.out.println("Getting name By id");
		return this.repository.findById(id).get().getEmpName();

	}

	// get employee contact by id
	public String getContactById(int id) {
		System.out.println("Getting name By id");
		return this.repository.findById(id).get().getEmpContact();

	}
}
