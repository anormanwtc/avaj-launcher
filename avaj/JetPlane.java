package avaj;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower wTower;

    JetPlane(String name, Coordinates coords) {
        super(name, coords);
    }

	private HashMap<String, String> msg = new HashMap<String, String>() {
		private static final long serialVersionUID = 654321123456L;
	{
		put("SUN", "We're free to fly fast");
		put("RAIN", "Its nice seeing the rain wash past");
		put("FOG", "How murky visibility low");
		put("SNOW", "So pretty");
	}};

	@Override
	public void updateConditions() {
        String weather = wTower.getWeather(this.coordinates);
        
        System.out.println("JetPlane#" + this.name + "(" + this.id + "): " + msg.get(weather));
        changeCoords(weather);
		if (this.coordinates.getHeight() <= 0) {
            System.out.println("RIP us may the lord have mercy...");
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude() 
				+ "] Latitude: [" + this.coordinates.getLatitude()
				+ "] Height: [" + this.coordinates.getHeight() + "]");
            this.wTower.unregister(this);
            System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

    @Override
    public void registerTower(WeatherTower tower) {
        this.wTower = tower;
        this.wTower.register(this);
        System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    @Override
    public void changeCoords(String weather) {
        if (weather.equals("SUN")) {
            this.coordinates.addHeight(2);
            this.coordinates.addLatitude(10);
        }
        else if (weather.equals("RAIN")) {
            this.coordinates.addLatitude(5);
        }
        else if (weather.equals("SNOW")) {
            this.coordinates.addLatitude(1);
        }
        else if (weather.equals("FOG")) {
            this.coordinates.addHeight(-7);
        }
    }
}
