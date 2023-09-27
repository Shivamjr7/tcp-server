package tcp.server;


import com.sun.security.ntlm.Server;

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
        while(true) {
            Socket socket = serverSocket.accept(); // accepting connection from clients , this will block
            System.out.println("Connected to client ....." + socket.getRemoteSocketAddress());
            Thread clientThread = new Thread(new ServerThread(socket));
            clientThread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        new TCPServer().startServer();
    }

}
