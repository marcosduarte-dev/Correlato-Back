package com.marcospedroso.facens.correlato.dto.gemini;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    private Content content;
    private String finishReason;
    private double avgLogprobs;
}
