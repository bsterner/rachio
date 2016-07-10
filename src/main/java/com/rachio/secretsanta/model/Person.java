package com.rachio.secretsanta.model;

import lombok.Data;
import lombok.NonNull;

/**
 * Represents a <tt>Person</tt> instance of a <tt>Participant</tt> in a game.
 *
 * The <tt>Data</tt> annotation generates the toString, equals, hashCode,
 * gettter/setter and Constructor with arguments (for all fields annotated with
 * NonNull).
 * 
 * @author Brian Sterner
 * @see Data
 * @see NonNull
 * @since 1.0
 */

@Data
public class Person implements Participant {

	@NonNull
	private String firstName;

	@NonNull
	private String lastName;

	@Override
	public String toString() {
		return new StringBuilder(this.firstName).append(" ").append(this.lastName).toString();
	}

}
