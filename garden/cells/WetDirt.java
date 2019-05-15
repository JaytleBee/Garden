package garden.cells;

import java.awt.Color;
import java.util.List;

import garden.SurroundingCells;
import garden.World;

public class WetDirt extends WetCell {
	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		updateWetness(surrounding);
		
		if (wetness <= 0.6)
			return new DryDirt();
		
		if (surrounding.any(Grass.class) && wetness > 1 
				&& (!surrounding.top.isSolid() || (surrounding.top.getClass() == Grass.class 
				&& ((Grass) surrounding.top).getDepth() + 1 < Grass.MAX_DEPTH)))
			return new Grass();
		
		if (!surrounding.bottom.isSolid())
			return surrounding.bottom;
		
		if (surrounding.top.getClass() == Water.class && Math.random() < 0.1)
			return new Grass();
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doesFall(SurroundingCells surrounding) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(0xa52a2a - 0x111111 * (int)wetness);
	}

	@Override
	public boolean absorbsWater() {
		return true;
	}
	
	public boolean blocksLight() {
		return true;
	}
}
