/**
 * 
 */
package org.igrok.tools.tests;

import static org.junit.Assert.*;

import java.io.InvalidObjectException;
import java.net.Socket;

import org.igrok.tools.services.ServiceCollection;
import org.igrok.tools.services.ServiceNotFoundException;
import org.junit.Test;

/**
 * @author oleg
 *
 */
public class ServiceCollectionTests {

	
	@Test
	public void constructorShouldSucceed() {
		assertNotNull(new ServiceCollection());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addSingletonShouldThrowIllegalArgumentExceptionWhenTypeIsNull() throws InvalidObjectException {
		ServiceCollection collection = new ServiceCollection();
		collection.addSingleton(null, new Object());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addSingletonShouldThrowIllegalArgumentExceptionWhenInstanceIsNull() throws InvalidObjectException {
		ServiceCollection collection = new ServiceCollection();
		collection.addSingleton(Object.class, null);
	}
	
	@Test(expected = InvalidObjectException.class)
	public void addSingletonShouldThrowInvalidObjectTypeExceptionWhenInstanceIsNotOfGivenType() throws InvalidObjectException {
		ServiceCollection collection = new ServiceCollection();
		collection.addSingleton(Socket.class, new Object());
	}
	
	@Test
	public void addSingletonShouldSucceeedWhenCorrectArgumentsGiven() throws InvalidObjectException {
		ServiceCollection collection = new ServiceCollection();
		collection.addSingleton(Object.class, new Object());
	}
	
	
	@Test(expected = ServiceNotFoundException.class)
	public void addSingletonWithServicesShouldThrowServiceNotFoundExceptionWhenNeededServiceIsNotPresent() throws InvalidObjectException, ServiceNotFoundException {
		ServiceCollection collection = new ServiceCollection();
		collection.addSingletonWithServices(TestClass.class, 0);
	}
	
	
	@Test
	public void addSingletonWithServicesShouldSucceed() throws InvalidObjectException, ServiceNotFoundException {
		ServiceCollection collection = new ServiceCollection();
		collection.addSingleton(TestClass2.class, new TestClass2());
		collection.addSingletonWithServices(TestClass.class, 0);
	}

	/**
	 * Test method for {@link org.igrok.tools.services.ServiceCollection#addInstance(java.lang.Class, java.lang.Object[])}.
	 */
	@Test
	public void testAddInstance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.igrok.tools.services.ServiceCollection#addInstanceWithServices(java.lang.Class, int)}.
	 */
	@Test
	public void testAddInstanceWithServices() {
		fail("Not yet implemented");
	}

}
