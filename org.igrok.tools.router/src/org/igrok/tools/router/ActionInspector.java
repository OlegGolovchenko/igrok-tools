/**
 * 
 */
package org.igrok.tools.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author oleg
 *
 */
public class ActionInspector {

	public static Object ConstructController(Constructor constructor) throws RouterException {
		Class<?>[] params = constructor.getParameterTypes();
		if (params.length == 0) {
			try {
				return constructor.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new RouterException(e.getLocalizedMessage());
			}
		} else {
			
		}
	}

}
