/**
 * 
 */
package org.igrok.tools.configuration;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author oleg
 *
 */
public class ConfigurationCollection {

	private Dictionary<String, Object> configuration;
	
	/**
	 * 
	 */
	public ConfigurationCollection() {
		this.configuration = new Hashtable<String, Object>();
	}
	
	public void loadFromFile(String path) throws FileNotFoundException {
		if(Paths.get(path) == null) {
			throw new FileNotFoundException("specified file does not exists");
		}
	}

}
