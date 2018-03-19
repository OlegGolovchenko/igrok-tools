/**
 * 
 */
package org.igrok.tools.services;

import java.io.InvalidObjectException;

/**
 * @author oleg
 *
 */
class IgSingletonService extends IgService {

	private Object instance;
	
	/**
	 * Constructs service provider with given type and instance
	 * @param type type to associate with
	 * @param instance instance of type to use as singleton
	 * @throws InvalidObjectException 
	 */
	public IgSingletonService(Class<?> type, Object instance) throws InvalidObjectException {
		super(type);
		if(instance == null) {
			throw new IllegalArgumentException("Instance can not be null");
		}
		if(instance.getClass() != type) {
			throw new InvalidObjectException("Instance ia not of give Type");
		}
		this.instance = instance;
	}

	/* (non-Javadoc)
	 * @see org.igrok.tools.services.IgService#getInstance()
	 */
	@Override
	public Object getInstance() {
		return this.instance;
	}

}
