import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class gameClient 
{

	public static void main(String[] args) throws IOException 
    {
		final String ip = "atlasNetwork.dynu.net";
		
        Socket serverSocket = new Socket(ip, 9090);
        BufferedReader input =new BufferedReader(new InputStreamReader( serverSocket.getInputStream() ));

        String responce = null;

        while(true)
        {
            responce = input.readLine();
            if( responce != null)
                System.out.println( responce );
        }

    }

	
}
