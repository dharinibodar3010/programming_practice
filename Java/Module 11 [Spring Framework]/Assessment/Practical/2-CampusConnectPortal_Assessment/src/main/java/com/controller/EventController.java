package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Event;
import com.repository.EventRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EventController {

	@Autowired
	EventRepository repo;

	@GetMapping("/dashboard")
	public String dashboard(
			Model model,
			HttpSession session) {

		if(session.getAttribute("student") == null) {

			return "redirect:/login";
		}

		List<Event> list = repo.findAll();

		model.addAttribute("events", list);

		return "dashboard";
	}

	@GetMapping("/eventForm")
	public String eventForm(HttpSession session) {

		if(session.getAttribute("student") == null) {

			return "redirect:/login";
		}

		return "event-form";
	}

	@PostMapping("/saveEvent")
	public String saveEvent(
			@Valid Event event,
			BindingResult result,
			HttpSession session) {

		if(session.getAttribute("student") == null) {

			return "redirect:/login";
		}

		if(result.hasErrors()) {

			return "event-form";
		}

		repo.save(event);

		return "redirect:/dashboard";
	}
}