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
	private final static String[] verbs = { "Cut", "Eliminate", "Reduce", "Scrap", "Increase", "Freeze", "Maintain",
			"Protect", "Axe", "Cap", "Overhaul", };

	/** Selection of VERB-NOUNs. */
	private final static String[] verbNouns = { "debt", "taxes", "welfare", "tuition fees", "energy bills",
			"financial independence", "education funding", "child benefit", "wages", "funding", };

	/** Selection of ADJECTIVEs. */
	private final static String[] adjectives = { "hard-working", "TB-infected", "publically-funded", "Welsh",
			"Scottish", "skilled", "illiterate", "failing", };

	/** Selection of PERSON-NOUNs. */
	private final static String[] personNouns = { "immigrants", "badgers", "pensioners", "migrants", "MPs",
			"single mothers", "bankers", "home-owners", "children", "foreigners", "16 year-olds",
			"public-sector workers", "students", "asylum seekers", "border staff", "NHS patients", };

	/** Static method to generate a new pledge phrase. */
	public static String generate() {

		final Random r = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append(pick(r, verbs));
		sb.append(" ");
		sb.append(pick(r, verbNouns));
		sb.append(" for ");
		sb.append(pick(r, adjectives));
		sb.append(" ");
		sb.append(pick(r, personNouns));

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
	private static String pick(final Random random, final String[] array) {
		return array[random.nextInt(array.length)];
	}
}
