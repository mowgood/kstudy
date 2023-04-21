package exam3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("morningGreeting")
@Scope(value = "prototype")
public class MorningGreetingImpl implements Greeting {
    @Override
    public void greet() {
        System.out.println("상쾌한 아침입니다.");
    }
}
