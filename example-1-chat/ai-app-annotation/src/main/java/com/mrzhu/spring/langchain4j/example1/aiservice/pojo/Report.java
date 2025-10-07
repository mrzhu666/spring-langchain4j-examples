package com.mrzhu.spring.langchain4j.example1.aiservice.pojo;

import dev.langchain4j.model.output.structured.Description;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

// 添加描述可以帮助大模型理解
@Description("报告")
@Schema(description = "学习建议报告")
@Data
public class Report {
    @Schema(description = "名字",allowableValues = "张飞")
    @Description("名字")
    String name;
    
    @Description("一系列建议")
    List<String> suggetstionList;
}
