import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Main.class.getName());
	}
	
	private TCPServer tcp;
	String paintName;
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		tcp = TCPServer.getInstance();
		tcp.SetObserver(this);
	}
	
	public void draw() {
		background(180);
	if(paintName!=null) {
		text(paintName, width/2, height/2);
	}
	}
	
	public void mousePressed() {
		
	}

	@Override
	public void OnMessage(String msg) {
		// TODO Auto-generated method stub
		paintName = msg;
		System.out.println("Mensaje Recibido"+msg);
	}

}
