package com.c.forms;

public class ContactSearchForm {

	
	private String field;
	private String value;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ContactSearchForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactSearchForm(String field, String value) {
		super();
		this.field = field;
		this.value = value;
	}
	
	
}
