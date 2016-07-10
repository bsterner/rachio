package com.rachio.secretsanta;

import java.util.Random;

import com.rachio.secretsanta.model.Participant;
import com.rachio.secretsanta.model.Person;

public interface Constants {

	public static final Random RANDOM = new Random();

	public static final class Players {
		public static final Participant p1 = new Person("Brian", "Sterner");
		public static final Participant p2 = new Person("Jeff", "Sterner");
		public static final Participant p3 = new Person("Bill", "Sterner");
		public static final Participant p4 = new Person("Kathy", "Sterner");
		public static final Participant p5 = new Person("Cristen", "Brooks");
		public static final Participant p6 = new Person("Jeremiah", "Brooks");
		public static final Participant p7 = new Person("Preslie", "Brooks");
		public static final Participant p8 = new Person("Jack", "Brooks");
	}

}
