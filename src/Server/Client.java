package Server;

import java.io.*;
import java.net.Socket;

public class Client {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //Server Connection
    private int port = 9090;
    private String hostName = "localhost";

    private Socket socket;
    private boolean running;

    //Server Communication
    private BufferedReader reader;
    private PrintWriter writer;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public Client(){

        this.running=false;
    }

    public void start() {

        try {

            this.running = true;

            //Creates socket connection
            this.socket = new Socket(this.hostName, this.port);

            //Creates reader system
            InputStream inputStream = socket.getInputStream();
            this.reader = new BufferedReader(new InputStreamReader(inputStream));

            //Creates writer system
            OutputStream outputStream = socket.getOutputStream();
            this.writer = new PrintWriter(outputStream, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminate(){

        this.running=false;
    }

    //Server Communication
    public void write(String jsonInput){

        this.writer.println(jsonInput);
    }

    public String read(){

        try {

            String inputString = this.reader.readLine();
            return inputString;

        }catch (IOException e){

            e.printStackTrace();
            return null;
        }
    }
}