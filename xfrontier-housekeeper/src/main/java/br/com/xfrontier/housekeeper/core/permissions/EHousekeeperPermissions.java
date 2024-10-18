package br.com.xfrontier.housekeeper.core.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface EHousekeeperPermissions {

    @PreAuthorize("hasAnyAuthority('HOUSEKEEPER', 'CUSTOMER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isHousekeeperOrCustomer {
    }
}