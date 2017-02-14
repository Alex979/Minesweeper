import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class MinesweeperPanel extends JPanel{

	private static int screenXWidth = 481;
	private static int screenYWidth = 481;
	private static int numTilesX = 16;
	private static int numTilesY = 16;
	private static int numberOfBombs = 40;
	private TileManager manager;
	private BufferedImage image;
	private Graphics g;
	
	
	public MinesweeperPanel(){
		image = new BufferedImage(screenXWidth, screenYWidth, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		g.setColor(Color.gray.brighter());
		g.fillRect(0, 0, screenXWidth, screenYWidth);
		
		addMouseListener(new ClickListener());
		
		manager = new TileManager(numTilesX,numTilesY,0,0, numberOfBombs, image, this);
		for(int x=0; x<manager.getXTiles(); x++){
			for(int y=0; y<manager.getYTiles(); y++){
				manager.getTile(x, y).draw(image);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, screenXWidth, screenYWidth, null);
	}
	
	public class ClickListener implements MouseListener{
		
		public void mousePressed(MouseEvent e){
			if(e.getX()>manager.getX() && e.getX()<(manager.getX() + (manager.getXTiles()*Tile.getTileSize()))){
				if(e.getY()>manager.getY() && e.getY()<(manager.getY() + (manager.getYTiles()*Tile.getTileSize()))){
					int clickX = (int)(e.getX()/Tile.getTileSize());
					int clickY = (int)(e.getY()/Tile.getTileSize());
					if(e.getButton() == MouseEvent.BUTTON1){
						manager.click(clickX, clickY);
					}else if(e.getButton() == MouseEvent.BUTTON3){
						manager.flagTile(clickX, clickY);
					}
					manager.redraw();
				}
			}
		}

		public void mouseClicked(MouseEvent arg0) {
			
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
			
		}
	}
}
