package me.pulpury.demowebmvc;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {
	
	interface ValidatedLimit {}
	interface ValidatedName {}
	
	private Integer id;
	
	@NotBlank(groups = ValidatedName.class)
	private String name;
	
	@Min(value = 0, groups = ValidatedLimit.class)
	private Integer limit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
