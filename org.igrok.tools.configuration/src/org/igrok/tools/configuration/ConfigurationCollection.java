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
 * @author oleg
 *
 */
public class ConfigurationCollection {

	private JSONObject configuration;

	/**
	 * 
	 */
	public ConfigurationCollection() {
	}

	/**
	 * Loads and replaces configuration values from json file
	 * 
	 * @param path
	 * @throws IOException
	 * @throws ParseException
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
		this.configuration = (JSONObject) dataObject;
	}

}
