package com.jimtough.controller;

import javax.inject.Inject;

import com.jimtough.random.GeneratedRandomIdentifier;
import com.jimtough.random.RandomGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Example mvc controller class, generates a new number and returns it to the view layer
 */
@Controller
public class IdentifierGenerationController {

	// Using the slf4j API
	private static final Logger LOGGER = LoggerFactory.getLogger(IdentifierGenerationController.class);

	// using the JCL api, jar provided by transient dependency via spring-core
	private Log log = LogFactory.getLog(IdentifierGenerationController.class);

	@Inject
	private RandomGenerator idGenerator;

	@RequestMapping("/new")
	public String showNewIdentifier(Model model) {
		// log to slf4j
		LOGGER.info("logging is using class: [{}]", LOGGER.getClass().getCanonicalName());
		// log to JCL
		log.info("logging is using Class: [" + log.getClass().getCanonicalName() + "]");

		final GeneratedRandomIdentifier anIdentifier = idGenerator.generate();

		model.addAttribute("creator", anIdentifier.createdBy());
		model.addAttribute("id", anIdentifier.identifier());

		return "newId";

	}

}
