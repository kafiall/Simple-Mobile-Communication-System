# Mobile Communication System Simulation 📱📡

An Object-Oriented Programming (OOP) simulation of a mobile network infrastructure, implemented in **Java**. This project models the interaction between mobile devices, SIM cards, and network antennas to handle call logic and signal coverage.

## 🚀 Project Overview
This system simulates the real-world requirements for establishing mobile calls, focusing on:
- **Connectivity:** Phones must be within the range of at least one antenna.
- **Resources:** Calls require sufficient battery level and SIM card credit.
- **Network Logic:** Antennas have limited capacity and must be interconnected (not isolated).

## 🛠️ System Architecture

### 1. **Phone Class**
Handles device status including battery levels and location updates. It determines if a device is capable of initiating or receiving calls.

### 2. **SimCard Class**
Manages subscriber identity, activity status, and credit balance. It handles the financial logic of deducting credit per call.

### 3. **Antenna Class**
Represents network towers with a specific coverage radius and call capacity. It uses geometric distance to verify if a device is "In Range".

### 4. **Network Class**
The backbone of the system. It manages the list of antennas and ensures the "No Isolated Antenna" rule is applied when expanding the network.

## 📋 Requirements for a Successful Call
As per the project specifications, a call is established only if:
- [x] **Caller:** Has battery > 0, Active SIM, and Credit >= 1.
- [x] **Receiver:** Has battery > 0, Active SIM, and is not currently on another call.
- [x] **Coverage:** Both parties must be within the range of an available antenna.
- [x] **Capacity:** The connected antennas must have available slots (`activeCalls < capacity`).

## 💻 How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/repository-name.git](https://github.com/your-username/repository-name.git)
