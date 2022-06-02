package com.example.javatest.entities.DTO;

import com.example.javatest.common.CustomZonedDateDeserializer;
import com.example.javatest.common.CustomZonedDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDTO {

    private String content;

    @JsonSerialize(using = CustomZonedDateSerializer.class)
    @JsonDeserialize(using = CustomZonedDateDeserializer.class)
    private ZonedDateTime timestamp;

}
