package com.example.relation.repository;

import com.example.relation.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.debugger.Address;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
//    void findByLastName()
}
