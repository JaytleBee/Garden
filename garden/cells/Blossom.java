package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Blossom extends Cell {

	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		if (surrounding.any(Virus.class)) {
			return new Virus();
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
	public boolean blocksLight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(0x9400d3);
	}

}
