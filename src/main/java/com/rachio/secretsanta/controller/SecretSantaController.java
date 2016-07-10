package com.rachio.secretsanta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.rachio.secretsanta.model.Participant;
import com.rachio.secretsanta.service.SecretSantaService;

@Controller
public class SecretSantaController {

	/**
	 * Creates secret santa assignments from existing participants.
	 */
	public Map<Participant, Participant> createAssignments() {
		return new SecretSantaService().createAssignments();
	}

	/**
	 * Creates secret santa assignments from the provided <tt>Participant</tt>
	 * list.
	 * 
	 * @param participants
	 *            the list of participants used to create the assignments.
	 * @return a
	 */
	public Map<Participant, Participant> createAssignments(List<Participant> participants) {
		return new SecretSantaService().createAssignments(participants);
	}

}
