/**
 * 
 */
package org.igrok.tools.protocols.http;

import java.util.ArrayList;
import java.util.List;

import org.igrok.tools.protocols.Protocol;

/**
 * @author oleg
 *
 */
public class HttpProtocol extends Protocol {
	
	private static List<String> implementedMethods = new ArrayList<String>();
	static {
		implementedMethods.add("GET");
	}

	public static boolean isImplemented(String method) {
		return implementedMethods.contains(method);
	}
}
