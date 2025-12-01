import java.io.*;
import java.util.*;

class BikeService {
    String date, parts; int mileage, cost;
    BikeService(String d, String p, int m, int c) {
        date = d; parts = p; mileage = m; cost = c;
    }
    public String toString() {
        return date + " | " + mileage + "km | " + parts + " | Rs" + cost;
    }
}

public class MotoTracker {
    static List<BikeService> services = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE = "services.txt";
    
    public static void main(String[] args) {
        loadServices();
        while(true) {
            System.out.println("\n1. Add Service | 2. View History | 3. Predict Next | 4. Report | 5. Exit");
            int choice = sc.nextInt(); sc.nextLine();
            switch(choice) {
                case 1: logService(); break;
                case 2: viewHistory(); break;
                case 3: predictNext(); break;
                case 4: generateReport(); break;
                case 5: saveServices(); return;
            }
        }
    }
    
    static void logService() {
        System.out.print("Date (yyyy-MM-dd): "); String date = sc.nextLine();
        System.out.print("Mileage: "); int mileage = sc.nextInt();
        sc.nextLine(); System.out.print("Parts: "); String parts = sc.nextLine();
        System.out.print("Cost: "); int cost = sc.nextInt();
        services.add(new BikeService(date, parts, mileage, cost));
        System.out.println("Service logged!");
    }
    
    static void viewHistory() {
        services.sort((a,b) -> a.date.compareTo(b.date));
        if(services.isEmpty()) { System.out.println("No services yet!"); return; }
        services.forEach(System.out::println);
    }
    
    static void predictNext() {
    System.out.print("Enter current mileage (km): ");
    int curr = sc.nextInt();

    int oilInterval = 3000;      // change to your real value
    int fullServiceInterval = 10000; // change if needed

    int nextOil = ((curr / oilInterval) + 1) * oilInterval;
    int nextFull = ((curr / fullServiceInterval) + 1) * fullServiceInterval;

    System.out.println("Next oil change at: " + nextOil + " km (about " + (nextOil - curr) + " km left)");
    System.out.println("Next full service at: " + nextFull + " km (about " + (nextFull - curr) + " km left)");
}

    
    static void generateReport() {
        int totalCost = services.stream().mapToInt(s -> s.cost).sum();
        System.out.println("Total services: " + services.size() + " | Total cost: Rs" + totalCost);
    }
    
    static void saveServices() {
        try(PrintWriter pw = new PrintWriter(FILE)) {
            for(BikeService s : services) {
                pw.println(s.date + "|" + s.mileage + "|" + s.parts + "|" + s.cost);
            }
        } catch(IOException e) { System.out.println("Save error"); }
    }
    
    static void loadServices() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                services.add(new BikeService(parts[0], parts[2], Integer.parseInt(parts[1]), Integer.parseInt(parts[3])));
            }
        } catch(IOException | ArrayIndexOutOfBoundsException e) { }
    }
}
