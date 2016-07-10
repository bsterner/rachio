package com.rachio.secretsanta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.rachio.secretsanta.model.Participant;
import com.rachio.secretsanta.model.Person;

public interface Constants {

	public static final Random RANDOM = new Random();
	
	public static final int PREVIOUS_YEAR_LIMIT = 3;

	public static final class Players {

		public static final Participant PLAYER1 = new Person("Brian", "Sterner");

		public static final Participant PLAYER2 = new Person("Jeff", "Sterner");

		public static final Participant PLAYER3 = new Person("Bill", "Sterner");

		public static final Participant PLAYER4 = new Person("Kathy", "Sterner");

		public static final Participant PLAYER5 = new Person("Cristen", "Brooks");

		public static final Participant PLAYER6 = new Person("Jeremiah", "Brooks");

		public static final Participant PLAYER7 = new Person("Preslie", "Brooks");

		public static final Participant PLAYER8 = new Person("Jack", "Brooks");

		public static final List<Participant> PLAYER_LIST = Arrays.asList(PLAYER1, PLAYER2, PLAYER3, PLAYER4, PLAYER5,
		        PLAYER6, PLAYER7, PLAYER8);
	}

}
