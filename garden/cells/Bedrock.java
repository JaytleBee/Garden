package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Bedrock extends WetCell {

	@Override
	public float getWetness() {
		return 0;
	}
	
	@Override
	public Cell update(SurroundingCells surrounding, World world) {
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
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean absorbsWater() {
		return true;
	}
	
	public boolean blocksLight() {
		return true;
	}
}
