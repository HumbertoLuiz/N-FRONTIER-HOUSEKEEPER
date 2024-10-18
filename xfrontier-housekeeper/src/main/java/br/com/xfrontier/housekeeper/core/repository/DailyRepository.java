package br.com.xfrontier.housekeeper.core.repository;

import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.customerCompleteNameContain;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.isNoPayment;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.isPaid;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.noApplicants;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.noHousekeeper;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.statusIn;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.withLessThan24HoursForService;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.withMore24HoursSinceCreation;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.withNumberApplicantsEqualTo;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.withNumberApplicantsGreaterThanOrEqualTo;
import static br.com.xfrontier.housekeeper.core.specifications.DailySpecifications.withNumberApplicantsLessThen;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.User;

public interface DailyRepository extends JpaRepository<Daily, Long>, JpaSpecificationExecutor<Daily> {

    List<Daily> findByCustomer(User customer);

    List<Daily> findByHousekeeper(User housekeeper);

    default List<Daily> findByFilter(String customer, List<DailyStatus> status, Sort sort) {
        return this.findAll(
            where(
                customerCompleteNameContain(customer)
                .and(statusIn(status))
            ), sort
        );
    }

    @Query(
        """
        SELECT
            d
        FROM
            Daily d
        WHERE
            d.status = br.com.xfrontier.housekeeper.core.enums.DailyStatus.PAID
        AND
            d.housekeeper IS NULL
        AND
            d.ibgeCode IN :cities
        AND
            :applicant NOT MEMBER OF d.applicants
        AND
            SIZE(d.applicants) < 3
        """
    )
    List<Daily> findOpportunities(List<String> cities, User applicant);

    default List<Daily> getAppropriateForSelectionOfDiarist() {
        return this.findAll(
            where(
                isPaid()
                .and(noHousekeeper())
                .and(withNumberApplicantsEqualTo(3))
            ).or(
                isPaid()
                .and(noHousekeeper())
                .and(withMore24HoursSinceCreation())
                .and(withNumberApplicantsLessThen(3))
                .and(withNumberApplicantsGreaterThanOrEqualTo(1))
            )
        );
    }

    default List<Daily> getAbleToCancel() {
        return this.findAll(
            where(
                isPaid()
                .and(withLessThan24HoursForService())
                .and(noApplicants())
            ).or(
                isNoPayment()
                .and(withMore24HoursSinceCreation())
                .and(noApplicants())
            )
        );
    }

}
