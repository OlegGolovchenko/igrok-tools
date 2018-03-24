/**
 * 
 */
package org.igrok.server.web;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author oleg
 *
 */
public class WebServer {

	private static boolean running = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 5000;
		try (ServerSocket server = new ServerSocket(port);) {
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
			ExecutorService clientHandlers = Executors.newFixedThreadPool(4);
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
	}

}
