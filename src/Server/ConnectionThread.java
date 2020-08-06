package Server;

import java.io.*;
import java.net.Socket;

public class ConnectionThread  extends Thread{

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //Server Connection
    private Server server;
    private Socket socket;

    //Client Communication
    private PrintWriter writer;

    //Connection State
    private boolean running;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public  ConnectionThread(Server server, Socket socket){

        this.server = server;
        this.socket = socket;

        this.running = false;
    }

    public void run(){

        try {

            //Connection State
            this.running = true;

            //Read data from the client (read to byte array)
            InputStream input = socket.getInputStream();
            //Set byte array to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));


            //Write data as a byte array
            OutputStream output = socket.getOutputStream();
            //Converts byte array to text format
            this.writer = new PrintWriter(output, true);

            String inputString;

            do {

                inputString = reader.readLine();
                System.out.println("Input from Client: "+inputString);
                this.write("Receive");

            }while (this.running);

            socket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminate(){

        this.running = false;
    }

    //Client Communication
    public void write(String output){

        this.writer.println(output);
    }
}
