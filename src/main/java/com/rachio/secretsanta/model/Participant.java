package com.rachio.secretsanta.model;


/**
 * A participant in a game.  It's expected that all <tt>Participant</tt>s of a game must use this interface.
 *
 * @author  Brian Sterner
 * @see Person
 * @since 1.0
 */
public interface Participant {

	public String getFirstName();
	
	public String getLastName();
	
	public void setFirstName(String firstName);
	
	public void setLastName(String lastName);
	
}
