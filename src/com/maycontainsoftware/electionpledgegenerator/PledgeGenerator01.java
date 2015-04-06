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
			"Generate", "Increase", "Maintain", "Overhaul", "Protect", "Reduce", "Reform", "Ringfence", "Scrap",

	};

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
