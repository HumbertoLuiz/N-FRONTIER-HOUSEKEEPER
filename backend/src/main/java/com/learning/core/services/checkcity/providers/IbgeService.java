package com.learning.core.services.checkcity.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.core.services.checkcity.adapters.CheckCityService;
import com.learning.core.services.checkcity.dtos.CityResponse;
import com.learning.core.services.checkcity.exceptions.CheckCityServiceException;

@Service
public class IbgeService implements CheckCityService {

    private final static String BASE_URL= "https://servicodados.ibge.gov.br/api/v1";

    private final RestTemplate clienteHttp= new RestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CityResponse findCityByIbgeCode(String ibgeCode) throws CheckCityServiceException {
        var url= BASE_URL + "/localidades/municipios/" + ibgeCode;
        var response= clienteHttp.getForEntity(url, String.class);
        var jsonNode= getJsonNode(response);
        validateJsonNode(jsonNode);
        var city= getCity(jsonNode);
        var state= getEstado(jsonNode);
        return new CityResponse(ibgeCode, city, state);
    }

    private String getEstado(JsonNode jsonNode) {
        return jsonNode.path("microrregiao")
            .path("mesorregiao")
            .path("UF")
            .path("sigla")
            .asText();
    }

    private String getCity(JsonNode jsonNode) {
        return jsonNode.path("name").asText();
    }

    private void validateJsonNode(JsonNode jsonNode) {
        if (jsonNode.path("name").asText().isEmpty()) {
            throw new CheckCityServiceException("City not found");
        }
    }

    private JsonNode getJsonNode(ResponseEntity<String> response) {
        try {
            return objectMapper.readTree(response.getBody());
        } catch (JsonMappingException e) {
            throw new CheckCityServiceException(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            throw new CheckCityServiceException(e.getLocalizedMessage());
        }
    }

}