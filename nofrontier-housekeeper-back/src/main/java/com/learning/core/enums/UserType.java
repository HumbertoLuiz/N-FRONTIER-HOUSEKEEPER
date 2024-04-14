package com.learning.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
    CUSTOMER (1),
    HOUSEKEEPER (2),
    ADMIN (3);

    private Integer id;
}
