### Motorcycle Maintenance Tracker (Java Console App)

###### **Description**

A simple Java console application that helps you track your motorcycle's service history, costs, and predicts the next service date based on mileage. All data is stored locally in a text file for easy access.

(or)

###### **Overview**

A simple Java console application to log vehicle services, track costs, and estimate upcoming maintenance based on odometer readings. Designed as a learning project for core Java (OOP, collections, file I/O)

###### **Features**

Add service entries with date, odometer, work done, and cost.

View full maintenance history for a vehicle.

Show total number of services and total money spent.

Suggest next service mileage based on configurable intervals.

Persist data to a text file so logs are kept between runs.

###### **Tech Stack**

Language: Java (JDK 8+).

Concepts: Classes/objects, ArrayList, streams, Scanner, exception handling, file I/O.

Environment: Any Java-supporting OS (Windows/Linux/macOS) with a terminal or IDE.

###### **How to Run**

Compile: javac VehicleTracker.java

Run: java VehicleTracker

Use the menu to:

1 Add service

2 View history

3 Show report / next service suggestion

###### **Sample Use**

> 1

Date: 2025-11-30

Mileage: 5000

Parts: Oil change

Cost: 800

> 2

2025-11-30 | 5000km | Oil change | Rs 800

###### **Customize For Your Bike**

In predictNext() method, set your service intervals (e.g., oil change every 3500 km, full service every 10000 km).

###### **Configuration**

Update service intervals (e.g., oil change every 5000 km) in the method that calculates the “next service” to match your vehicle’s real schedule.

###### **Example Intervals**

int oilInterval = 3500; // Oil change every 3500 km

int fullServiceInterval = 10000; // Full service every 10000 km

###### **Future Improvements**

- Support multiple vehicles.

- Add a Swing/JavaFX GUI.

- Store data in a database (e.g., SQLite or MySQL) instead of a flat file.

###### **Credits**

Open-source project inspired by real-world motorcycle use cases, for fresher/Java interview resumes.
