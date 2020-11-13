package avaj;

import java.util.HashMap;

public class Balloon extends Aircraft implements Flyable {
    private WeatherTower wTower;

    Balloon(String name, Coordinates coords) {
        super(name, coords);
    }

	private HashMap<String, String> msg = new HashMap<String, String>() {
		private static final long serialVersionUID = 12345678909876L;
	{
		put("SUN", "I hope I dont sunburn");
		put("RAIN", "Ugh its cold and wet");
		put("FOG", "I wish I could see the view");
		put("SNOW", "Cold but beautiful");
	}};

    

	@Override
	public void updateConditions() {
        String weather = wTower.getWeather(this.coordinates);
        
        System.out.println("Balloon#" + this.name + "(" + this.id + "): " + msg.get(weather));
        changeCoords(weather);
		if (this.coordinates.getHeight() <= 0) {
            System.out.println("Im glad balloon crashes are gentle enough...");
			System.out.println("Balloon#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude() 
				+ "] Latitude: [" + this.coordinates.getLatitude()
				+ "] Height: [" + this.coordinates.getHeight() + "]");
            this.wTower.unregister(this);
            System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

    @Override
    public void registerTower(WeatherTower tower) {
        this.wTower = tower;
        this.wTower.register(this);
        System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    @Override
    public void changeCoords(String weather) {
        if (weather.equals("SUN")) {
            this.coordinates.addHeight(4);
            this.coordinates.addLongitude(10);
        }
        else if (weather.equals("RAIN")) {
            this.coordinates.addHeight(-5);
        }
        else if (weather.equals("SNOW")) {
            this.coordinates.addHeight(-15);
        }
        else if (weather.equals("FOG")) {
            this.coordinates.addHeight(-3);
        }
    }
}
