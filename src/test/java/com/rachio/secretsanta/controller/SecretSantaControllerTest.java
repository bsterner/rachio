package com.rachio.secretsanta.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rachio.secretsanta.Constants;
import com.rachio.secretsanta.dao.ParticipantDao;
import com.rachio.secretsanta.dao.ParticipantDaoImpl;
import com.rachio.secretsanta.model.Participant;

@Slf4j
public class SecretSantaControllerTest {

	private SecretSantaController controller;

	private ParticipantDao dao;

	private List<Participant> participants;

	private Map<Participant, Participant> assignments;

	@Before
	public void setup() {
		controller = new SecretSantaController();
		dao = new ParticipantDaoImpl();
	}

	@After
	public void tearDown() {
		// nothing for now
	}

	@Test
	public void testCreateAssignmentsExisting() {
		participants = dao.getParticpants();
		assignments = controller.createAssignments();

		verifyBasicAssignments();
		verifyPreviousYearAssignments();
	}

	@Test
	public void testCreateAssignmentsProvided() {
		// participants = Arrays.asList(PLAYER1, PLAYER2, PLAYER8, PLAYER3);
		// assignments = controller.createAssignments(participants);
		//
		// verifyBasicAssignments();
	}

	@Ignore
	private void verifyPreviousYearAssignments() {
		int[][] matrix = dao.getParticipantMatrix();

		// Loops through the map of assignments, gets the index of the secret
		// santa in the original list
		// as well as the gift receiver and then uses these indices to check
		// that the value stored in our
		// previous year matrix is > the PREVIOUS_YEAR_LIMIT
		for (Participant secretSanta : assignments.keySet()) {
			Participant giftReceiver = assignments.get(secretSanta);

			// Remember....
			// a) The vertical axis (which translates to the row) represents our
			// secret santa
			// b) The horizontal axis (which translates to the column)
			// represents the gift receiver
			int matrixRow = participants.indexOf(secretSanta);
			int matrixColumn = participants.indexOf(giftReceiver);

			int priorYearAssignment = matrix[matrixRow][matrixColumn];

			log.debug("Previous years value at row {} and column {} is {}", matrixRow, matrixColumn,
			        priorYearAssignment);

			assertTrue(priorYearAssignment > Constants.PREVIOUS_YEAR_LIMIT);
		}
	}

	@Ignore
	private void verifyBasicAssignments() {
		// Verify sizes are equal (ignoring uniqueness)
		assertEquals(participants.size(), assignments.size());

		// Verify uniqueness of keys and values
		assertEquals(participants.size(), new HashSet<Participant>(assignments.keySet()).size());
		assertEquals(participants.size(), new HashSet<Participant>(assignments.values()).size());

		// Verify non-reflexive assignments
		for (Participant p : assignments.keySet()) {
			assertFalse(p.equals(assignments.get(p)));
		}
	}

}
