package demo.view;

import demo.service.SingletonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class JSFSpringSessionScopedBean {

    @Autowired
    private SingletonService singletonService;

    private String welcomeMessage = "Populated by JSF Spring Session Scoped bean";

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
