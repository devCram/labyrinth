/******************************************************************************
 *  Compilation:  javac Server.java
 *  Execution:    java Server
 *  Dependencies: In.java Out.java Connection.java ConnectionListener.java
 *
 *  Creates a server to listen for incoming connection requests on 
 *  port 4444.
 *
 *  % java Server
 *
 *  Remark
 *  -------
 *    - Use Vector instead of ArrayList since it's synchronized.
 *  
 ******************************************************************************/

package network;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Vector;

public class Server {
    public static void main(String[] args) throws Exception {
        Vector<Connection> connections        = new Vector<Connection>();
        ServerSocket serverSocket             = new ServerSocket(Integer.parseInt(args[0]));
        ConnectionListener connectionListener = new ConnectionListener(connections);

        //thread that broadcasts messages to clients
        connectionListener.start();

        System.err.println("Gameserver started");

        while(true) {
            //wait for next client connection request
            Socket clientSocket = serverSocket.accept();
            System.err.println("Created socket with client");

            //listen to client in a separate thread
            Connection connection = new Connection(clientSocket);
            connections.add(connection);
            connection.start();
        }
    }
}