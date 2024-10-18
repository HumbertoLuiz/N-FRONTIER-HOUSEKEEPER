package br.com.xfrontier.housekeeper.core.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.xfrontier.housekeeper.core.events.NewRatingEvent;
import br.com.xfrontier.housekeeper.core.models.Rating;

@Component
public class NewRatingPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Rating rating) {
        var event = new NewRatingEvent(this, rating);
        applicationEventPublisher.publishEvent(event);
    }

}
