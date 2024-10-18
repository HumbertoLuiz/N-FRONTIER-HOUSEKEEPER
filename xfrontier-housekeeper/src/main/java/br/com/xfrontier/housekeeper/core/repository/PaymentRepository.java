package br.com.xfrontier.housekeeper.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.models.Payment;
import br.com.xfrontier.housekeeper.core.models.User;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByDailyHousekeeperAndDailyStatusIn(User housekeeper, List<DailyStatus> status);

}
