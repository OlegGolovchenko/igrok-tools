/**
 * 
 */
package org.igrok.tools.services;

/**
 * Exception to be thrown when service is not found during automated creation of singleton or instance
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class ServiceNotFoundException extends Exception {

	/**
	 * Creates exception with given message
	 * @param message message to hold
	 */
	public ServiceNotFoundException(String message) {
		super(message);
	}

}
