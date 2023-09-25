package tcp.server;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP server : Socket programming in Java
 * This server is basically a blocking one
 * Meaning once accept method is called it waits for some client to call
 * To handle multiple connections we create a new thread everytime and handle that connection in that thread
 */
public class TCPServer {

    private int port;


    private DataInputStream in;

    TCPServer(int port) {
        this.port = port;

    }

    TCPServer() {
        this(5388);
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        Socket socket = serverSocket.accept(); // accepting connection from clients , this will block

        System.out.println("Connected to client .....");

        // get input stream from socket
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String line = "";

        //TODO : create a new thread and handle this in that thread
        while (!line.equals("exit")) {
            try {
                line = in.readUTF();
                System.out.println("Message from client " + line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }
        System.out.println("Closing connection");

        // close connection
        socket.close();
        in.close();
    }

    public static void main(String[] args) throws IOException {
        new TCPServer().startServer();
    }

}
