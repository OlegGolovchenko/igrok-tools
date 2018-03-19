/**
 * 
 */
package org.igrok.tools.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
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
	 * @throws IOException if file not found or inaccessible, or if any IO error occured during read
	 * @throws ParseException if json file format can not be parsed
	 */
	public void loadFromFile(String path) throws IOException, ParseException {
		Path filePath = Paths.get(path);
		if (filePath == null) {
			throw new FileNotFoundException("specified file does not exists");
		}
		if (!Files.isReadable(filePath)) {
			throw new IOException("file is not readeable");
		}
		List<String> data = Files.readAllLines(filePath);
		String jsonString = "";
		for (String dataLine : data) {
			jsonString += dataLine + "\r\n";
		}
		JSONParser parser = new JSONParser();
		Object dataObject = parser.parse(jsonString);
		JSONObject object = (JSONObject)dataObject;
		object.forEach((key,value)->{
			this.configuration.put(key, value);
		});
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
