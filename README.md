# Simple Mobile-Communication System 📱📡

A Java-based simulation of a mobile network infrastructure using Object-Oriented Programming (OOP) principles. This project models how phones, SIM cards, and antennas interact to manage calls and signal coverage.

## 🚀 Project Overview
This system simulates real-world mobile communication constraints:
- **Coverage:** Phones must be within the range of at least one antenna.
- **Resource Management:** Calls depend on battery levels and SIM credit.
- **Network Topology:** Prevents "Isolated Antennas" by ensuring coverage overlap.

## 🛠️ Core Components

### 1. **Phone Class**
Manages the device state:
- Tracks `batteryLevel` and `Location`.
- Handles `isCurrentlyOnCall` status.
- **Mobility:** Includes logic to update location and verify signal continuity.

### 2. **SimCard Class**
Handles subscriber data:
- Stores `phoneNumber` and `creditBalance`.
- Validates if the SIM is `active`.
- Deducts credit only upon successful call establishment.

### 3. **Antenna Class**
Represents a cell tower:
- Defined by a `radius` (coverage area) and `capacity` (max simultaneous calls).
- Tracks `activeCalls` to prevent network congestion.

### 4. **Network Class**
The system controller:
- Manages all antennas.
- **No-Isolation Policy:** New antennas must overlap with existing ones to be added.
- Finds the **nearest available** antenna for any given phone location.

## 📋 Communication Rules (Business Logic)
A call is established only when **all** the following conditions are met:
- **Caller Side:** Battery > 0, SIM active, and sufficient credit.
- **Receiver Side:** Battery > 0, SIM active, and not already on another call.
- **Infrastructure:** Both phones must be within range of an antenna with free capacity.

## 💻 How to Run
1. Clone this repository:
   ```bash
   git clone [https://github.com/kafiall/Simple-Mobile-Communication-System.git]

2. Open the project in any Java IDE.
3. Run Main.java to see the console output of various simulation scenarios.

## 📝 Authors
1. **Allouni Abdelkafi**
2. **Hessainit Ramzi**
3. **Rahal Khalil**
4. **Hamani Ilyas**

> This project was developed as part of the **Object Oriented Programming 1** module
