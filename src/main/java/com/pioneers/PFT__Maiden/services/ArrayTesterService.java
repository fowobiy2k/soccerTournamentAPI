package com.pioneers.PFT__Maiden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.ArrayTester;
import com.pioneers.PFT__Maiden.repo.ArrayTesterRepository;

@Service
public class ArrayTesterService {
	
	@Autowired
	private ArrayTesterRepository repository;
	
	public void createTester(ArrayTester arrayTester) {
		repository.save(arrayTester);
	}
	
	public ArrayTester getTesterById(long id) {
		return repository.findById(id).get();
	}
	
	public List<ArrayTester> getAll() {
		return repository.findAll();
	}

}
