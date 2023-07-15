package com.example.relation.repository;

import com.example.relation.entity.DependantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository extends CrudRepository<DependantEntity, Integer> {
}
