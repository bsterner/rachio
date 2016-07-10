package com.rachio.secretsanta.service.ai;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.rachio.secretsanta.Constants;
import com.rachio.secretsanta.dao.ParticipantDao;
import com.rachio.secretsanta.dao.ParticipantDaoImpl;
import com.rachio.secretsanta.model.Participant;
import com.rachio.secretsanta.service.SecretSantaService;

/**
 * Service class to encapsulate the business logic for assigning secret santas
 * which potentially may change over time.
 * 
 * @author Brian Sterner
 *
 */
@Slf4j
public class SmartSecretSanta implements SecretSantaService {

	private ParticipantDao dao = new ParticipantDaoImpl();

	/* (non-Javadoc)
	 * @see com.rachio.secretsanta.service.SecretSantaServiceIntf#createAssignments()
	 */
	@Override
    public Map<Participant, Participant> createAssignments() {
		List<Participant> participants = dao.getParticpants();
		Map<Participant, Participant> secretSantas = generateAssignments(participants);

		return secretSantas;
	}

	/* (non-Javadoc)
	 * @see com.rachio.secretsanta.service.SecretSantaServiceIntf#createAssignments(java.util.List)
	 */
	@Override
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
