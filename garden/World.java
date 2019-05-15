package garden;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import garden.cells.*;

public class World extends JPanel implements MouseListener, MouseMotionListener {
	private Garden garden;
	
	private boolean rain;
	
	public Cell getCell(int x, int y) {
		if (x < 0 || x >= widthInCells || y < 0 || y >= heightInCells)
			return new Bedrock();
		return cells[x][y];
	}
	
	public void setRain(boolean rain) {
		this.rain = rain;
	}

	private int mouseX, mouseY;
	
	private boolean mousePressed = false;
	
	private Cell[][] cells;
	private int widthInCells = 200;
	private int heightInCells = 150;
	
	public World(Garden garden) {
		super();
		
		this.setSize(800, 600);
		
		this.garden = garden;
		
		cells = new Cell[widthInCells][heightInCells];
		
		reset();
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 800, 600);
		
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				g.setColor(cells[x][y].getColor());
				
				g.fillRect(x * 4, y * 4, 4, 4);
			}
		}
	}
	
	public void step() {
		Cell[][] newCells = new Cell[widthInCells][heightInCells];
		
		if (rain) {
			for (int x = 0; x < widthInCells; x++) {
				if (Math.random() < ((double)garden.getRainRate().getValue() / 100)) {
					cells[x][0] = new Water();
				}
			}
		}
		
		
		for (int x = 0; x < widthInCells; x++) {
			for (int y = 0; y < heightInCells; y++) {
				
				
				SurroundingCells surrounding = new SurroundingCells();
				
				surrounding.topLeft = x == 0 || y == 0 				? new Bedrock() : cells[x - 1][y - 1];
				surrounding.top = y == 0 								? new Bedrock() : cells[x][y - 1];
				surrounding.topRight = x + 1 == widthInCells || y == 0 ? new Bedrock() : cells[x + 1][y - 1];
				surrounding.left = x == 0 								? new Bedrock() : cells[x - 1][y];
				surrounding.right = x + 1 == widthInCells 				? new Bedrock() : cells[x + 1][y];
				surrounding.bottomLeft = x == 0 || y + 1== heightInCells	? new Bedrock() : cells[x - 1][y + 1];
				surrounding.bottom = y + 1== heightInCells 				? new Bedrock() : cells[x][y + 1];
				surrounding.bottomRight = x + 1 == widthInCells || y + 1 == heightInCells? new Bedrock() : cells[x + 1][y + 1];
				
				surrounding.xMiddle = x;
				surrounding.yMiddle = y;
				
				newCells[x][y] = cells[x][y].update(surrounding, this);
			}
		}
		
		cells = newCells;
		
		tryPlaceBlock();
	}

	public void reset() {
		int dirtHeight = 50;
		
		Random random = new Random();
		
		for (int x = 0; x < widthInCells; x++) {
			dirtHeight += random.nextInt(3) - 1;
			
			for (int y = 0; y < heightInCells; y++) {
				
				if (y + dirtHeight >= heightInCells)
					cells[x][y] = new DryDirt();
				else
					cells[x][y] = new Air();
			}
		}
		
		repaint();
	}
	
	private void tryPlaceBlock() {
		if (mousePressed && mouseX >= 0 && mouseX < widthInCells && mouseY >= 0 && mouseY < heightInCells) {
			cells[mouseX][mouseY] = garden.getSelectedCellType();
		}
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		
		mouseX = e.getX() / 4;
		mouseY = e.getY() / 4;
		
		tryPlaceBlock();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = false;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX() / 4;
		mouseY = e.getY() / 4;
		
		tryPlaceBlock();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX() / 4;
		mouseY = e.getY() / 4;
		
		tryPlaceBlock();
	}
}
