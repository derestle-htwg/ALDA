package sim;

import sim.SYSimulation;
import java.awt.Color;
import java.io.IOException;

public class SYDemo {
	public static void main(String[] args) {
        SYSimulation sim;
		try {
			sim = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		
		sim.startSequence("Test1");
        sim.visitStation(9);
        sim.visitStation(20);
        sim.visitStation(33);
        sim.visitStation(46);
        sim.visitStation(79);
        sim.visitStation(63);
		sim.drive(9, 20, Color.YELLOW);
        sim.drive(20, 33, Color.YELLOW);
        sim.drive(33, 46, Color.YELLOW);
        sim.drive(46, 79, Color.RED.darker());
        sim.drive(79, 63, Color.GREEN.darker());
        sim.stopSequence();
        
    }
}
