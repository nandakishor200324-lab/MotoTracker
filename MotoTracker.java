import java.io.*;
import java.util.*;

// Model one bike service entry (date, work done, mileage, cost)
class BikeService {
    String date, parts; 
    int mileage, cost;

    // Constructor to initialize a service record
    BikeService(String d, String p, int m, int c) {
        date = d; parts = p; mileage = m; cost = c;
    }

    // Return a nicely formatted string for console output
    public String toString() {
        return date + " | " + mileage + "km | " + parts + " | Rs" + cost;
    }
}

// Main application class for Motorcycle Maintenance Tracker
public class MotoTracker {
    // In‑memory list of all service records
    static List<BikeService> services = new ArrayList<>();
    // Scanner is used for input
    static Scanner sc = new Scanner(System.in);
    // File used to store service data persistently
    static final String FILE = "services.txt";
    
    // Entry point: show menu loop and handle user choices
    public static void main(String[] args) {
        loadServices(); // Load existing data from file (if any)
        while(true) {
            System.out.println("\n1. Add Service | 2. View History | 3. Predict Next | 4. Report | 5. Exit");
            int choice = sc.nextInt(); 
            sc.nextLine(); // consume leftover newline
            switch(choice) {
                case 1: logService(); break; // Add new service record
                case 2: viewHistory(); break; // Show all past services
                case 3: predictNext(); break; // Predict next service based on mileage
                case 4: generateReport(); break; // Show summary (count + total cost)
                case 5: saveServices(); return; // Save to file and exit
            }
        }
    }
    
    // Take service details from user and add to the list
    static void logService() {
        System.out.print("Date (yyyy-MM-dd): "); 
        String date = sc.nextLine();

        System.out.print("Mileage: "); 
        int mileage = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Parts: "); 
        String parts = sc.nextLine();

        System.out.print("Cost: "); 
        int cost = sc.nextInt();

        services.add(new BikeService(date, parts, mileage, cost));
        System.out.println("Service logged!");
    }
    
    // Display all services sorted by date
    static void viewHistory() {
        // Sort by date string (yyyy-MM-dd format works lexicographically)
        services.sort((a,b) -> a.date.compareTo(b.date));
        if(services.isEmpty()) {
        System.out.println("No services yet!");
        return; 
        }
        // Print each service using BikeService.toString()
        services.forEach(System.out::println);
    }
    
    // Predict next oil change and full service based on current mileage
    static void predictNext() {
    System.out.print("Enter current mileage (km): ");
    int curr = sc.nextInt();
    // Configurable intervals (can be adjusted per bike)
    int oilInterval = 3000;      // change to your real value
    int fullServiceInterval = 10000; // change if needed

    // Compute next multiples of interval greater than current km
    int nextOil = ((curr / oilInterval) + 1) * oilInterval;
    int nextFull = ((curr / fullServiceInterval) + 1) * fullServiceInterval;

    System.out.println("Next oil change at: " + nextOil + " km (about " + (nextOil - curr) + " km left)");
    System.out.println("Next full service at: " + nextFull + " km (about " + (nextFull - curr) + " km left)");
}

    // Show total number of services and total money spent
    static void generateReport() {
        int totalCost = services.stream().mapToInt(s -> s.cost).sum();
        System.out.println("Total services: " + services.size() + " | Total cost: Rs" + totalCost);
    }
    
    // Write all service records to services.txt for persistence
    static void saveServices() {
        try(PrintWriter pw = new PrintWriter(FILE)) {
            for(BikeService s : services) {
                // Store fields separated by |
                pw.println(s.date + "|" + s.mileage + "|" + s.parts + "|" + s.cost);
            }
        } 
        catch(IOException e) { 
            System.out.println("Save error");
        }
    }
    
    // Read service records from services.txt (if file exists)
    static void loadServices() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            // Read each line and reconstruct BikeService objects
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                services.add(new BikeService(
                    parts[0],                   // date
                    parts[2],                   // parts/work done
                    Integer.parseInt(parts[1]), // mileage
                    Integer.parseInt(parts[3])  // cost
                    ));
            }
        } catch(IOException | ArrayIndexOutOfBoundsException e) { 
            // If file not found or bad line format, ignore and start with empty list
        }
    }
}
