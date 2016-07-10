package com.rachio.secretsanta.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rachio.secretsanta.Constants;
import com.rachio.secretsanta.model.Participant;

/**
 * For this exercise DAO implementation just creates and uses basic data
 * structures. In real production environment data would be retrieved from a
 * database and ideally use some type of persistence framework (MyBatis,
 * Hibernate, TopLink, JDO, JOOQ, etc...).
 * 
 * @Repository annotation is part of SpringMVC Controller/Service/Repository
 *             pattern and represents an object that access a data repository.
 *
 */
@Repository
public class ParticipantDaoImpl implements ParticipantDao {

	@Override
	public List<Participant> getParticpants() {
		return Constants.Players.PLAYER_LIST;
	}

	@Override
	public int[][] getParticipantMatrix() {
		int M = Constants.Players.PLAYER_LIST.size() + 1;
		
		// Matrix representation of previous years (aka rounds)
		// 1) The positional values correspond to our player list in the Constants file
		// 2) The horizontal axis represents the gift receivers
		// 3) The vertical axis represents the secret santa assigned to them
		// 4) The numeric values represent the previous year there was an assignment
		// 5) M means that no earlier Santa has been assigned.
		int[][] rounds = {
		        { 0, M, 3, M, 1, 4, M, 2 },
		        { M, 0, 4, 2, M, 3, M, 1 },
		        { M, 2, 0, M, 1, M, 3, 4 },
		        { M, 1, M, 0, 2, M, 3, 4 },
		        { M, 4, M, 3, 0, M, 1, 2 },
		        { 1, 4, 3, M, M, 0, 2, M },
		        { M, 3, M, 2, 4, 1, 0, M },
		        { 4, M, 3, 1, M, 2, M, 0 }
		};
		
		return rounds;
	}
	
}
