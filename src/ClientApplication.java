import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApplication {

    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("Nemaš ni URL/IP adres ni port servera a hoćeš da komuniciraš s njim");
            System.exit(1);
        }
        String serverIP = args[0];
        int portNumber = Integer.parseInt(args[1]);
        try(Socket clientSocket = new Socket(serverIP, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader korisnikIn = new BufferedReader(new InputStreamReader(System.in))){

            String userInput;
            while ((userInput = korisnikIn.readLine())!=null){
                out.println(userInput);
                System.out.println("OD server odgovor: "+in.readLine());
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
