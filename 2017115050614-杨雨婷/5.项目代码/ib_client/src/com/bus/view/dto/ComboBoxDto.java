package com.bus.view.dto;

public class ComboBoxDto {

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ComboBoxDto(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.value;
	}

	public ComboBoxDto() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	
}
