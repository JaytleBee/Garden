package garden;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSlider;

import garden.cells.*;

public class Garden extends JFrame implements ActionListener {
	private Cell selectedCellType = new DryDirt();
	private World world;
	
	private Cell[] selectibleCellTypes = {new Air(), 
			new DryDirt(), 
			new WetDirt(), 
			new Grass(), 
			new Water(),
			new Plant(),
			new Virus()};
	private JCheckBox auto;
	private JCheckBox rain;
	private JSlider speed;
	private JSlider rainRate;
	
	private long runDelay = 500;
	private RunWorldThread runWorld;
	
	public Garden() {
		super("Garden");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		world = new World(this);
		add(world, BorderLayout.CENTER);
		
		runWorld = new RunWorldThread(this, world, true);
		runWorld.start();
		
		JMenuBar menu = new JMenuBar();
		
		
		JButton step = new JButton("Step");
		step.setActionCommand("step");
		step.addActionListener(this);
		
		menu.add(step);
		
		JButton clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.addActionListener(this);
		
		menu.add(clear);
	
		rain = new JCheckBox("Rain");
		rain.setActionCommand("rain");
		rain.addActionListener(this);
		menu.add(rain);
		
		rainRate = new JSlider(0, 100, 1);
		menu.add(rainRate);
		
		auto = new JCheckBox("Auto");
		auto.setSelected(runWorld.isActive());
		auto.setActionCommand("startstop");
		auto.addActionListener(this);
		
		menu.add(auto);
		
		speed = new JSlider(1, 100, 50);
		menu.add(speed);
		
		
		for (int i = 0; i < selectibleCellTypes.length; i++) {
			JButton cellButton = new JButton();
			cellButton.setActionCommand("type " + i);
			cellButton.addActionListener(this);
			cellButton.setBackground(selectibleCellTypes[i].getColor());
			menu.add(cellButton);
		}
		
		
		setSize(800, 650);
		
		add(menu, BorderLayout.NORTH);
		
		
		
		setVisible(true);
	}
	
	public JSlider getRainRate() {
		return rainRate;
	}

	public Cell getSelectedCellType() {
		return selectedCellType;
	}
	
	public long getSpeed() {
		if (speed != null)
			return speed.getValue();
		return 0;
	}
	
	public static void main(String[] args) {	
		Garden g = new Garden();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		
		switch(command) {
		case("step"): {
			world.step();
		}break;
		case("startstop"): {
			runWorld.setActive(auto.isSelected());
		}break;
		case("clear"): {
			runWorld.setActive(false);
			world.reset();
			runWorld.setActive(auto.isSelected());
		}
		case("rain"): {
			world.setRain(rain.isSelected());
		}
		default: {
			if (command.startsWith("type ")) {
				int i = Integer.parseInt(command.substring(5, command.length()));
				
				selectedCellType = selectibleCellTypes[i];
			}
		}
		}
	}
}
