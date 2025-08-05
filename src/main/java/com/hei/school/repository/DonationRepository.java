package com.hei.school.repository;

import com.hei.school.model.Donation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
  List<Donation> findAllByOrderByIdDesc();
}
