package exercise6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * The server read word and language from client
 * The server state the translation
 * 
 * @author norbalqish
 *
 */
public class ServerTranslation{
	
	/**
	 * Main entry point to the server side application
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		
		ServerSocket serverSocket = null;
		
		try {
			// Bind Serversocket to port 2564
			int portNo = 2564;
			serverSocket = new ServerSocket (portNo);
			
			
					while (true) {
						// Accept client request for connection
						Socket clientSocket = serverSocket.accept ();
						System.out.println("connecting...");
						
						// Create stream to read data on the client
						BufferedReader bufferedReader = new BufferedReader (
								new InputStreamReader(clientSocket.getInputStream()));
						
						PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
						
						// Receive input from client
						
						String clientInput = bufferedReader.readLine();
						String targetedLanguage = bufferedReader.readLine();
						
						
						//Print input from client
						System.out.println("Text to be translated : " + clientInput);
						System.out.println("Targeted language : " +targetedLanguage);
						
						//method call
						String translation = textTranslator(clientInput, targetedLanguage);
						
						
						printWriter.println(translation);
						System.out.println(translation);
						
				//close reader and writer
						bufferedReader.close();
						printWriter.close();
						// Close socket
						clientSocket.close();
					}
		
					
		} catch (IOException ioe) {
			
			if (serverSocket != null)
				serverSocket.close ();
			
			ioe.printStackTrace();
			}
		}

/**
 * This method translate the client input
 * @param inputClient
 * @param targetedLanguage
 * @return
 */
	public static String textTranslator(String inputClient, String targetedLanguage) {
	
		String translatedText;
	
		if(targetedLanguage.equals("Bahasa Melayu")) {
		
			if(inputClient.equals("What’s up?")) {
				translatedText = "Translated Text : Ada apa?";
			}
			else {
			translatedText = "Text cannot be translated";
			}		
		}
		else if(targetedLanguage.equals("Arabic")) {
			if(inputClient.equals("What’s up?")) {
				translatedText = "Translated Text : ما أخبارك؟ ";
			}
			else {
				translatedText = "Text cannot be translated";
			}	
		}
		else if(targetedLanguage.equals("Korea")) {
			if(inputClient.equals("What’s up?")) {
				translatedText = "Translated Text : 뭐야?";
			}
			else {
				translatedText = "Text cannot be translated";
			}	
		}
		else {
			translatedText = "Invalid language";
		}
	
		return translatedText;
	}

}
