package projectServer;

import java.net.*;
import java.io.*;

public class ProjectServer {

    public static void main(String[] args) throws IOException {
        initial();
        ServerSocket server = new ServerSocket(5056);
        
        try {
            System.out.println("Waiting for client");
            while(!server.isClosed()) {
            Socket socket1 = server.accept();
            System.out.println("new Client connected");
            new Thread(new Clienthandler(socket1)).start();
           
        }
        }catch (Exception e) {
            e.printStackTrace();
            server.close();
        }
    
    }

    private static void initial() throws FileNotFoundException, IOException {
        try {
            FileInputStream user = new FileInputStream("users.bin");
            user.close();
            
        }catch(FileNotFoundException fne) {
            FileOutputStream users = new FileOutputStream("users.bin");
            users.close();
        }
        try {
            FileInputStream user = new FileInputStream("accounts.bin");
            user.close();
        }catch(FileNotFoundException fne) {
            FileOutputStream users = new FileOutputStream("accounts.bin");
            users.close();
        }
        try {
            FileInputStream user = new FileInputStream("server.bin");
            user.close();
        }catch(FileNotFoundException fne) {
            FileOutputStream users = new FileOutputStream("server.bin");
            users.close();
        }
        try {
            FileInputStream user = new FileInputStream("groups.bin");
            user.close();
        }catch(FileNotFoundException fne) {
            FileOutputStream users = new FileOutputStream("groups.bin");
            users.close();
        }
        
        try {
            FileInputStream user = new FileInputStream("massage.bin");
            user.close();
        }catch(FileNotFoundException fne) {
            FileOutputStream users = new FileOutputStream("massage.bin");
            users.close();
        }
        
        try {
            FileInputStream user = new FileInputStream("groupmassage.bin");
            user.close();
        }catch(FileNotFoundException fne) {
            FileOutputStream users = new FileOutputStream("groupmassage.bin");
            users.close();
        }
    }
    
    
}
