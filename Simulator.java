import java.util.PriorityQueue;

public class Simulator {
  private final PriorityQueue<Event> events;

  public Simulator(Simulation simulation) {
    events = new PriorityQueue<Event>();
    for (Event e : simulation.initialEvents()) {
       events.add(e);
    }
  }

  public void run() {
    Event event = this.events.poll();
    while (event != null) {
      System.out.println(event);
      Event[] newEvents = event.simulate();
      for (Event e : newEvents) {
        events.add(e);
      }
      event = this.events.poll();
    }
    return;
  }
}
