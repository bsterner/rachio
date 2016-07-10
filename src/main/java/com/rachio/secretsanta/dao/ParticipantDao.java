package com.rachio.secretsanta.dao;

import java.util.List;

import com.rachio.secretsanta.model.Participant;

/**
 * Participant DAO is responsible for performing CRUD operations on <Participant>. 
 */
public interface ParticipantDao {
	
	/**
	 * Retrieves the current list of participants in the secret santa game.
	 * 
	 * @param id the id of the note
	 * @return the a list of <tt>Participant</tt> objects.
	 */
	public List<Participant> getParticpants();

}
