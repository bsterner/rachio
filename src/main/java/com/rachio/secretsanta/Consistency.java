package com.rachio.secretsanta;

public enum Consistency {
	
	BC("BC");
	
	private String name;

	private Consistency(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
	    return this.name;
    }

	public String getValue() {
	    return this.name;
    }
	
}
