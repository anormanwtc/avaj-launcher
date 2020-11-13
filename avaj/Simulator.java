package avaj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Simulator {
	
	private static int simulationCycles;
	private static BufferedReader br;
	private static String currentLine;
	
	private static WeatherTower tower;
	
	private static void initSimulation(File file) {
		try {
			br = new BufferedReader(new FileReader(file));
			simulationCycles = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException | NullPointerException e) {
            System.out.println("Invalid simulation");
            System.exit(0);
		}
	}
	
	private static void loadAircrafts() {
		try {
			String info[];
			tower = new WeatherTower();
			while ((currentLine = br.readLine()) != null) {
				info = currentLine.split("\\s+");
				AircraftFactory.newAircraft(info[0], info[1], Integer.parseInt(info[2]),
					Integer.parseInt(info[3]), Integer.parseInt(info[4])).registerTower(tower);

			}
			br.close();
		} catch (IOException | NumberFormatException | NullPointerException e) {
            System.out.println("Invalid simulation");
            System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		try {
			new File("simulation.txt").createNewFile();
			PrintStream out = new PrintStream(new FileOutputStream("simulation.txt"));
			System.setOut(out);
		}
		catch (IOException e) {
			System.exit(0);
		}
		if (args.length < 1) { 
			return;
		}
		initSimulation(new File(args[0]));
		loadAircrafts();
		while (simulationCycles-- > 0) {
			tower.changeWeather();
		}
	}
}

