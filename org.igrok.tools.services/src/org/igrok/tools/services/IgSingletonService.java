/**
 * 
 */
package org.igrok.tools.services;

/**
 * @author oleg
 *
 */
public class IgSingletonService extends IgService {

	private Object instance;
	
	/**
	 * Constructs service provider with given type and instance
	 * @param type type to associate with
	 * @param instance instance of type to use as singleton
	 */
	public IgSingletonService(Class<?> type, Object instance) {
		super(type);
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
