package com.maycontainsoftware.electionpledgegenerator;

import java.util.Random;

public class PledgeGenerator01 {

	// VERB ITEM-NOUN for ADJ PERSON-NOUN

	private final static String[] verbs = { "Cut", "Eliminate", "Reduce", "Scrap", "Increase", "Freeze", "Maintain",
			"Protect", "Axe", "Cap", "Overhaul", };

	private final static String[] itemNouns = { "debt", "taxes", "welfare", "tuition fees", "energy bills",
			"financial independence", "education funding", "child benefit", "wages", "funding", };

	private final static String[] adj = { "hard-working", "TB-infected", "publically-funded", "Welsh", "Scottish",
			"skilled", "illiterate", "failing", };

	private final static String[] personNouns = { "immigrants", "badgers", "pensioners", "migrants", "MPs",
			"single mothers", "bankers", "home-owners", "children", "foreigners", "16 year-olds",
			"public-sector workers", "students", "asylum seekers", "border staff", "NHS patients", };

	public static String generate() {

		final Random r = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append(pick(r, verbs));
		sb.append(" ");
		sb.append(pick(r, itemNouns));
		sb.append(" for ");
		sb.append(pick(r, adj));
		sb.append(" ");
		sb.append(pick(r, personNouns));

		return sb.toString();
	}

	private static String pick(final Random r, final String[] array) {
		return array[r.nextInt(array.length)];
	}
}
