package br.com.xfrontier.housekeeper.core.specifications;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.models.Daily;
import br.com.xfrontier.housekeeper.core.models.Daily_;
import br.com.xfrontier.housekeeper.core.models.User_;

import jakarta.persistence.criteria.JoinType;

public class DailySpecifications {

    public static Specification<Daily> withNumberApplicantsEqualTo(int numberApplicants) {
        return (root, query, criteriaBuilder) -> {
            var quantityApplicants = criteriaBuilder.size(root.get(Daily_.applicants));
            return criteriaBuilder.equal(quantityApplicants, numberApplicants);
        };
    }

    public static Specification<Daily> withNumberApplicantsLessThen(int numberApplicants) {
        return (root, query, criteriaBuilder) -> {
            var quantityApplicants = criteriaBuilder.size(root.get(Daily_.applicants));
            return criteriaBuilder.lessThan(quantityApplicants, numberApplicants);
        };
    }

    public static Specification<Daily> withNumberApplicantsGreaterThanOrEqualTo(int numberApplicants) {
        return (root, query, criteriaBuilder) -> {
            var quantityApplicants = criteriaBuilder.size(root.get(Daily_.applicants));
            return criteriaBuilder.greaterThanOrEqualTo(quantityApplicants, numberApplicants);
        };
    }

    public static Specification<Daily> isPaid() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Daily_.status), DailyStatus.PAID);
        };
    }

    public static Specification<Daily> isNoPayment() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Daily_.status), DailyStatus.NO_PAYMENT);
        };
    }

    public static Specification<Daily> noHousekeeper() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get(Daily_.housekeeper));
        };
    }

    public static Specification<Daily> withMore24HoursSinceCreation() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(
                root.get(Daily_.createdAt),
                LocalDateTime.now().minusHours(24)
            );
        };
    }

    public static Specification<Daily> noApplicants() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isEmpty(root.get(Daily_.applicants));
        };
    }

    public static Specification<Daily> withLessThan24HoursForService() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(
                root.get(Daily_.dateService),
                LocalDateTime.now().plusHours(24)
            );
        };
    }

    public static Specification<Daily> customerCompleteNameContain(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return criteriaBuilder.and();
            }
            var customer = root.join(Daily_.customer, JoinType.LEFT);
            var completeNameSmall = criteriaBuilder.lower(customer.get(User_.completeName));
            return criteriaBuilder.like(completeNameSmall, "%" + searchValue.toLowerCase() + "%");
        };
    }

    public static Specification<Daily> statusIn(List<DailyStatus> status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get(Daily_.status).in(status);
        };
    }

}
