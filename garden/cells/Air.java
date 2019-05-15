package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Air extends Cell {
	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		if (surrounding.top.doesFall(surrounding)) {
			return surrounding.top;
		}
		
		if (surrounding.right.doesSlide())
			return surrounding.right;
		
		//if (surrounding.left.doesSlide())
		//	return surrounding.left;
		
		//if (surrounding.topRight.doesSlide()) {
		//	return surrounding.topRight;
		//}
		
		//if (surrounding.topLeft.doesSlide()) {
		//	return surrounding.topLeft;
		//}
		
		return this;
	}

	public boolean doesFall(SurroundingCells surrounding) {
		return false;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public Color getColor() {
		return new Color(0xe0ffff);
	}
	
	@Override
	public boolean absorbsWater() {
		return false;
	}

	@Override
	public boolean blocksLight() {
		return false;
	}
}
