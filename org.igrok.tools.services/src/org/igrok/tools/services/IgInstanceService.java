/**
 * 
 */
package org.igrok.tools.services;

import java.lang.reflect.InvocationTargetException;

/**
 * @author oleg
 *
 */
public class IgInstanceService extends IgService {

	private Object params;

	/**
	 * @param type
	 */
	public IgInstanceService(Class<?> type, Object... params) {
		super(type);
		this.params = params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.igrok.tools.services.IgService#getInstance()
	 */
	@Override
	public Object getInstance() {
		Object instance = null;
		try {
			instance = this.type.getConstructors()[0].newInstance(params);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			System.err.println(e.getLocalizedMessage());
		}
		return instance;
	}

}
