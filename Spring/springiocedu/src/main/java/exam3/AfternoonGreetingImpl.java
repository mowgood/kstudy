package exam3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("afternoonGreeting")
@Scope(value = "prototype")
public class AfternoonGreetingImpl implements Greeting {
    @Override
    public void greet() {
        System.out.println("즐거운 오후되세요.");
    }
}
