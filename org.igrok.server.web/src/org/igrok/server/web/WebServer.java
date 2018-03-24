/**
 * 
 */
package org.igrok.server.web;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.igrok.tools.configuration.ConfigurationCollection;
import org.igrok.tools.configuration.ConfigurationException;

/**
 * Web server class
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class WebServer {

	private static boolean running = true;
	private ConfigurationCollection configuration;

	public WebServer() {
		this.configuration = new ConfigurationCollection();
		this.configuration.addEnvironment();

	}

	public void addInitialConfiguration(String file) {
		try {
			this.configuration.loadFromFile(file);
		} catch (ConfigurationException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	/**
	 * @param args
	 */
	public void run() {
		try {
			InetAddress host = InetAddress.getByName(
					this.configuration.getValue("host") != null ? (String) this.configuration.getValue("host")
							: "localhost");
			int port = this.configuration.getValue("port") != null
					? Integer.parseInt((String) this.configuration.getValue("port"))
					: 5000;
			System.out.println("Igrok web server v0.0.1");
			try (ServerSocket server = new ServerSocket(port,0,host);) {
				System.out.println("Listening on " + server.getInetAddress().getHostAddress() + ":" + server.getLocalPort());
				System.out.println("Type 'exit' to close application");
				server.setSoTimeout(1000);
				Thread commandThread = new Thread(() -> {

					try (Scanner command = new Scanner(System.in)) {
						while (running) {
							if (command.hasNextLine()) {
								String currentCommand = command.nextLine();
								System.out.println("Recieved command: " + currentCommand);
								if (currentCommand.matches("exit")) {
									System.out.println("Exiting. Awaiting all process termination.");
									running = false;
								}
							}
						}
					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}
				});
				commandThread.setName("Command Thread");
				commandThread.start();
				int nbThreads = this.configuration.getValue("threads") != null
						? Integer.parseInt((String) this.configuration.getValue("threads"))
						: 4;
				ExecutorService clientHandlers = Executors.newFixedThreadPool(nbThreads);
				while (running) {

					try {
						Socket client = server.accept();
						clientHandlers.execute(() -> {
							if (client != null) {
								try (Scanner reader = new Scanner(client.getInputStream())) {
									try (PrintWriter writer = new PrintWriter(client.getOutputStream())) {
										while (running) {
											
										}
									} catch (Exception e) {
										System.err.println("error " + e.getLocalizedMessage());
									}
								} catch (Exception e) {
									System.err.println("error " + e.getLocalizedMessage());
								}
								try {
									client.close();
								} catch (IOException e) {
									System.err.println(e.getLocalizedMessage());
								}
							}
						});
					} catch (InterruptedIOException iioe) {

					} catch (Exception e) {
						System.err.println(e.getLocalizedMessage());
					}

				}
				clientHandlers.shutdown();
				System.out.print("Awaiting termination of remaining threads");
				while (!clientHandlers.isTerminated()) {
					System.out.print(".");
				}
				System.out.println("done");
			} catch (

			Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
			System.out.println("All processes terminated. Exit completed.");
		} catch (UnknownHostException uhe) {
			System.err.println(uhe.getLocalizedMessage());
		}

	}
}
