/**
 * 
 */
package org.igrok.tools.services;

import java.io.InvalidObjectException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg
 *
 */
public class ServiceCollection {

	private List<IgService> services;

	/**
	 * 
	 */
	public ServiceCollection() {
		services = new ArrayList<IgService>();
	}

	public void addSingleton(Class<?> type, Object instance) throws InvalidObjectException {
		this.services.add(new IgSingletonService(type, instance));
	}

	public void addSingletonWithServices(Class<?> type, int constructorId) throws InvalidObjectException, ServiceNotFoundException {
		Constructor<?> ctr = type.getConstructors()[constructorId];
		Class<?>[] paramTypes = ctr.getParameterTypes();
		Object[] params = new Object[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			for (IgService service : this.services) {
				if (service.getType() == paramTypes[i]) {
					params[i] = service.getInstance();
				}
			}
			if(params[i]==null) {
				throw new ServiceNotFoundException("Service "+paramTypes[i]+" not found");
			}
		}
		try {
			Object instance = ctr.newInstance(params);
			this.addSingleton(type, instance);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	public void addInstance(Class<?>type,Object...params) {
		this.services.add(new IgInstanceService(type,params));
	}
	
	public void addInstanceWithServices(Class<?>type, int constructorId) {
		Constructor<?> ctr = type.getConstructors()[constructorId];
		Class<?>[] paramTypes = ctr.getParameterTypes();
		Object[] params = new Object[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			for (IgService service : this.services) {
				if (service.getType() == paramTypes[i]) {
					params[i] = service.getInstance();
				}
			}
		}
		this.addInstance(type, params);
	}
}
