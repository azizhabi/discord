package projectclient;

import java.io.IOException;
import java.net.*;
import org.json.JSONObject;

public class ProjectClient {
    public MainSystem mainsystem ;
    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1",5047);
        MainSystem mainsystem = new MainSystem(socket);
        mainsystem.client();

    }
}

