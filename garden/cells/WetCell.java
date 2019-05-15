package garden.cells;

import java.util.List;

import garden.SurroundingCells;

public abstract class WetCell extends Cell {
	protected float wetness = 1;
	
	public float getWetness() {
		return wetness;
	}
	
	public void subtractWetness() {
		wetness--;
	}
	
	protected void updateWetness(SurroundingCells surrounding) {
		if (surrounding.any(Water.class))
			wetness++;
		
		List<WetCell> surroundingWetCells = surrounding.get(WetCell.class);
		
		if (surrounding.any(Virus.class))
			wetness = 0;
		
		float cumulativeWetness = 0;
		for (WetCell c : surroundingWetCells) {
			cumulativeWetness += c.wetness;
		}
		
		for (WetCell c : surroundingWetCells) {
			c.wetness = cumulativeWetness / surroundingWetCells.size();
		}
		
	}
}
