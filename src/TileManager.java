import java.awt.*;
import java.awt.image.*;


public class TileManager {

	private Tile[][] tiles;
	private int xPos;
	private int yPos;
	private int xTiles;
	private int yTiles;
	private int numberOfBombs = 40;
	private BufferedImage image;
	private Graphics g;
	private MinesweeperPanel panel;
	private static boolean isPlaying = true;
	
	public TileManager(int x, int y, int xPos, int yPos, int numberOfBombs, BufferedImage image, MinesweeperPanel panel){
		this.xPos = xPos;
		this.yPos = yPos;
		xTiles = x;
		yTiles = y;
		this.numberOfBombs = numberOfBombs;
		this.image = image;
		g = this.image.getGraphics();
		this.panel = panel;
		tiles = new Tile[x][y];
		for(int var=0; var<x; var++){
			for(int var2=0; var2<y; var2++){
				tiles[var][var2] = new Tile(var,var2, this);
			}
		}
		for(int var=0; var<x; var++){
			for(int var2=0; var2<y; var2++){
				getTile(var,var2).decideNumber();
			}
		}
	}
	
	public void click(int x, int y){
		System.out.println("Click @ "+x+", "+y);
		Tile clickedTile = getTile(x,y);
		if(clickedTile!=null){
			if(clickedTile.isVisible())
				return;
			if(clickedTile.isFlagged()){
				clickedTile.SetFlagged(false);
				return;
			}
			if(clickedTile.isBomb()){
				loseGame();
				for(int xTile=0; xTile<xTiles; xTile++){
					for(int yTile=0; yTile<yTiles; yTile++){
						if(tiles[xTile][yTile].isBomb())
							tiles[xTile][yTile].SetVisible(true);
					}
				}
				return;
			}
			if(clickedTile.getNumBombs()>0){
				clickedTile.SetVisible(true);
				return;
			}
			clickedTile.SetVisible(true);
			click(x-1,y);
			click(x+1,y);
			click(x,y-1);
			click(x,y+1);
			click(x+1,y+1);
			click(x+1,y-1);
			click(x-1,y-1);
			click(x-1,y+1);
			return;
		}
	}
	
	public void flagTile(int x, int y){
		System.out.println("Flag @ "+x+", "+y);
		Tile clickedTile = getTile(x,y);
		if(clickedTile.isFlagged())
			clickedTile.SetFlagged(false);
		else if(!clickedTile.isVisible()){
			clickedTile.SetFlagged(true);
		}
	}
	
	public void redraw(){
		panel.repaint();
		for(int x=0; x<xTiles; x++){
			for(int y=0; y<yTiles; y++){
				getTile(x,y).draw(image);
			}
		}
		if(!isPlaying()){
			g.setColor(Color.red.darker());
			g.setFont(new Font("Comic Sans",Font.PLAIN,30));
		}
	}
	
	private void loseGame(){
		isPlaying = false;
	}
	
	public static boolean isPlaying(){
		return isPlaying;
	}
	
	public Tile getTile(int x, int y){
		if(x>=xTiles || y>=yTiles || x<0 || y<0){
			return null;
		}
		return tiles[x][y];
	}
	
	public int getXTiles(){
		return xTiles;
	}
	
	public int getYTiles(){
		return yTiles;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getNumBombs(){
		return numberOfBombs;
	}
	
	public int getY(){
		return yPos;
	}
}
