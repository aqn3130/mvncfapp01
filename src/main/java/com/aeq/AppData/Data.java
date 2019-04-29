package com.aeq.AppData;

import com.sun.tools.javac.comp.Annotate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:/application.properties")
public class Data {
    @Value("${greeting.text}")
    private String greetingText;
    @Value("${greeting.preamble}")
    private String greetingPreambleText;

    @Bean
    public String greeting(){
        return greetingText;
    }

    public class Worker{
        private String text;
        private String preamble;

        public Worker(String preamble,String text){

            this.text = text;
            this.preamble = preamble;
            System.out.println("New instance");
        }

        public void execute(){

            System.out.println(preamble+" "+text);
        }
    }

    @Bean
//    @Scope("prototype")
//    @Profile("dev")
    public Worker worker(){
        return new Worker(greetingPreambleText,greetingText);
    }

}
