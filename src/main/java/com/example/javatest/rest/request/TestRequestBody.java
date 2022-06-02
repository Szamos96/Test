package com.example.javatest.rest.request;

import com.example.javatest.common.CustomZonedDateDeserializer;
import com.example.javatest.common.CustomZonedDateSerializer;
import com.example.javatest.common.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({
        TestRequestBody.CONTENT_FIELD,
        TestRequestBody.TIMESTAMP_FIELD
})
public class TestRequestBody {

    protected static final String CONTENT_FIELD = "content";
    protected static final String TIMESTAMP_FIELD = "timestamp";


    @JsonProperty(CONTENT_FIELD)
    @NotBlank(message = ErrorMessages.CONTENT_MUST_BE_PROVIDED)
    String content;

    @JsonProperty(TIMESTAMP_FIELD)
    @JsonDeserialize(using = CustomZonedDateDeserializer.class)
    @JsonSerialize(using = CustomZonedDateSerializer.class)
    ZonedDateTime timestamp;


}
