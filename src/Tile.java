import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class Tile {
	private boolean isBomb;
	private boolean isFlagged;
	private TileManager manager;
	private int xPos;
	private int yPos;
	private boolean isVisible = false;
	private static int tileSize = 30;
	private int numBombs;
	private static ImageIcon blank = new ImageIcon("src/shaq.png");
	
	public Tile(int x, int y, TileManager _manager){
		xPos = x;
		yPos = y;
		manager = _manager;
		double bombPercent = (double)(manager.getNumBombs())/(manager.getXTiles() * manager.getYTiles());
		if(Math.random() < bombPercent)
			isBomb = true;
		else
			isBomb = false;
	}
	
	public void decideNumber(){
		int bombs = 0;
		if(manager.getTile(xPos+1, yPos)!= null && manager.getTile(xPos+1, yPos).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos-1, yPos)!= null && manager.getTile(xPos-1, yPos).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos, yPos+1)!= null && manager.getTile(xPos, yPos+1).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos, yPos-1)!= null && manager.getTile(xPos, yPos-1).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos+1, yPos+1)!= null && manager.getTile(xPos+1, yPos+1).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos+1, yPos-1)!= null && manager.getTile(xPos+1, yPos-1).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos-1, yPos+1)!= null && manager.getTile(xPos-1, yPos+1).isBomb){
			bombs++;
		}
		if(manager.getTile(xPos+-1, yPos-1)!= null && manager.getTile(xPos-1, yPos-1).isBomb){
			bombs++;
		}
		numBombs = bombs;
	}
	
	public boolean isBomb(){
		return isBomb;
	}
	
	public void setManager(TileManager _manager){
		manager = _manager;
	}
	
	public static int getTileSize(){
		return tileSize;
	}
	
	public int getNumBombs(){
		return numBombs;
	}
	
	public void SetVisible(boolean bool){
		isVisible = bool;
	}
	
	public void SetFlagged(boolean bool){
		isFlagged = bool;
	}
	
	public boolean isFlagged(){
		return isFlagged;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
	public void draw(BufferedImage image){
		Graphics g = image.getGraphics();
		int newXPos = manager.getX() + (xPos * tileSize);
		int newYPos = manager.getY() + (yPos * tileSize);
		if(isVisible){
			if(isBomb){
				g.setColor(Color.gray);
				g.fillRect(newXPos, newYPos, tileSize, tileSize);
			}else if(numBombs!=0){
					g.setColor(new Color(230,230,230));
					g.fillRect(newXPos, newYPos, tileSize, tileSize);
					g.setFont(new Font("Arial",Font.PLAIN,20));
					g.setColor(Color.red);
					g.drawString(""+numBombs, newXPos+5, newYPos+20);
			}else{
					g.setColor(Color.white);
					g.fillRect(newXPos, newYPos, tileSize, tileSize);
			}
		}else{
			if(isFlagged){
				g.setColor(Color.red);
			}else{
				g.setColor(Color.blue);
			}
			g.fillRect(newXPos, newYPos,tileSize, tileSize);
		}
		g.setColor(Color.black);
		g.drawRect(newXPos, newYPos, tileSize, tileSize);
	}
}
