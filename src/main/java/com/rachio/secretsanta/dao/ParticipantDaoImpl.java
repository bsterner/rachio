package com.rachio.secretsanta.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.rachio.secretsanta.model.Participant;

import static com.rachio.secretsanta.Constants.Players.*;

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
		return Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
	}

}
