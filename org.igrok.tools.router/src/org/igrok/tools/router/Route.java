/**
 * 
 */
package org.igrok.tools.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Route representation for web server
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class Route {

	private String root;
	private String action;
	private boolean isStatic;
	private Method actionMethod;

	/**
	 * 
	 */
	public Route(String root, String action) {
		if (root != this.root) {
			this.root = root;
		}
		if (action != this.action) {
			this.action = action;
		}
		this.setStatic(false);
	}

	/**
	 * @return the isStatic
	 */
	public boolean isStatic() {
		return isStatic;
	}
	
	protected void assignAction(Method actionMethod) {
		this.actionMethod = actionMethod;
	}
	
	public boolean hasAction(String action) {
		return this.action == action;
	}
	
	public boolean hasRoot(String root) {
		return this.root == root;
	}

	/**
	 * @param isStatic
	 *            the isStatic to set
	 */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	/**
	 * 
	 * @return
	 * @throws RouterException
	 */
	public RouteResult executeAction() throws RouterException {
		Class<?> cls = this.actionMethod.getDeclaringClass();
		if(cls.getConstructors().length>1) {
			throw new RouterException("Multiple constructors declared for controller.");
		}
		try {
			Object controller = cls.getConstructors()[0].newInstance();
			Object result = this.actionMethod.invoke(controller);
			if(!(result instanceof RouteResult)) {
				throw new RouterException("Action method result must be of RouteResult type");
			}
			return (RouteResult)result;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			throw new RouterException(e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object route) {
		boolean equal = false;
		equal = route instanceof Route;
		if(equal) {
			Route inst = (Route) route;
			equal = this.hasRoot(inst.root) && this.hasAction(inst.action) && inst.isStatic == this.isStatic;
		}
		return equal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.root, this.action, this.isStatic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "route[" + this.root + "" + this.action + "]";
	}
}