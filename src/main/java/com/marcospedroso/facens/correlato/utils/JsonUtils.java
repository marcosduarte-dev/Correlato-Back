package com.marcospedroso.facens.correlato.utils;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcospedroso.facens.correlato.dto.data.IAData;
import com.marcospedroso.facens.correlato.dto.data.IAResponseData;

public class JsonUtils {
    public static IAResponseData criarIAResponseData(String jsonString) {
        IAResponseData iaResponseData = new IAResponseData();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            IAData iaData = objectMapper.readValue(jsonString, IAData.class);

            iaResponseData.setAprovado(iaData.isAprovado());
            iaResponseData.setDiferencas(iaData.getDiferencas());
            iaResponseData.setEquivalencias(iaData.getEquivalencias());
            iaResponseData.setPorcentagemEquivalencia(iaData.getPorcentagemEquivalencia());
            iaResponseData.setCreatedAt(LocalDateTime.now());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return iaResponseData;
    }
}
