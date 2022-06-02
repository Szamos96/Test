package com.example.javatest.rest.response;

import com.example.javatest.common.CustomZonedDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({
        TestResponseBody.CONTENT_FIELD,
        TestResponseBody.TIMESTAMP_FIELD,
        TestResponseBody.LONGEST_PALINDROME_SIZE_FIELD

})
public class TestResponseBody {

    protected static final String CONTENT_FIELD = "content";
    protected static final String TIMESTAMP_FIELD = "timestamp";
    protected static final String LONGEST_PALINDROME_SIZE_FIELD = "longest_palindrome_size";


    @JsonProperty(CONTENT_FIELD)
    String content;

    @JsonProperty(TIMESTAMP_FIELD)
    @JsonSerialize(using = CustomZonedDateSerializer.class)
    ZonedDateTime timestamp;

    @JsonProperty(LONGEST_PALINDROME_SIZE_FIELD)
    int longestPalindromeSize;

}
