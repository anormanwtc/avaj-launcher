package avaj;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower wTower;

    Helicopter(String name, Coordinates coords) {
        super(name, coords);
    }

	private HashMap<String, String> msg = new HashMap<String, String>() {
		private static final long serialVersionUID = 12345678909876L;
	{
		put("SUN", "What a view, pity about the noise");
		put("RAIN", "Close the doors we dont want to get wet");
		put("FOG", "We can move as slow as we need in low visibility");
		put("SNOW", "Close the doors or suffer the cold");
	}};

    

	@Override
	public void updateConditions() {
        String weather = wTower.getWeather(this.coordinates);
        
        System.out.println("Helicopter#" + this.name + "(" + this.id + "): " + msg.get(weather));
        changeCoords(weather);
		if (this.coordinates.getHeight() <= 0) {
            System.out.println("This may be a rough landing...");
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude() 
				+ "] Latitude: [" + this.coordinates.getLatitude()
				+ "] Height: [" + this.coordinates.getHeight() + "]");
            this.wTower.unregister(this);
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

    @Override
    public void registerTower(WeatherTower tower) {
        this.wTower = tower;
        this.wTower.register(this);
        System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    @Override
    public void changeCoords(String weather) {
        if (weather.equals("SUN")) {
            this.coordinates.addHeight(2);
            this.coordinates.addLongitude(10);
        }
        else if (weather.equals("RAIN")) {
            this.coordinates.addLongitude(5);
        }
        else if (weather.equals("SNOW")) {
            this.coordinates.addLongitude(1);
        }
        else if (weather.equals("FOG")) {
            this.coordinates.addHeight(-12);
        }
    }
}
