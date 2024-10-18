package br.com.xfrontier.housekeeper.core.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.xfrontier.housekeeper.core.events.NewUserEvent;
import br.com.xfrontier.housekeeper.core.models.User;

@Component
public class NewUserPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publish(User user) {
        var event= new NewUserEvent(this, user);
        eventPublisher.publishEvent(event);
    }

}