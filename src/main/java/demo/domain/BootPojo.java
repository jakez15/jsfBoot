package demo.domain;

import java.io.Serializable;

public class BootPojo implements Serializable {
	
	private static final long serialVersionUID = -2357170823020318987L;

	private String name;
	private String region;
	
	public BootPojo(String string, String string2) {
		this.name = string;
		this.region = string2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	

}
