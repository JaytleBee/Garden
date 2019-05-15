package garden;

public class RunWorldThread extends Thread {
	private boolean active;
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private boolean die;
	
	private World world;
	private Garden garden;
	
	
	public RunWorldThread(Garden garden, World world, boolean active) {
		this.garden = garden;
		this.world = world;
		this.active = active;
	}
	
	public void run() {
		super.run();
		while (!die) {
			if (active) {
				world.step();
			}
			
			try {
				Thread.sleep(100 - garden.getSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
