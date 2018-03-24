/**
 * 
 */
package org.igrok.tools.protocols.http;

/**
 * @author oleg
 *
 */
public abstract class HttpMessage {
	private String httpVer;
	private String method;
	private String uri;
	
	public static final String GET = "GET";
	public static final String OPTIONS = "OPTIONS";
	public static final String HEAD = "HEAD";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static final String DELETE = "DELETE";
	public static final String TRACE = "TRACE";
	public static final String CONNECT = "CONNECT";

	/**
	 * 
	 */
	public HttpMessage() {
	}

	public void addRequestLine(String line) throws HttpProtocolException {
		String[] parts = line.split(" ");
		if (parts.length == 3) {
			this.method = parts[0];
			this.uri = parts[1];
			this.httpVer = parts[3].split("/")[1];
		} else {
			throw new HttpProtocolException("Request line should contain three parts");
		}
	}
}
