/**
 * 
 */
package org.igrok.tools.tests;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;

import org.igrok.tools.services.IgInstanceService;
import org.junit.Test;

/**
 * @author oleg
 *
 */
public class IgInstanceServiceTests {
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIllegalArgumentExceptionWhenTypeIsNull() throws InvalidObjectException {
		new IgInstanceService(null);
	}
	
	@Test
	public void constructorShouldSucceeedWhenCorrectArgumentsGiven() throws InvalidObjectException {
		IgInstanceService service = new IgInstanceService(Object.class); 
		assertNotNull(service);
	}
	
	@Test
	public void getTypeShouldSucceedWhenTypeIsNotNull() throws InvalidObjectException {
		IgInstanceService service  = new IgInstanceService(Object.class);
		assertEquals(service.getType(),Object.class);
	}
	
	@Test
	public void getInstanceShouldReturnGivenInstance() throws InvalidObjectException {
		IgInstanceService service  = new IgInstanceService(Object.class);
		assertNotNull(service.getClass());
	}
	
}
