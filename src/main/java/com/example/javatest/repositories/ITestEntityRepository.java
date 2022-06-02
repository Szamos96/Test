package com.example.javatest.repositories;

import com.example.javatest.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestEntityRepository extends JpaRepository<TestEntity, String> {
}
