package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Plant extends Cell {
	private final int BLOOM_HEIGHT = 15;
	
	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		if (surrounding.any(Virus.class))
			return new Virus();
		
		if (!surrounding.bottom.isSolid() || surrounding.bottom.getClass() == Blossom.class)
			return new Air();
		
		
		int height = 0;
		while (world.getCell(surrounding.xMiddle, surrounding.yMiddle + height).getClass() == Plant.class) {
			height++;
		}
		
		if (height >= BLOOM_HEIGHT) {
			return new Blossom();
		}
		
		
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
		return false;
	}

	@Override
	public boolean absorbsWater() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(0xadff2f);
	}

	public boolean blocksLight() {
		return false;
	}
}
