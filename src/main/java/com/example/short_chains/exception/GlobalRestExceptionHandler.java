package com.example.short_chains.exception;

import com.example.short_chains.resp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 全局的异常处理类
 *
 * @author plumsun Created on 2023-12-03
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalRestExceptionHandler {

    /**
     * base sys exception handler rest result.
     *
     * @param e the e
     * @return the rest result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response easiBaseSysExceptionHandler(Exception e) {
        log.error("系统异常", e);
        return Response.err(e.getMessage());
    }
}
