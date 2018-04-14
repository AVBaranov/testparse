package abaranov;

import abaranov.parse.RunParse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main (String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        RunParse runParse = context.getBean(RunParse.class);
        runParse.parse(args[0], args[1]);
    }
}
