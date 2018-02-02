package demo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import demo.domain.BootPojo;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class JSFViewScopedBean implements Serializable{
	
	private static final long serialVersionUID = -6577905732006156661L;

	private String welcomeMessage = "Populated by Custom spring View scoped bean";

	private List<BootPojo> bootPojoList = new ArrayList<>();
	
	private List<BootPojo> filteredBootPojoList = new ArrayList<>();
	
	@PostConstruct
	public void populateBootPojoList(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getViewRoot();
		BootPojo bootPojo0 = new BootPojo("Boot0", "region0");
		BootPojo bootPojo1 = new BootPojo("Boot1", "region1");
		BootPojo bootPojo2 = new BootPojo("Boot2", "region2");
		BootPojo bootPojo3 = new BootPojo("Boot3", "region3");
		BootPojo bootPojo4 = new BootPojo("Boot4", "region4");
		bootPojoList = Arrays.asList(bootPojo0, bootPojo1, bootPojo2, bootPojo3, bootPojo4);
	}

    public List<BootPojo> getBootPojoList() {
		return bootPojoList;
	}

	public void setBootPojoList(List<BootPojo> bootPojoList) {
		this.bootPojoList = bootPojoList;
	}

	public String getWelcomeMessage() {
        return welcomeMessage;
    }

	public List<BootPojo> getFilteredBootPojoList() {
		return filteredBootPojoList;
	}

	public void setFilteredBootPojoList(List<BootPojo> filteredBootPojoList) {
		this.filteredBootPojoList = filteredBootPojoList;
	}
	
}
