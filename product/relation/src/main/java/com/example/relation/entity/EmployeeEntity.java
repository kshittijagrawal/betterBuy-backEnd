package com.example.relation.dto;

import com.example.relation.entity.AddressEntity;
import com.example.relation.entity.DependantEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
public class EmployeeEntity {
    @Id
    private Integer id;
    private String firstName;


    private String lastName;
    private Date dob;
    private Date doj;
    private int fixed_salary;
    private int variable_salary;

    @ManyToOne
    @JoinColumn(name = "office", referencedColumnName = "id")
    private AddressEntity office_address;

    @JoinColumn(name = "home", referencedColumnName = "id")
    @ManyToOne
    private AddressEntity home_address;

    @ManyToMany
    @JoinTable(name = "employee_dependent", joinColumns = {
            @JoinColumn(name = "employee_id", referencedColumnName = "id")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "dependent_id", referencedColumnName ="id")
            })
    private List<DependantEntity> employeeDependentList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public int getFixed_salary() {
        return fixed_salary;
    }

    public void setFixed_salary(int fixed_salary) {
        this.fixed_salary = fixed_salary;
    }

    public int getVariable_salary() {
        return variable_salary;
    }

    public void setVariable_salary(int variable_salary) {
        this.variable_salary = variable_salary;
    }

    public AddressEntity getOffice_address() {
        return office_address;
    }

    public void setOffice_address(AddressEntity office_address) {
        this.office_address = office_address;
    }

    public AddressEntity getHome_address() {
        return home_address;
    }

    public void setHome_address(AddressEntity home_address) {
        this.home_address = home_address;
    }
}
