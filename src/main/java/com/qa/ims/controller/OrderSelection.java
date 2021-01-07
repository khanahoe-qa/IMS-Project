package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum OrderSelection {
	ADD("Add an item to the order"), REMOVE("Remove an items from the order"), 
	REMAKE("Re-enter all details for the order while keeping its ID");
	
	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	OrderSelection(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	/**
	 * Prints out all possible selections
	 */
	public static void printSelection() {
		for (OrderSelection selection : OrderSelection.values()) {
			LOGGER.info(selection.getDescription());
		}
	}
	
	/**
	 * Gets a selection based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Selection type
	 */
	public static OrderSelection getSelection(Utils utils) {
		OrderSelection selection = null;
		do {
			try {
				selection = OrderSelection.valueOf(utils.getString().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		} while (selection == null);
		return selection;
	}
}
