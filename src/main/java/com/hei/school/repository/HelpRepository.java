package com.hei.school.repository;

import com.hei.school.model.Help;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpRepository extends JpaRepository<Help, Long> {
    List<Help> findAllByOrderByIdDesc();
}
