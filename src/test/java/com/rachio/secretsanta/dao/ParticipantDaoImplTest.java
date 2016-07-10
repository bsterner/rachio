package com.rachio.secretsanta.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rachio.secretsanta.model.Participant;

public class ParticipantDaoImplTest {

	private ParticipantDao dao;

	private List<Participant> participants;

	@Before
	public void setUp() throws Exception {
		dao = new ParticipantDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetParticipants() {
		participants = dao.getParticpants();
		assertNotNull(participants);
		assertTrue(participants.size() > 0);
		assertEquals(participants.size(), new HashSet<Participant>(participants).size());
	}

}
