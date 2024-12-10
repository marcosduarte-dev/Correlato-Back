package com.marcospedroso.facens.correlato.dto.gemini;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    private List<Part> parts;
    private String role;

}
