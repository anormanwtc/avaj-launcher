package avaj;

class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates)  {
        this.name = name;
        this.id = nextID();
        this.coordinates = coordinates;
    }

    private static long nextID() {
        idCounter++;
        long nextId = idCounter;
        return nextId;
    }
}