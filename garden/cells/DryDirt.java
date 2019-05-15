package garden.cells;

import java.awt.Color;
import java.util.List;

import garden.SurroundingCells;
import garden.World;

public class DryDirt extends Cell {

	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		if (!surrounding.bottom.isSolid())
			return surrounding.bottom;
		
		if (surrounding.top.getClass() == Water.class)
			return new WetDirt();
		
		List<WetCell> wetCells = surrounding.get(WetCell.class);
		
		for (WetCell c : wetCells) {
			if (c.getWetness() > 1) {
				c.subtractWetness();
				return new WetDirt();
			}
		}
		
		return this;
	}

	public boolean doesFall(SurroundingCells surrounding) {
		return true;
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public Color getColor() {
		return new Color(0xf4a460);
	}
	
	@Override
	public boolean absorbsWater() {
		return true;
	}
	
	public boolean blocksLight() {
		return true;
	}
}
