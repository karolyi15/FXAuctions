package Server;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //Sever Socket
    private int port=9090;
    private ServerSocket serverSocket;

    //Data Base
    private DataBase dataBase;

    //Server State
    private boolean running;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //Constructor
    public Server(){

        this.dataBase = DataBase.getInstance();

        this.running = false;
    }

    public void start(){

        try {

            this.serverSocket = new ServerSocket(port);

            this.running = true;
            System.out.print("Server is listening in port: " + port+"\n");


            while (running) {

                //Starts listening for incoming client requests
                Socket socket = serverSocket.accept();

                //Update Active Connections
                System.out.println("*** New User Connected ***");

                //Start Connection Thread
                new ConnectionThread(this,socket).start();

            }

            this.serverSocket.close();

        }catch(IOException e) {

            System.out.println("Error while attempting to connect user");
        }

    }

    public void terminate(){

        this.running = false;
    }

    //Data Base Access
    public synchronized JSONObject queryUser(String username, String password){

        JSONObject userData = this.dataBase.query("Users", username);
        JSONObject outputJson = new JSONObject();

        if(userData != null){

            if(userData.get("Password").equals(password)){

                outputJson.put("RequestState",true);
                outputJson.put("User",userData);

                return outputJson;
            }
        }

        outputJson.put("RequestState",false);
        return outputJson;
    }

    public synchronized JSONObject addUser(String username, JSONObject userData){

        boolean  requestState = this.dataBase.add("Users",username,userData);

        JSONObject outputJson = new JSONObject();

        outputJson.put("RequestState",requestState);

        return outputJson;
    }

    //Main
    public static void main(String[] args){

        Server server = new Server();
        server.start();
    }
}
