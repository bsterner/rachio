package com.rachio.secretsanta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rachio.secretsanta.model.Participant;

@Service
public interface SecretSantaService {

	/**
	 * Creates secret santa assignments from a stored list of users. The stored
	 * list of users is pulled from some type of data repository.
	 * 
	 * @return a <tt>Map</tt> representing the assignments where the key is the
	 *         secret santa and the participant they are assigned to is the
	 *         value.
	 */
	public abstract Map<Participant, Participant> createAssignments();

	/**
	 * Creates secret santa assignments from the provided list of users.
	 * 
	 * @param participants
	 * @return a <tt>Map</tt> representing the assignments where the key is the
	 *         secret santa and the value is the participant they are assigned
	 *         to.
	 */
	public abstract Map<Participant, Participant> createAssignments(List<Participant> participants);

}