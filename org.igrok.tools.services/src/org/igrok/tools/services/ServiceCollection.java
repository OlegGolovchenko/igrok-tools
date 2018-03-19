/**
 * 
 */
package org.igrok.tools.services;

import java.io.InvalidObjectException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.igrok.tools.configuration.ConfigurationCollection;

/**
 * @author oleg
 *
 */
public class ServiceCollection {

	private List<IgService> services;
	private ConfigurationCollection configuration;

	/**
	 * 
	 */
	public ServiceCollection() {
		services = new ArrayList<IgService>();
	}

	public void addConfiguration(ConfigurationCollection configuration) {
		this.configuration = configuration;
	}

	public void addSingleton(Class<?> type, Object instance) throws InvalidObjectException {
		this.services.add(new IgSingletonService(type, instance));
	}

	public void addSingletonWithServices(Class<?> type, int constructorId)
			throws InvalidObjectException, ServiceNotFoundException {
		Constructor<?> ctr = type.getConstructors()[constructorId];
		Parameter[] paramsCtr = ctr.getParameters();
		Object[] params = new Object[paramsCtr.length];
		for (int i = 0; i < paramsCtr.length; i++) {
			for (IgService service : this.services) {
				if (service.getType() == paramsCtr[i].getType()) {
					params[i] = service.getInstance();
					if (params[i] == null) {
						params[i] = this.configuration.getValue(paramsCtr[i].getName());
					}
				}
			}
			if (params[i] == null) {
				throw new ServiceNotFoundException("Service for parameter " + paramsCtr[i].getName() + " of type "
						+ paramsCtr[i].getType() + " not found");
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

	public void addInstance(Class<?> type, Object... params) {
		this.services.add(new IgInstanceService(type, params));
	}

	public void addInstanceWithServices(Class<?> type, int constructorId) throws ServiceNotFoundException {
		Constructor<?> ctr = type.getConstructors()[constructorId];
		Parameter[] paramsCtr = ctr.getParameters();
		Object[] params = new Object[paramsCtr.length];
		for (int i = 0; i < paramsCtr.length; i++) {
			for (IgService service : this.services) {
				if (service.getType() == paramsCtr[i].getType()) {
					params[i] = service.getInstance();
					if (params[i] == null) {
						params[i] = this.configuration.getValue(paramsCtr[i].getName());
					}
				}
			}
			if (params[i] == null) {
				throw new ServiceNotFoundException("Service for parameter " + paramsCtr[i].getName() + " of type "
						+ paramsCtr[i].getType() + " not found");
			}
		}
		this.addInstance(type, params);
	}
}
