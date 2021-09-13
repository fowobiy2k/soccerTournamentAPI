package com.pioneers.PFT__Maiden.models;

import javax.persistence.*;

@Entity
@Table(name= "tbl_events")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue(value= "event")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private long id;
	private String time, eventType;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "subject")
	private Player subject;
	
	public Event() {
		
	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Player getSubject() {
		return subject;
	}
	public void setSubject(Player subject) {
		this.subject = subject;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
}
