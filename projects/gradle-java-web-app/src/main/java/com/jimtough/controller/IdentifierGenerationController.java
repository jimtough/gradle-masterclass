package com.jimtough.controller;

import javax.inject.Inject;

import com.jimtough.random.GeneratedRandomIdentifier;
import com.jimtough.random.RandomGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Example mvc controller class, generates a new number and returns it to the
 * view layer
 * <p>
 * - Also shows the various logging api.
 * i.e. "Apache Commons Logging (JCL)", Log4j and  "Simple Logging Facade" *
 * <p>
 * <p>
 * See reference documents:
 * http://www.slf4j.org/index.html
 * https://commons.apache.org/proper/commons-logging/guide.html
 * https://logging.apache.org/log4j/1.2/
 */
@Controller
public class IdentifierGenerationController {

	// Using the slf4j api directly - remove comments from this code only after SLF4J dependencies are added, see tutorials. 
	//private static final Logger logger = LoggerFactory.getLogger(IdentifierGenerationController.class);

	// using the log4j api, jar provided by our specific dependency
	private static final Logger logger = Logger.getLogger("com.jimtough.controller.IdentifierGenerationController");

	// using the JCL api, jar provided by transient dependency via spring-core
	private Log log = LogFactory.getLog(IdentifierGenerationController.class);

	@Inject
	private RandomGenerator idGenerator;

	@RequestMapping("/new")
	public String showNewIdentifier(Model model) {
		// Only as an example to show different logging dependencies
		// included in our project.
		// Better, is to use only one and its good practice to use slf4j.

		// debug from JCL
		log.debug("logging is using Class: " + log.getClass().getCanonicalName());

		// debug from log4j
		logger.debug("logging is using CLass: " + logger.getClass().getCanonicalName());

		final GeneratedRandomIdentifier anIdentifier = idGenerator.generate();

		model.addAttribute("creator", anIdentifier.createdBy());
		model.addAttribute("id", anIdentifier.identifier());

		return "newId";

	}

}
