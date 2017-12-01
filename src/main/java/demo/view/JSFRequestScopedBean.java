package demo.view;

import org.springframework.context.annotation.Scope;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@Scope("request")
public class JSFRequestScopedBean {
    
    private String welcomeMessage = "Populated by JSF created bean";

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
