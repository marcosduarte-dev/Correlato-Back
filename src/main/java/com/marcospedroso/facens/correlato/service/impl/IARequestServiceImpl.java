package com.marcospedroso.facens.correlato.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;
import com.marcospedroso.facens.correlato.dto.data.IAResponseData;
import com.marcospedroso.facens.correlato.dto.gemini.GeminiResponse;
import com.marcospedroso.facens.correlato.mapper.data.AnaliseEquivalenciaDataMapper;
import com.marcospedroso.facens.correlato.model.IAResponse;
import com.marcospedroso.facens.correlato.repository.IAResponseRepository;
import com.marcospedroso.facens.correlato.utils.JsonUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class IARequestServiceImpl {
    private final IAResponseRepository iaResponseRepository;

    ResourceBundle res = ResourceBundle.getBundle("db");

    @Transactional
    public IAResponseData analisarEquivalenciaPorIA(AnaliseEquivalenciaData data) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> parts = new HashMap<>();
        parts.put("text", String.format(
            "Tarefa: Analisar a equivalência entre duas disciplinas.\n\n" +
            "Descrição: Você receberá a ementa de duas disciplinas, sendo a primeira a disciplina " +
            "de origem e a segunda a disciplina de destino. Sua tarefa é analisar as equivalências " +
            "e diferenças entre as duas ementas e determinar se a disciplina de destino pode eliminar " +
            "a disciplina de origem. Para que a disciplina de destino elimine a disciplina de origem, " +
            "é necessário que a carga horária e a ementa da disciplina de destino cubram pelo menos " +
            "75%% do conteúdo programático e da carga horária da disciplina de origem. Lembrando que " +
            "algumas universidades escrevem de manerias mais genéricas e outras mais específicas então " +
            "seja flexível quanto a isso.\n\n" +
            "Instruções:\n" +
            "1. Analise a ementa da disciplina de origem e da disciplina de destino.\n" +
            "2. Compare a carga horária e o conteúdo programático das duas disciplinas.\n" +
            "3. Determine se a disciplina de destino pode eliminar a disciplina de origem com base " +
            "nos critérios de pelo menos 75%% de cobertura da carga horária e do conteúdo programático.\n\n" +
            "Ementa da Disciplina de Origem:\n%s\n\n" +
            "Ementa da Disciplina de Destino:\n%s\n\n" +
            "Carga Horária da Disciplina de Origem: %s\n" +
            "Carga Horária da Disciplina de Destino: %s\n\n" +
            "Nota: Por favor, forneça sua análise sobre a equivalência das disciplinas e se a " +
            "disciplina de destino pode eliminar a disciplina de origem com base nos critérios estabelecidos.",
            data.getDisciplinaOrigem().getEmenta(), data.getDisciplinaDestino().getEmenta(),
            data.getDisciplinaOrigem().getCargaHoraria().toString(), data.getDisciplinaDestino().getCargaHoraria().toString()
        ));

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(parts));

        Map<String, Object> responseSchema = new HashMap<>();
        responseSchema.put("type", "OBJECT");

        Map<String, Object> properties = new HashMap<>();
        properties.put("equivalencias", Map.of("type", "STRING"));
        properties.put("diferencas", Map.of("type", "STRING"));
        properties.put("aprovado", Map.of("type", "BOOLEAN"));
        properties.put("porcentagemEquivalencia", Map.of("type", "NUMBER"));
        
        responseSchema.put("properties", properties);

        Map<String, Object> generationConfig = new HashMap<>();
        generationConfig.put("response_mime_type", "application/json");
        generationConfig.put("response_schema", responseSchema);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(content));
        requestBody.put("generationConfig", generationConfig);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        String apiUrl = res.getString("gemini.api.url");
        
        var responseData = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, GeminiResponse.class);

        String textIaData = responseData.getBody().getCandidates().get(0).getContent().getParts().get(0).getText();
        
        IAResponseData iaResponseData = JsonUtils.criarIAResponseData(textIaData);
        iaResponseData.setAnaliseEquivalencia(data);

        IAResponse iaResponse = new IAResponse();
        iaResponse.setAnaliseEquivalencia(AnaliseEquivalenciaDataMapper.fromDTODataToEntity(data));
        iaResponse.setAprovado(iaResponseData.getAprovado());
        iaResponse.setCreatedAt(iaResponseData.getCreatedAt());
        iaResponse.setDiferencas(iaResponseData.getDiferencas());
        iaResponse.setEquivalencias(iaResponseData.getEquivalencias());
        iaResponse.setPorcentagemEquivalencia(iaResponseData.getPorcentagemEquivalencia());
        iaResponse.setRequestRaw(requestBody.toString());
        iaResponse.setResponseRaw(responseData.getBody().toString());

        iaResponseRepository.save(iaResponse);
        
        return iaResponseData;
    }
}