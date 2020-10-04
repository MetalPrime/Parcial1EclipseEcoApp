import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Main.class.getName());
	}
	
	private TCPServer tcp;
	
	private int currentScreen;
	private Color color;
	private Name paintName;
	private Coordinate coordinate;
	private Gson gson = new Gson();
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		tcp = TCPServer.getInstance();
		tcp.SetObserver(this);
		color = new Color(150, 150, 150);
		paintName = new Name(null);
		coordinate = new Coordinate(250,250);

		
	}
	
	public void draw() {
		background(180);
		
		switch(currentScreen) {
		case 0:
			fill(0);
			text("Esperando nombre",width/2,height/2);
			break;
		case 1:
			
			
			if(!paintName.getName().equals(null)) {
				textSize(30);
				fill(255);
				text(paintName.getName(),coordinate.getPosX()-30,coordinate.getPosY()-60);
				fill(color.getR(),color.getG(),color.getB());
				ellipse(coordinate.getPosX(), coordinate.getPosY(), 50, 50);
			}

			break;
		}
		
	}
	
	public void mousePressed() {
		
	}

	@Override
	public void OnMessage(String msg) {
		// TODO Auto-generated method stub
		if(msg.contains("name")) {
			gson = new Gson();
			paintName = gson.fromJson(msg, Name.class);
		}
		
		if(!paintName.getName().equals(null)){
			currentScreen = 1;
	    }
		
		if(msg.contains("R") && msg.contains("G") && msg.contains("B")) {
			gson = new Gson();
			color = gson.fromJson(msg, Color.class);
		}
		
		if(msg.contains("posX") && msg.contains("posY") ) {
			gson = new Gson();
			coordinate = gson.fromJson(msg, Coordinate.class);
		}
	}

}
