package com.pioneers.PFT__Maiden.utility.schedule;

public class Schedule {
	
	private FirstRound firstRound;
//	private QuarterFinals quarterFinals;
	private SemiFinals semiFinals;
	private FinalMatch finals;

	public FirstRound getFirstRound() {
		return firstRound;
	}

	public void setFirstRound(FirstRound firstRound) {
		this.firstRound = firstRound;
	}

//	public QuarterFinals getQuarterFinals() {
//		return quarterFinals;
//	}
//
//	public void setQuarterFinals(QuarterFinals quarterFinals) {
//		this.quarterFinals = quarterFinals;
//	}

	public SemiFinals getSemiFinals() {
		return semiFinals;
	}

	public void setSemiFinals(SemiFinals semiFinals) {
		this.semiFinals = semiFinals;
	}

	public FinalMatch getFinals() {
		return finals;
	}

	public void setFinals(FinalMatch finals) {
		this.finals = finals;
	}
	
}
