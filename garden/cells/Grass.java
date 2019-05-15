package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Grass extends WetCell {
	public static final int MAX_DEPTH = 7;
	
	private int depth = 0;
	
	public int getDepth() {
		return depth;
	}

	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		updateWetness(surrounding);
		
		if (surrounding.any(Virus.class))
			return new Virus();
			
		if (!surrounding.top.blocksLight())
			depth = 0;
		
		if (!surrounding.bottom.isSolid())
			return surrounding.bottom;
		
		if (surrounding.top.blocksLight()) {
			if (surrounding.top.getClass() == Grass.class) {
				depth = ((Grass) surrounding.top).depth + 1;
				if (depth < MAX_DEPTH)
					return this;
			}
				
			return new WetDirt();
		}
		
		return this;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean doesFall(SurroundingCells surrounding) {
		return true;
	}

	@Override
	public Color getColor() {
		return new Color(0x45ff2f);
	}

	@Override
	public boolean absorbsWater() {
		return true;
	}
	
	public boolean blocksLight() {
		return true;
	}
}
