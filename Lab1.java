import java.util.Scanner;

class Lab1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Simulation simulation = new ShopSimulation(sc);
    new Simulator(simulation).run();
    sc.close();
  }
}
