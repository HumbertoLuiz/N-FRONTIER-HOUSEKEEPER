package com.learning.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.core.models.Daily;
import com.learning.core.models.Rating;
import com.learning.core.models.User;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByDailyAndEvaluator(Daily daily, User evaluator);

    @Query(
        """
        SELECT
            AVG(r.score)
        FROM
            Rating r
        WHERE
            r.evaluated = :user
        """
    )
    Double getRatingAverage(User user);

    @Query(
        """
        SELECT
            COUNT(*) = 2
        FROM
            Rating r
        WHERE
            r.daily = :daily
        """
    )
    boolean isCustomerAndHousekeeperRatedDaily(Daily daily);

    boolean existsByEvaluatorAndDailyId(User evaluator, Long dailyId);

    Page<Rating> findByEvaluated(User evaluated, Pageable pageable);

    default List<Rating> getLastRatings(User evaluated) {
        var ratingSort = Sort.sort(Rating.class);
        var sort = ratingSort.by(Rating::getCreatedAt).descending();
        var pageable = PageRequest.of(0, 2, sort);
        return findByEvaluated(evaluated, pageable).getContent();
    }

}
