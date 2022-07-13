package project.discord;

import projectclient.ClientSockets;
import projectclient.MainSystem;

import java.io.IOException;
import java.net.Socket;

public class conection {

    static Socket socket;
    static ClientSockets clientSockets;
    static {
        try {
            socket = new Socket("127.0.0.1",5056);
            clientSockets = new ClientSockets(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static MainSystem mainsystem;

    static {
        try {
            mainsystem = new MainSystem(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void controll(){

    }
}
