package avaj;

public class Coordinates {
    private int lon;
    private int lat;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.lon = longitude;
        this.lat = latitude;
        this.height = height;
    }
    public int getLongitude() {
        return lon;
    }
    public int getLatitude() {
        return lat;
    }
    public int getHeight() {
        return height;
    }
    public void addLongitude(int increase) {
        lon += increase;
    }
    public void addLatitude(int increase) {
        lat += increase;
    }
    public void addHeight(int increase) {
        height += increase;
        if(height > 100)
            height = 100;
        if (height < 0)
            height = 0;
    }
}
