/**
 * 
 */
package org.igrok.tools.router;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg
 *
 */
public abstract class Router {

	private List<Route> routes;

	/**
	 * 
	 */
	public Router() {
		this.routes = new ArrayList<Route>();
	}

	public final void addRoute(Route route) {
		if (route != null) {
			this.routes.add(route);
		}
	}

	public final void scanRoutes(Class<?> cls) {
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if(method.isAnnotationPresent(RouteMarker.class)) {
				RouteMarker annotation = method.getAnnotation(RouteMarker.class);
				Route route = new Route(annotation.root(),annotation.action());
				route.assignAction(method);
				this.routes.add(route);
			}
		}
	}

	public final void removeRoute(Route route) {
		if (route != null) {
			this.routes.remove(route);
		}
	}

	public final void removeActions(String action) {
		List<Route> routesToDelete = new ArrayList<Route>();
		for (Route route : this.routes) {
			if (route.hasAction(action)) {
				routesToDelete.add(route);
			}
		}
		for (Route route : routesToDelete) {
			this.routes.remove(route);
		}
	}

	public final void removeRoot(String root) {
		List<Route> routesToDelete = new ArrayList<Route>();
		for (Route route : this.routes) {
			if (route.hasRoot(root)) {
				routesToDelete.add(route);
			}
		}
		for (Route route : routesToDelete) {
			this.routes.remove(route);
		}
	}

	public final Route retrieveRoute(String root, String action) {
		for (Route route : this.routes) {
			if (route.hasRoot(root) && route.hasAction(action)) {
				return route;
			}
		}
		return null;
	}
}
