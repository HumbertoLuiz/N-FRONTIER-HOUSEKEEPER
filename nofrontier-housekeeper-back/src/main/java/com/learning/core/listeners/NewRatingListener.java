package com.learning.core.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.learning.core.enums.DailyStatus;
import com.learning.core.events.NewRatingEvent;
import com.learning.core.models.Rating;
import com.learning.core.repository.DailyRepository;
import com.learning.core.repository.RatingRepository;
import com.learning.core.repository.UserRepository;

@Component
public class NewRatingListener {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DailyRepository dailyRepository;

    @EventListener
    public void handleNewRatingEvent(NewRatingEvent event) {
        var rating = event.getRating();
        updateReputationRating(rating);
        updateStatusDailyEvaluated(rating);
    }

    private void updateStatusDailyEvaluated(Rating rating) {
        var daily = rating.getDaily();
        if (ratingRepository.isCustomerAndHousekeeperRatedDaily(daily)) {
            daily.setStatus(DailyStatus.EVALUATED);
            dailyRepository.save(daily);
        }
    }
     

    private void updateReputationRating(Rating rating) {
        var evaluated = rating.getEvaluated();
        var averageScore = ratingRepository.getRatingAverage(evaluated);
        evaluated.setReputation(averageScore);
        userRepository.save(evaluated);
    }

}
