package com.rachio.secretsanta.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.rachio.secretsanta.Constants;
import com.rachio.secretsanta.dao.ParticipantDao;
import com.rachio.secretsanta.dao.ParticipantDaoImpl;
import com.rachio.secretsanta.model.Participant;

/**
 * Service class to encapsulate the business logic for assigning secret santas
 * which potentially may change over time.
 * 
 * @author Brian Sterner
 *
 */
@Slf4j
public class SecretSantaService {

	/**
	 * Creates secret santa assignments from a stored list of users. The stored
	 * list of users is pulled from some type of data repository.
	 * 
	 * @return a <tt>Map</tt> representing the assignments where the key is the
	 *         secret santa and the participant they are assigned to is the
	 *         value.
	 */
	public Map<Participant, Participant> createAssignments() {
		ParticipantDao dao = new ParticipantDaoImpl();
		List<Participant> participants = dao.getParticpants();
		Map<Participant, Participant> secretSantas = generateAssignments(participants);

		return secretSantas;
	}

	/**
	 * Creates secret santa assignments from the provided list of users.
	 * 
	 * @param participants
	 * @return a <tt>Map</tt> representing the assignments where the key is the
	 *         secret santa and the value is the participant they are assigned
	 *         to.
	 */
	public Map<Participant, Participant> createAssignments(List<Participant> participants) {
		Map<Participant, Participant> secretSantas = generateAssignments(participants);

		return secretSantas;
	}

	/**
	 * Randomly generates a secret santa assignments from the provided list of
	 * participants.
	 * 
	 * @param participants
	 *            the participant list to randomly generate the assignments.
	 * @return a <tt>Map</tt> representing the assignments where the key is the
	 *         secret santa and the value is the participant they are assigned
	 *         to.
	 */
	private Map<Participant, Participant> generateAssignments(List<Participant> participants) {
		List<Participant> shuffled = shuffleParticipants(participants);

		// Assign secret santa to adjacent participant in list. Last participant
		// will be assigned to first
		Map<Participant, Participant> secretSantas = new HashMap<Participant, Participant>();
		for (int i = 0; i < shuffled.size() - 1; i++) {
			secretSantas.put(shuffled.get(i), shuffled.get(i + 1));
		}

		// Assign last participant to first
		secretSantas.put(shuffled.get(shuffled.size() - 1), shuffled.get(0));

		printSecretSantas(secretSantas);

		return secretSantas;
	}

	/**
	 * Handles the shuffling of the users in the list. This could be complex or
	 * very simple.
	 * 
	 * @param participants
	 *            the participant list to shuffle.
	 * @return the shuffled list of participants.
	 */
	private List<Participant> shuffleParticipants(List<Participant> participants) {
		log.debug("List BEFORE shuffling [{}]", participants);

		Collections.shuffle(participants, Constants.RANDOM);

		log.debug("List AFTER shuffling [{}]", participants);

		return participants;
	}

	private void printSecretSantas(Map<Participant, Participant> secretSantas) {
		log.debug("SECRET SANTA ASSIGNMENTS :: [ Secret Santa ] ===> [ Participant ]");
		for (Participant santa : secretSantas.keySet()) {
			log.debug("\t{} ====> {}", santa, secretSantas.get(santa));
		}
	}

}
