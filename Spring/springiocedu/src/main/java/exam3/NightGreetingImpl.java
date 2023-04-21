package exam3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("nightGreeting")
@Scope(value = "prototype")
public class NightGreetingImpl implements Greeting {
    @Override
    public void greet() {
        System.out.println("안녕히 주무세요.");
    }
}
