package com.rachio.secretsanta.service;

import static com.rachio.secretsanta.Constants.Players.p1;
import static com.rachio.secretsanta.Constants.Players.p2;
import static com.rachio.secretsanta.Constants.Players.p3;
import static com.rachio.secretsanta.Constants.Players.p8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rachio.secretsanta.dao.ParticipantDao;
import com.rachio.secretsanta.dao.ParticipantDaoImpl;
import com.rachio.secretsanta.model.Participant;

public class SecretSantaServiceTest {

	private SecretSantaService controller;

	private ParticipantDao dao;

	private Map<Participant, Participant> assignments;

	@Before
	public void setup() {
		controller = new SecretSantaService();
		dao = new ParticipantDaoImpl();
	}

	@After
	public void tearDown() {
		// nothing for now
	}

	@Test
	public void testCreateAssignmentsExisting() {
		List<Participant> participants = dao.getParticpants();
		assignments = controller.createAssignments();
		verifyAssignments(participants);
	}

	@Test
	public void testCreateAssignmentsProvided() {
		List<Participant> participants = Arrays.asList(p1, p2, p8, p3);
		assignments = controller.createAssignments(participants);
		verifyAssignments(participants);
	}
	
	@Ignore
	private void verifyAssignments(List<Participant> participants) {
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
