package com.mrzhu.spring.langchain4j.controller;

import com.mrzhu.spring.langchain4j.exception.BusinessException;
import com.mrzhu.spring.langchain4j.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "其它")
public class OtherController {
    
    @Operation(summary = "测试接口")
    @Parameters({
        @Parameter(name = "test", description = "测试参数",required = true,in = ParameterIn.PATH)
    })
    @GetMapping("/test")
    public String test(@RequestParam(value = "test", defaultValue = "测试接口") String test){
        if (test.equals("测试接口")) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR);
        }
        
        return test;
    }
    
}
