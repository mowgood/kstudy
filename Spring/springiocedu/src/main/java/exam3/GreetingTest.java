package exam3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

public class GreetingTest {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("exam3.xml");
        LocalDateTime localDateTime = (LocalDateTime) factory.getBean("localTimeBean");

        int hour = localDateTime.getHour();
        Greeting bean;

        if(hour >= 6 && hour <12) {
            bean = (Greeting) factory.getBean("morningGreeting");
        } else if(hour >= 12 && hour <17) {
            bean = (Greeting) factory.getBean("afternoonGreeting");
        } else if(hour >= 17 && hour <22) {
            bean = (Greeting) factory.getBean("eveningGreeting");
        } else {
            bean = (Greeting) factory.getBean("nightGreeting");
        }
        bean.greet();
        ((ClassPathXmlApplicationContext)factory).close();
    }
}
