package Server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
                this.parseRequest(inputString);

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

    private void parseRequest(String inputString){

        JSONParser parser = new JSONParser();

        try{

            JSONObject inputJson = (JSONObject) parser.parse(inputString);
            long requestID = (long) inputJson.get("Request");

            if(requestID == -1){
                //Terminate Thread
                this.terminate();

            }else if(requestID == 0){
                //Create User
                JSONObject userData = (JSONObject) inputJson.get("User");
                String username = (String) userData.get("Username");

                JSONObject requestState = this.server.addUser(username,userData);
                this.write(requestState.toJSONString());

            }else if(requestID == 1){
                //Access User
                String username = (String) inputJson.get("Username");
                String password = (String) inputJson.get("Password");

                JSONObject userData = this.server.queryUser(username,password);
                this.write(userData.toJSONString());
            }

        }catch (ParseException e){

            e.printStackTrace();
        }
    }
}
