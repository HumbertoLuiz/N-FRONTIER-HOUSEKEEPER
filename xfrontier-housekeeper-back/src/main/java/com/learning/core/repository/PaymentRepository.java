package com.learning.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.core.enums.DailyStatus;
import com.learning.core.models.Payment;
import com.learning.core.models.User;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByDailyHousekeeperAndDailyStatusIn(User housekeeper, List<DailyStatus> status);

}
