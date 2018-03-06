/**
 * 
 */
package org.igrok.tools.router;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 * Annotation to mark suitable route
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public @interface RouteMarker {
	String root();
	String action();
}
