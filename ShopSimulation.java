import java.util.Scanner;

class ShopSimulation extends Simulation {
  public boolean[] available;
  public Event[] initEvents;

  ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();

    available = new boolean[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      available[i] = true;
    }

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      initEvents[id] = new ShopEvent(ShopEvent.ARRIVAL, arrivalTime, id, serviceTime, available);
      id += 1;
    }
  }

  public Event[] getInitialEvents() {
    return initEvents;
  }
}
