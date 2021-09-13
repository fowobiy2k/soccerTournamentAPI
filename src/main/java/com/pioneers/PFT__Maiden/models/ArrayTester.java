package com.pioneers.PFT__Maiden.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "tbl_array_tester")
public class ArrayTester {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private long id;
	private String[] mvp_list;
	private String[] teamList;
	
		
	public ArrayTester() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String[] getMvp_list() {
		return mvp_list;
	}


	public void setMvp_list(String[] mvp_list) {
		this.mvp_list = mvp_list;
	}


	public String[] getTeamList() {
		return teamList;
	}


	public void setTeamList(String[] teamList) {
		this.teamList = teamList;
	}
	
	
}
