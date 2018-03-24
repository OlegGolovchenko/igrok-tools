/**
 * 
 */
package org.igrok.tools.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Configuration properties collection and manager
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class ConfigurationCollection {

	private JSONObject configuration;

	/**
	 * Constructs new instacne of ConfigurationCollection
	 */
	public ConfigurationCollection() {
		this.configuration = new JSONObject();
	}

	/**
	 * Loads configuration values from json file
	 * @param path location of configuration.json file
	 * @throws ConfigurationException if any eror occures
	 */
	public void loadFromFile(String path) throws ConfigurationException {
		Path filePath = Paths.get(path);
		if (filePath == null) {
			throw new ConfigurationException("specified file does not exists");
		}
		if (!Files.isReadable(filePath)) {
			throw new ConfigurationException("file is not readeable");
		}
		try {
			List<String> data = Files.readAllLines(filePath);
			String jsonString = "";
			for (String dataLine : data) {
				jsonString += dataLine + "\r\n";
			}
			JSONParser parser = new JSONParser();
			
			try {
				Object dataObject = parser.parse(jsonString);
				JSONObject object = (JSONObject)dataObject;
				object.forEach((key,value)->{
					this.configuration.put(key, value);
				});
			} catch (ParseException e) {
				throw new ConfigurationException(e.getLocalizedMessage());
			}
		} catch (IOException e) {
			throw new ConfigurationException(e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * Adds all values from environmental variables
	 */
	public void addEnvironment() {
		System.getProperties().forEach((key, value)->{
			this.configuration.put(key, value);
		});
	}
	
	/**
	 * Gets given configuration value or null if variable not present
	 * @param key name of configuration variable
	 */
	public Object getValue(String key) {
		return this.configuration.getOrDefault(key, null);
	}
	
	/**
	 * Adds or replaces value if exists
	 * @param key key to add or replace value
	 * @param value value to set for key
	 */
	public void addOrReplaceProperty(Object key, Object value) {
		this.configuration.put(key, value);
	}
	
	/**
	 * Checks if ConfigurationCollection is empty
	 * @return true if empty false otherwise
	 */
	public boolean isEmpty() {
		return this.configuration.isEmpty();
	}
	
	/**
	 * gets size of given configurationcollection
	 * @return size of this collection
	 */
	public int getSize() {
		return this.configuration.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.configuration.toString();
	}
}
