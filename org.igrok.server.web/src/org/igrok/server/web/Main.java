/**
 * 
 */
package org.igrok.server.web;

/**
 * @author oleg
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.addInitialConfiguration("configuration.json");
		server.run();
	}

}
