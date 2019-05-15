package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Water extends Cell {

	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		if (surrounding.top.doesFall(surrounding) && surrounding.top.getClass() != Water.class)
			return surrounding.top;
		
		if (!surrounding.bottom.isSolid())
			return surrounding.bottom;
		
		if (surrounding.bottom.getClass() == Grass.class) {
			if (Math.random() < 0.01 && ((Grass) surrounding.bottom).getWetness() > 1) {
				return new Plant();
			}
		}
		
		if (surrounding.bottom.getClass() == Plant.class && Math.random() < 0.5) {
			return new Plant();
		}
		
		if (surrounding.bottom.absorbsWater())
			return new Air();
		
		if (surrounding.any(Bedrock.class))
			return new Air();
		
		return this;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean doesSlide() {
		return false;
	}

	@Override
	public boolean doesFall(SurroundingCells surrounding) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.blue;
	}

	@Override
	public boolean absorbsWater() {
		return false;
	}
	
	public boolean blocksLight() {
		return false;
	}
}
