package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
