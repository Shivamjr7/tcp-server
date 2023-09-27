package tcp.server;

import javax.xml.crypto.Data;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;

    private DataInputStream in ;
        ServerThread(Socket socket){
        this.socket  = socket;
    }
    @Override
    public void run() {

        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String line = "";

        //TODO : create a new thread and handle this in that thread
        while (!line.equals("exit")) {
            try {
                line = in.readUTF();
                System.out.println("Message from client " + line);
            } catch (IOException i) {
                System.out.println("Connection lost to addrr : " + socket.getRemoteSocketAddress());
                line = "exit";
            }
        }
        System.out.println("Closing connection");

        try {
            // close connection
            socket.close();
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
