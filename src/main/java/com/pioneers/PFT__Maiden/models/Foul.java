package com.pioneers.PFT__Maiden.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("foul")
public class Foul extends Event {
		
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "foul_by")
	private Player concededBy;
	
	private String cardShown;
	private String setPieceAwarded;
	public Player getConcededBy() {
		return concededBy;
	}
	public void setConcededBy(Player concededBy) {
		this.concededBy = concededBy;
	}
	public String getCardShown() {
		return cardShown;
	}
	public void setCardShown(String cardShown) {
		this.cardShown = cardShown;
	}
	public String getSetPieceAwarded() {
		return setPieceAwarded;
	}
	public void setSetPieceAwarded(String setPieceAwarded) {
		this.setPieceAwarded = setPieceAwarded;
	}
	
}
