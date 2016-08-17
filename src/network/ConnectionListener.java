/******************************************************************************
 *  Compilation:  javac ConnectionListener.java
 *  Dependencies: Connection.java
 *
 ******************************************************************************/

package network;

import java.util.Vector;
import java.util.logging.*;

import gameLogic.*;

public class ConnectionListener extends Thread {
    private Vector<Connection> connections;


    private final static Logger LOGGER = Logger.getLogger(ConnectionListener.class.getName());
    private int playerID;
    private boolean mError, mException;
    private String sException;

    //--------------------------------------------------------------------------------
    // create a new board so we can get the player information needed
    private Board board = new Board();

    public ConnectionListener(Vector<Connection> connections) {
        this.connections = connections;

        /*
        TODO
        -board in string umwandeln
        for getAllTiles.size
            tile.getID() += string;
         */

        //================================================================================
        // setup the logger
        //================================================================================
        try {
            //================================================================================
            // set log level

            LOGGER.info("*****STARTING*****");
        } catch (Exception e) {
            //================================================================================
            // catch error
            LOGGER.warning(e.toString());

        }
    }

    //--------------------------------------------------------------------------------
    // check for incoming messages and broadcast
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < connections.size(); i++) {
                Connection ith = connections.get(i);

                //--------------------------------------------------------------------------------
                // if connection terminated, remove from list of active connections
                if (!ith.isAlive())
                    connections.remove(i);

                //================================================================================
                // Broadcasts to all clients oder to one specific client
                // -broadcasting to all:
                //  jth.print...
                // -broadcast to specific client:
                //  ith.print...
                //================================================================================
                String message = ith.getMessage();

                //send init board strings to clients (speficially)
                if(!connections.get(i).isInit()) {
                    ith.println("initTile");
                    ith.println("initPlayer");
                    connections.get(i).setInit(true);
                }

                //--------------------------------------------------------------------------------
                // Broadcast to specific clients goes here before the jth-loop!
                if(mException) {
                    ith.println(sException);
                    mException = false;
                }
                //--------------------------------------------------------------------------------
                // Begin with server broadcasting to all clients
                // Begin with reading client messages
                if (message != null)
                    for (Connection jth : connections) {
                        try {
                            /**
                             * TODO hier werden logik funktionien aufgerufen
                             * hier wird das board überprüft und wieder gesenet
                             */
                            jth.println(message);
                        }
                        catch (Exception e) {
                            //--------------------------------------------------------------------------------
                            // error will only be displayed in the client
                            mException = true;
                            sException = e.getMessage();
                        }
                    }
            }

            //--------------------------------------------------------------------------------
            // don't monopolize processor
            try                 { Thread.sleep(100);   }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}