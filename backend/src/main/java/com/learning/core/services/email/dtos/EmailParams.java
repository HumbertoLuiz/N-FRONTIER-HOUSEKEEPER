package com.learning.core.services.email.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailParams {

    private String addressee;
    private String subject;
    private String template;
    private Map<String, Object> props;
}
