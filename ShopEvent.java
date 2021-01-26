class ShopEvent extends Event {
  int customerId;
  int eventType;
  double serviceTime;
  boolean available[];
  int counterId;
  public static final int ARRIVAL = 0;
  public static final int SERVICE_BEGIN = 1;
  public static final int SERVICE_END = 2;
  public static final int DEPARTURE = 3;

  public ShopEvent(int eventType, double time, int customerId, double serviceTime, boolean available[]) {
    super(time);
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.available = available;
    this.eventType = eventType;
  }

  public ShopEvent(int eventType, double time, int customerId, double serviceTime, int counterId, boolean available[]) {
    super(time);
    this.customerId = customerId;
    this.counterId = counterId;
    this.eventType = eventType;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  public ShopEvent(int eventType, double time, int customerId, int counterId, boolean available[]) {
    super(time);
    this.customerId = customerId;
    this.eventType = eventType;
    this.counterId = counterId;
    this.available = available;
  }

  public ShopEvent(int eventType, double time, int customerId) {
    super(time);
    this.customerId = customerId;
    this.eventType = eventType;
  }


  @Override
  public String toString() {
    if (this.eventType == ShopEvent.ARRIVAL) {
      return super.toString() + String.format(": Customer %d arrives", this.customerId);
    } else if (this.eventType == ShopEvent.SERVICE_BEGIN) {
      return super.toString() + String.format(": Customer %d service begin (by Counter %d)", this.customerId, this.counterId);
    } else if (this.eventType == ShopEvent.SERVICE_END) {
      return super.toString() + String.format(": Customer %d service done (by Counter %d)", this.customerId, this.counterId);
    } else if (this.eventType == ShopEvent.DEPARTURE) {
      return super.toString() + String.format(": Customer %d departed", this.customerId);
    }
    return "Unknown event type";
  }

  @Override
  public Event[] simulate() {
    if (this.eventType == ShopEvent.ARRIVAL) {
      int counter = -1;
      for (int i = 0; i < available.length; i += 1) {
        if (available[i]) {
          counter = i;
          break;
        }
      }
      if (counter == -1) {
        return new Event[] { 
          new ShopEvent(ShopEvent.DEPARTURE, this.time, this.customerId)
        };
      } else {
        return new Event[] { 
          new ShopEvent(ShopEvent.SERVICE_BEGIN, this.time, this.customerId, this.serviceTime, counter, this.available)
        };
      }
    }
    else if (this.eventType == ShopEvent.SERVICE_BEGIN) {
      available[this.counterId] = false;
      return new Event[] { 
        new ShopEvent(ShopEvent.SERVICE_END, this.time + this.serviceTime, this.customerId, this.counterId, this.available)
      };
    }
    else if (this.eventType == ShopEvent.SERVICE_END) {
      available[this.counterId] = true;
      return new Event[] { 
        new ShopEvent(ShopEvent.DEPARTURE, this.time, this.customerId),
      };
    }
    else if (this.eventType == ShopEvent.DEPARTURE) {
      // do nothing
    }
    return new Event[] {};
  }
}
