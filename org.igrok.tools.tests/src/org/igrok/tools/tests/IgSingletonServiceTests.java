/**
 * 
 */
package org.igrok.tools.tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InvalidObjectException;
import java.net.Socket;

import org.igrok.tools.services.IgSingletonService;

/**
 * @author oleg
 *
 */
class IgSingletonServiceTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIllegalArgumentExceptionWhenTypeIsNull() throws InvalidObjectException {
		new IgSingletonService(null,new Object());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowIllegalArgumentExceptionWhenInstanceIsNull() throws InvalidObjectException {
		new IgSingletonService(Object.class,null);
	}
	
	@Test(expected = InvalidObjectException.class)
	public void constructorShouldThrowInvalidObjectTypeExceptionWhenInstanceIsNotOfGivenType() throws InvalidObjectException {
		new IgSingletonService(Socket.class,new Object());
	}
	
	@Test
	public void constructorShouldSucceeedWhenCorrectArgumentsGiven() throws InvalidObjectException {
		IgSingletonService service = new IgSingletonService(Object.class,new Object()); 
		assertNotNull(service);
	}
	
	@Test
	public void getTypeShouldSucceedWhenTypeIsNotNull() throws InvalidObjectException {
		Object object = new Object();
		IgSingletonService service  = new IgSingletonService(Object.class, object);
		assertEquals(service.getType(),Object.class);
	}
	
	@Test
	public void getInstanceShouldReturnGivenInstance() throws InvalidObjectException {
		Object object = new Object();
		IgSingletonService service  = new IgSingletonService(Object.class, object);
		assertEquals(service.getInstance(),object);
	}
	
	
}
