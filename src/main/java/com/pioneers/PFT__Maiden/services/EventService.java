package com.pioneers.PFT__Maiden.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Event;
import com.pioneers.PFT__Maiden.repo.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public void createEvent(Event event) {
		repository.save(event);
	}

	public Event getEventById(long id) {
		return repository.findById(id).get();
	}

	public List<Event> getAllEvents() {
		return repository.findAll();
	}
	
	public List<Event> getEventsByType(String type) {
		return repository.findByEventType(type);
	}

//	public List<Event> findByType(String type) {
//
//		List<Event> list = repository.findAll();
//		List<Event> output = new ArrayList<Event>();
//
//		for (Event event : list) {
//			if (type.equalsIgnoreCase(event.getType())) {
//				output.add(event);
//			}
//		}
//
//		return output;
//	}

	public List<Event> findBySubject(Long id) {

		List<Event> list = repository.findAll();
		List<Event> output = new ArrayList<Event>();

		for (Event event : list) {
			if (id == event.getSubject().getId()) {
				output.add(event);
			}
		}

		return output;
	}

}
