package tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {


    private Socket clientSocket;

    private DataOutputStream dataOutputStream;
    private DataInputStream inputStream;


    public static void main(String[] args) throws Exception {

        new TCPClient().startClient();
    }

    void startClient() throws IOException {

        clientSocket = new Socket("127.0.0.1", 5388);
        System.out.println("Connected to Server...");
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        inputStream = new DataInputStream(System.in); // this reads data from standard input
        String line = "";

        while (!line.equals("exit")) {
            try {
                line = inputStream.readLine();
                dataOutputStream.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            inputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (IOException i) {
            System.out.println(i);
        }


    }


}
