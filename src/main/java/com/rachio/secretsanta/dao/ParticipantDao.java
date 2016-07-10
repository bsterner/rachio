package com.rachio.secretsanta.dao;

import java.util.List;

import com.rachio.secretsanta.model.Participant;

/**
 * Participant DAO is responsible for performing CRUD operations on
 * <Participant>.
 */
public interface ParticipantDao {

	/**
	 * Retrieves the current list of participants in the secret santa game.
	 * 
	 * @param id
	 *            the id of the note
	 * @return the a list of <tt>Participant</tt> objects.
	 */
	public List<Participant> getParticpants();

	/**
	 * Gets a matrix of participants representing the individual and previous
	 * assignments.
	 * 
	 * @return a 2-dimensional array corresponding to the data where the numeric
	 *         values represent the entries in a list.
	 */
	public int[][] getParticipantMatrix();

}
