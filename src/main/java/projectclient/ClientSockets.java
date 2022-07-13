package projectclient;
import java.net.*;
import java.io.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.json.JSONObject;
public class ClientSockets {
    private Socket socket;
    private DataInputStream reader;
    private DataOutputStream writer;
    JSONObject json;
    public ClientSockets(Socket socket) {
        this.json = new JSONObject();
        try {

            this.socket=socket;
            this.reader=new DataInputStream(socket.getInputStream());
            this.writer=new DataOutputStream(socket.getOutputStream());

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMassage (JSONObject json) {
        try{
            writer.writeUTF(json.toString());
            writer.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    public JSONObject getMassage() {
        try {
            String  massage = (String) reader.readUTF();
            JSONObject json1 = new JSONObject(massage);
            return json1;
        }catch(IOException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        return json;
    }
}
