/**
 * 
 */
package org.igrok.tools.services;

/**
 * @author oleg
 *
 */
public abstract class IgService {
	protected Class<?> type;
	/**
	 * 
	 */
	public IgService(Class<?> type) {
		if(type == null) {
			throw new IllegalArgumentException("Type can mot be null");
		}
		this.type = type;
	}
	
	public abstract Object getInstance();
	
	public Class<?> getType(){
		return this.type;
	}
}
