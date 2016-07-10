package com.rachio.secretsanta.service.ai;

/**
 *
 * Secret Santa Algorithm class designed from example in problem II of Choco3 API
 *
 *
 * Choco3 model by Hakan Kjellerstrand (hakank@gmail.com)
 * http://www.hakank.org/choco3/
 *
 */
import static com.rachio.secretsanta.Constants.Players.PLAYER_LIST;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.chocosolver.samples.AbstractProblem;
import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;
import org.chocosolver.util.ESat;

import com.rachio.secretsanta.Consistency;
import com.rachio.secretsanta.Constants;
import com.rachio.secretsanta.dao.ParticipantDao;
import com.rachio.secretsanta.dao.ParticipantDaoImpl;
import com.rachio.secretsanta.model.Participant;
import com.rachio.secretsanta.service.SecretSantaService;

@Slf4j
public class SmarterSecretSanta extends AbstractProblem implements SecretSantaService {

	private static final String SOLVER_SECRET_SANTA = "Secret Santa Solver";

	private static final String CONSTRAINT_SORT_TYPE_DETECT = "detect";

	private static final String BOUNDED_NAME_DISTANCE_TO_MAXIMIZE = "distanceToMaximize";

	private static final String BOUNDED_NAME_SANTA_DISTANCE = "santaDistance";

	private static final String BOUNDED_NAME_SANTAS = "santas";

	private IntVar[] santas;

	private IntVar[] santaDistance;

	private IntVar distanceToMaximize;

	private int[][] rounds;

	private int numberOfSantas;

	private int maximize;

	private Map<Participant, Participant> assignments = new HashMap<Participant, Participant>();

	private ParticipantDao dao = new ParticipantDaoImpl();

	@Override
	public void buildModel() {
		log.debug("numberOfSantas: {}", numberOfSantas);

		// After this is run: santas[0 -> 7] = [0,7]
		santas = VariableFactory.boundedArray(BOUNDED_NAME_SANTAS, numberOfSantas, 0, numberOfSantas - 1, solver);

		// After this is run: [santaDistance[0 -> 7] = [0,9]
		santaDistance = VariableFactory.boundedArray(BOUNDED_NAME_SANTA_DISTANCE, numberOfSantas, 0, maximize, solver);

		// After this is run: distanceToMaximize = [0,81]
		distanceToMaximize = VariableFactory.bounded(BOUNDED_NAME_DISTANCE_TO_MAXIMIZE, 0, maximize * maximize, solver);

		// Creates constraint that values cannot be equal to each other
		solver.post(IntConstraintFactory.alldifferent(santas, Consistency.BC.name()));

		// Optimizes "distance" based on values in matrix representing earlier
		// rounds
		for (int i = 0; i < numberOfSantas; i++) {
			solver.post(IntConstraintFactory.element(santaDistance[i], rounds[i], santas[i], 0,
			        CONSTRAINT_SORT_TYPE_DETECT));
		}

		// Create constraint that Secret Santa is non-reflexive (not assigned to
		// one's self)
		for (int i = 0; i < numberOfSantas; i++) {
			solver.post(IntConstraintFactory.arithm(santas[i], "!=", VariableFactory.fixed(i, solver)));
		}

		// Creates "not equal to" constraints by iterating the matrix, reading
		// the current value (which represents the prior
		// year in which a secret santa assignment occurred) and checking to see
		// if it is less than the limit imposed.
		for (int i = 0; i < numberOfSantas; i++) {
			for (int j = 0; j < numberOfSantas; j++) {
				if (rounds[i][j] <= Constants.PREVIOUS_YEAR_LIMIT) {
					solver.post(IntConstraintFactory.arithm(santas[i], "!=", VariableFactory.fixed(j, solver)));
				}
			}
		}

		// Sets a type of "policy constraint" that says basically try to
		// maximize the values within the domain ranges stored in santaDistance
		// using the distanceToMaximize IntVar domain integers In this case, it
		// would try to set them all to maximize (value=9) which means they've
		// never been assigned
		solver.post(IntConstraintFactory.sum(santaDistance, distanceToMaximize));
	}

	@Override
	public void createSolver() {
		solver = new Solver(SOLVER_SECRET_SANTA);
	}

	@Override
	public void configureSearch() {
	}

	@Override
	public void solve() {
		solver.findOptimalSolution(ResolutionPolicy.MAXIMIZE, distanceToMaximize);
	}

	@Override
	public void prettyOut() {
		if (solver.isFeasible() == ESat.TRUE) {
			int numSolutions = 0;
			Participant secretSanta, assignedTo;

			do {
				log.debug("santas: {}", Arrays.asList(santas));

				for (int i = 0; i < numberOfSantas; i++) {
					int v = santas[i].getValue();
					secretSanta = PLAYER_LIST.get(i);
					assignedTo = PLAYER_LIST.get(v);
					this.assignments.put(secretSanta, assignedTo);
					log.debug(String.format("%d (%-6s) is a Secret Santa of %d (%-6s) (distance: %d)", i, secretSanta,
					        v, assignedTo, santaDistance[i].getValue()));
				}
				numSolutions++;
			} while (solver.nextSolution() == Boolean.TRUE);

			log.debug("There were {} solutions.", numSolutions);

		} else {
			log.debug("Problem is NOT feasible.");
		}
	}

	public int[][] getRounds() {
		return rounds;
	}

	public void setRounds(int[][] rounds) {
		this.rounds = rounds;
		setNumberOfSantas(rounds.length);
		setMaximize(numberOfSantas + 1);
	}

	private void setMaximize(int maximize) {
		this.maximize = maximize;
	}

	private void setNumberOfSantas(int numberOfSantas) {
		this.numberOfSantas = numberOfSantas;
	}

	public Map<Participant, Participant> getAssignments() {
		return this.assignments;
	}

	/**
	 * Creates secret santa assignments by leveraging the choco3 api.
	 * 
	 * @param participants
	 * @return a <tt>Map</tt> representing the assignments where the key is the
	 *         secret santa and the value is the participant they are assigned
	 *         to.
	 */
	@Override
    public Map<Participant, Participant> createAssignments() {
		int[][] matrix = dao.getParticipantMatrix();
		SmarterSecretSanta smartSanta = new SmarterSecretSanta();
		smartSanta.setRounds(matrix);
		smartSanta.execute(new String[] {});
		return smartSanta.getAssignments();
    }

	/**
	 * TODO: Didn't get to this so just return the other
	 */
	@Override
    public Map<Participant, Participant> createAssignments(List<Participant> participants) {
	    return this.createAssignments();
    }

}
