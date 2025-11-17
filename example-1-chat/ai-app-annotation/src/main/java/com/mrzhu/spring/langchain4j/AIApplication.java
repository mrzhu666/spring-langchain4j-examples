package com.mrzhu.spring.langchain4j;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class AIApplication implements CommandLineRunner {
    
    @Resource
    private ApplicationContext applicationContext;
    
    public static void main(String[] args) throws UnknownHostException {
        //SpringApplication.run(AIApplication.class, args);
        SpringApplication app=new SpringApplication(AIApplication.class);
        ConfigurableApplicationContext application=app.run(args);
        //ConfigurableApplicationContext application=SpringApplication.run(Knife4jSpringBootDemoApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n\t"+
                "Doc: \thttp://{}:{}/doc.html\n"+
                "----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));
    }
    
    @Override
    public void run(String... args) throws Exception {
        Map<String, ChatModel> beansOfType = applicationContext.getBeansOfType(ChatModel.class);
        log.info("ChatModel的Bean有："+beansOfType.toString());
        
        Map<String, StreamingChatModel> beansOfType1 = applicationContext.getBeansOfType(StreamingChatModel.class);
        log.info("StreamingChatModel的Bean有："+beansOfType1.toString());
        
    }
}
