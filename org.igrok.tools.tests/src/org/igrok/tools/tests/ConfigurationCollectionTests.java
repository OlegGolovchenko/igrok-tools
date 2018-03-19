/**
 * 
 */
package org.igrok.tools.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.igrok.tools.configuration.ConfigurationCollection;
import org.json.simple.parser.ParseException;
import org.junit.Test;


import org.json.simple.parser.ParseException;

/**
 * @author oleg
 *
 */
public class ConfigurationCollectionTests {

	
	@Test
	public void constructorShouldSucceed() {
		assertNotNull(new ConfigurationCollection());
	}
	
	@Test
	public void constructorShouldCreateEmptyCollection() {
		ConfigurationCollection collection = new ConfigurationCollection();
		assertTrue(collection.isEmpty());
	}

	
	@Test(expected = IOException.class)
	public void loadFromFileShouldThrowIOExceptionIfJsonFileNotExists() throws IOException, ParseException{
		ConfigurationCollection collection = new ConfigurationCollection();
		collection.loadFromFile("config_error.json");
	}
	
	
	@Test(expected = ParseException.class)
	public void loadFromFileShouldThrowParseExceptionIfJsonFormatError() throws IOException, ParseException{
		ConfigurationCollection collection = new ConfigurationCollection();
		collection.loadFromFile("test_error.json");
	}
	
	@Test
	public void loadFromFileShouldSucceed() throws IOException, ParseException{
		ConfigurationCollection collection = new ConfigurationCollection();
		collection.loadFromFile("test.json");
		assertFalse(collection.isEmpty());
	}
	
	@Test
	public void addEnvironmentShouldFillCollection() {
		ConfigurationCollection collection = new ConfigurationCollection();
		collection.addEnvironment();
		assertFalse(collection.isEmpty());
	}

	
	@Test
	public void getValueShouldReturnNullIfValueNotExists() {
		ConfigurationCollection collection = new ConfigurationCollection();
		assertNull(collection.getValue("test"));
	}

	
	@Test
	public void getValueShouldNotReturnNullIfValueExists() {
		ConfigurationCollection collection = new ConfigurationCollection();
		collection.addEnvironment();
		assertNotNull(collection.getValue("os.name"));
	}
}
