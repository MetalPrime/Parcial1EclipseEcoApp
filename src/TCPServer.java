import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread{



	    public static TCPServer instanceUnique;

	    public static TCPServer getInstance(){
	        if(instanceUnique==null){
	            instanceUnique = new TCPServer();
	            getInstance().start();
	        }
	        return instanceUnique;
	    }

	    private TCPServer(){

	    }

	    private ServerSocket server;
	    private Socket socket;
	    private BufferedReader reader;
	    private BufferedWriter writer;

	    @Override
	    public void run() {
	        try {
	            server = new ServerSocket(5000);
	            socket = server.accept();
	            InputStream is = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            reader = new BufferedReader(isr);

	            OutputStream os = socket.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            writer = new BufferedWriter(osw);

	            while (true){
	            	String line = reader.readLine();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void sendMessages(String msg){
	        new Thread(
	                () ->{
	                    try {
	                            writer.write(msg);
	                            writer.flush();
	                    } catch (IOException e){
	                        e.printStackTrace();

	                    }
	                }
	        ).start();


	    }
	}

