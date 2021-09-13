package com.pioneers.PFT__Maiden.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("goal")
public class Goal extends Event {
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "assist_by")
	private Player assistBy;

	public Player getAssistBy() {
		return assistBy;
	}

	public void setAssistBy(Player assistBy) {
		this.assistBy = assistBy;
	}

}
