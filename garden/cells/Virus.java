package garden.cells;

import java.awt.Color;

import garden.SurroundingCells;
import garden.World;

public class Virus extends Cell {

	@Override
	public Cell update(SurroundingCells surrounding, World world) {
		if (surrounding.any(Water.class)) {
			return new DryDirt();
		}
			
		if (Math.random() < 0.01)
			return new Air();
		
		// TODO Auto-generated method stub
		if (!surrounding.bottom.isSolid())
			return surrounding.bottom;
		
		for (Cell c : surrounding.asArray()) {
			if (c.getClass() != Air.class && c.getClass() != Virus.class)
				return this;
		}
		return new Air();
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
	public boolean absorbsWater() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean blocksLight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(0xff1493);
	}

}
