package demo.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("jsfSpringRequestScopedBean")
@Scope("request")
public class JSFSpringRequestScopedBean {
    private String welcomeMessage = "Populated by JSF Spring Request Scoped bean";

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getValue(){
        return "Jake Test";
    }

    public String getHotCodeValue(){
        return "JZ Hot Code Value Test";
    }

}
