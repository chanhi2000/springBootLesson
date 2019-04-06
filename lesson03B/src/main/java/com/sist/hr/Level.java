package com.sist.hr;

public enum Level {
	BASIC("1"),SILVER("2"),GOLD("3");
	
	private final String value;
	
	Level(String value){
		this.value = value;
	}

	//DB에 입력
	public String toStr() {
		return value;
	}

	public static Level val(String value) {
		switch(value) {
			case "1": return BASIC;
			case "2": return SILVER;
			case "3": return GOLD;
			default: throw new AssertionError("Unknown value:"+value);
		}
	}
}
