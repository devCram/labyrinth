/******************************************************************************
 *  Compilation:  javac Connection.java
 *
 ******************************************************************************/

package lobby;

import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import network.In;
import network.Out;
import gameLogic.*;


public class Connection extends Thread {
    private Socket socket;
    private Out out;
    private In in;
    private String message;         // one line buffer
    private boolean init = true;   // for init message
    private int pId;                 //connection ID (for logging)
    private int roomID;
    private String room;
    private String playerName;
    private boolean ready=false;
    private boolean host=false;

    public Connection(Socket socket) {
        in = new In(socket);
        out = new Out(socket);
        this.socket = socket;
    }

    public void println(String s) {
        out.println(s);
    }

    public void run() {
        String s;
        while ((s = in.readLine()) != null) {
            setMessage(s);
        }
        out.close();
        in.close();
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("closing socket");
    }

    /***************************************************************************
     *  The methods getMessage() and setMessage() are synchronized
     *  so that the thread in Connection doesn't call setMessage()
     *  while the ConnectionListener thread is calling getMessage().
     ***************************************************************************/
    public synchronized String getMessage() {
        if (message == null) return null;
        String temp = message;
        message = null;
        notifyAll();
        return temp;
    }

    public synchronized void setMessage(String s) {
        if (message != null) {
            try {
                wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        message = s;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void startGameServer() {
        try {
            String[] startOptions = new String[]{System.getProperty("java.home") + "/bin/java",
                    "-Djava.util.logging.config.file=src/network/logging.properties",
                    "-jar",
                    "gameServer.jar",
                    "4445"};
            new ProcessBuilder(startOptions).start();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}