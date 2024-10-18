package br.com.xfrontier.housekeeper.core.events;

import org.springframework.context.ApplicationEvent;

import br.com.xfrontier.housekeeper.core.models.User;

import lombok.Getter;

@Getter
public class NewUserEvent extends ApplicationEvent {

    private static final long serialVersionUID= 1L;

    private User user;

    public NewUserEvent(Object source, User user) {
        super(source);
        this.user= user;
    }

}