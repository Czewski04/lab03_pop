package organzier;

import java.util.ArrayList;

public class Organizer {
    private int id;
    private String name;
    private ArrayList<Integer> EventsId;

    public ArrayList<Integer> getEventsId() {
        return EventsId;
    }

    public void setEventsId(ArrayList<Integer> eventsId) {
        EventsId = eventsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
