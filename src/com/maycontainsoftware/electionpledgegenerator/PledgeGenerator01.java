package com.maycontainsoftware.electionpledgegenerator;

import java.util.Random;

/**
 * The first pledge generator. This generator knows a single sentence structure, knows lists of words that fit into the
 * specific places in that sentence, and just picks them at random.
 * 
 * @author Charlie
 */
public class PledgeGenerator01 {

	// The chosen sentence structure is: [I/we pledge to] VERB-PHRASE for NOUN-PHRASE
	// Where VERB-PHRASE is constructed as: VERB VERB-NOUN
	// And NOUN-PHRASE is constructed as: ADJECTIVE PERSON-NOUN

	/** Selection of VERBs. */
	private final static String[] verbs = { "Axe", "Boost", "Cap", "Challenge", "Cut", "Defend", "Eliminate", "Freeze",
			"Generate", "Increase", "Maintain", "Overhaul", "Protect", "Reduce", "Reform", "Ringfence", "Scrap", };

	/** Selection of VERB-NOUNs. */
	private final static String[] verbNouns = { "child benefit", "council tax", "debt", "education funding",
			"energy bills", "financial independence", "funding", "health inspections", "income tax", "tax credits",
			"taxes", "the TV licence", "tuition fees", "voting rights", "wages", "weekly bin collections", "welfare",
			"out-of-work benefits", };

	/** Selection of ADJECTIVEs. */
	private final static String[] adjectives = { "childless", "disadvantaged", "energy-efficient", "English-speaking",
			"failing", "hard-working", "illiterate", "ineligible", "nationally-devolved", "public-sector",
			"publically-funded", "radicalized", "Scottish", "self-employed", "skilled", "TB-infected", "trained",
			"Welsh", };

	/** Selection of PERSON-NOUNs. */
	private final static String[] personNouns = { "16 year-olds", "asylum seekers", "badgers", "bankers",
			"benefits claimants", "border staff", "children", "families", "farmers", "first-time buyers", "fishermen ",
			"foreigners", "healthcare tourists", "higher-rate tax-payers", "home-owners", "immigrants", "landlords",
			"lobbyists", "migrants", "MPs", "NHS patients", "pensioners", "primary school children",
			"public-sector workers", "railway workers", "reality TV stars", "renters", "single mothers", "students",
			"teenagers", };

	/** The last chosen verb, or null if no verb chosen yet. */
	private static String verb;

	/** The last chosen verbNoun, or null if no verbNoun chosen yet. */
	private static String verbNoun;

	/** The last chosen adjective, or null if no adjective chosen yet. */
	private static String adjective;

	/** The last chosen personNoun, or null if no personNoun chosen yet. */
	private static String personNoun;

	/** Static method to generate a new pledge phrase. */
	public static String generate() {

		final Random r = new Random();
		final StringBuffer sb = new StringBuffer();
		sb.append(verb = pick(r, verbs, verb));
		sb.append(" ");
		sb.append(verbNoun = pick(r, verbNouns, verbNoun));
		sb.append(" for ");
		sb.append(adjective = pick(r, adjectives, adjective));
		sb.append(" ");
		sb.append(personNoun = pick(r, personNouns, personNoun));

		return sb.toString();
	}

	/**
	 * Utility method to pick a random item from the specified array.
	 * 
	 * @param random
	 *            The random number generator to use.
	 * @param array
	 *            The array of Strings.
	 * @return A random String from the specified array.
	 */
	private static String pick(final Random random, final String[] array, final String avoid) {
		// Pick a new word that doesn't match the word to avoid
		String word;
		do {
			word = array[random.nextInt(array.length)];
		} while (word == avoid);

		return word;
	}
}
