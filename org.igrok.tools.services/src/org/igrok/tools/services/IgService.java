/**
 * 
 */
package org.igrok.tools.services;

/**
 * @author oleg
 *
 */
abstract class IgService {
	protected Class<?> type;
	/**
	 * 
	 */
	public IgService(Class<?> type) {
		if(type == null) {
			throw new IllegalArgumentException("Type can not be null");
		}
		this.type = type;
	}
	
	public abstract Object getInstance();
	
	public Class<?> getType(){
		return this.type;
	}
}
