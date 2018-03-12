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
		this.type = type;
	}
	
	public abstract Object getInstance();
	
	public Class<?> getType(){
		return this.type;
	}
}
