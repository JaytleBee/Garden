package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public abstract class Cell {
	public void place() {
		
	}
	
	public abstract Cell update(SurroundingCells surrounding, World world);
	public abstract boolean isSolid();
	public abstract boolean doesFall(SurroundingCells surrounding);
	
	public abstract boolean absorbsWater();
	public abstract boolean blocksLight();
	
	public boolean doesSlide() {
		return false;
	}
	public abstract Color getColor();
}
