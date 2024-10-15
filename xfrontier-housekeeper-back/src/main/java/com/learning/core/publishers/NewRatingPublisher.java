package com.learning.core.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.learning.core.events.NewRatingEvent;
import com.learning.core.models.Rating;

@Component
public class NewRatingPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Rating rating) {
        var event = new NewRatingEvent(this, rating);
        applicationEventPublisher.publishEvent(event);
    }

}
