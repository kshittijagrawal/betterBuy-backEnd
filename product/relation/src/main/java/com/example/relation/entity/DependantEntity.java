package com.example.relation.entity;

import javax.persistence.*;

@Table
@Entity
public class DependantEntity {


    @Id
    private Integer id;
    private String name;
    private int age;
    private String relation;
    private String phoneNumber;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "Address_id", referencedColumnName = "id")
    private AddressEntity address;


    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

//    public int getAddress_id() {
//        return address_id;
//    }
//
//    public void setAddress_id(int address_id) {
//        this.address_id = address_id;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
