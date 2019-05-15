package garden;

import java.util.ArrayList;
import java.util.List;

import garden.cells.Cell;

public class SurroundingCells {
	public SurroundingCells() {
		
	}
	
	public SurroundingCells(int xMiddle, int yMiddle, Cell topLeft, Cell top, Cell topRight,Cell left, Cell right, Cell bottomLeft, Cell bottom, Cell bottomRight) {
		this.xMiddle = xMiddle;
		this.yMiddle = yMiddle;
		
		this.topLeft = topLeft;
		this.top = top;
		this.topRight = topRight;
		this.left = left;
		this.right = right;
		this.bottomLeft = bottomLeft;
		this.bottom = bottom;
		this.bottomRight = bottomRight;
	}
	
	public int xMiddle, yMiddle;
	public Cell topLeft, top, topRight,
				left, right,
				bottomLeft, bottom, bottomRight;
	
	public boolean any(Class<? extends Cell> type) {
		for (Cell c : asArray()) {
			if (type.isAssignableFrom(c.getClass()))
				return true;
		}
		
		return false;
	}
	
	public<T extends Cell> List<T> get(Class<T> type) {
		List<T> list = new ArrayList<>();
		
		for (Cell c : asArray()) {
			if (type.isAssignableFrom(c.getClass())) {
				list.add((T) c);
			}
		}
		
		return list;
	}
	
	public Cell[] asArray() {
		return new Cell[] {topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight};
	}
}
