/**
 * 
 */
package org.igrok.tools.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import org.igrok.tools.services.ServiceCollection;
import org.igrok.tools.services.ServiceNotFoundException;

/**
 * @author oleg
 *
 */
public class ActionInspector {

	public static Object ConstructController(Constructor<?> constructor, ServiceCollection services)
			throws RouterException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ServiceNotFoundException {
		Parameter[] params = constructor.getParameters();
		if (params.length == 0) {
			try {
				return constructor.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new RouterException(e.getLocalizedMessage());
			}
		} else {
			return constructor.newInstance(services.fillParams(params));
		}
	}

}
