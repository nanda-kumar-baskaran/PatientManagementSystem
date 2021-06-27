package com.soficlinic.patientmanagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soficlinic.patientmanagement.dao.Visit;

@Transactional
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
