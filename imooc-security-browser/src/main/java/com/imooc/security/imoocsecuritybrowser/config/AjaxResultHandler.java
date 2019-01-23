package com.imooc.security.imoocsecuritybrowser.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResultHandler {

    private HttpStatus httpStatus;

    private Integer httpStatusValue;

    private String resultMessage;

}
