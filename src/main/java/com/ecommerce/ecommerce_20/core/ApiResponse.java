package com.ecommerce.ecommerce_20.core;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

//@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse<T> extends ModelBase{
    private HttpStatus httpStatus;
    private Integer code;
    private String message;
    private T data;

}
