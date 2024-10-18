package br.com.xfrontier.housekeeper.core.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.xfrontier.housekeeper.core.enums.DailyStatus;
import br.com.xfrontier.housekeeper.core.events.NewRatingEvent;
import br.com.xfrontier.housekeeper.core.models.Rating;
import br.com.xfrontier.housekeeper.core.repository.DailyRepository;
import br.com.xfrontier.housekeeper.core.repository.RatingRepository;
import br.com.xfrontier.housekeeper.core.repository.UserRepository;

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
