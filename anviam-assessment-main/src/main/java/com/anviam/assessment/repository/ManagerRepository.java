package com.anviam.assessment.repository;


import com.anviam.assessment.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ManagerRepository extends JpaRepository<Manager,Long> {

    Manager findByEmail(String email);
}
