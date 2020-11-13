package avaj;

import java.util.List;
import java.util.ArrayList;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    public void register(Flyable ufo) {
        observers.add(ufo);
    }
    public void unregister(Flyable ifo) {
        observers.remove(ifo);
    }
    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
