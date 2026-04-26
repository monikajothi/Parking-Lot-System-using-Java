import java.util.Scanner;

public class ParkingLotApp {

    static String[] parkingSlots;
    static int totalSlots;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of slots: ");
        totalSlots = sc.nextInt();
        parkingSlots = new String[totalSlots];
        for (int i = 0; i < totalSlots.length; i++) {
            System.out.println("a");
        }
        System.out.println("Parking allotted with " + totalSlots + " slots");
        

        while (true) {
            System.out.print("\nEnter command (P/L/S/Q/K): ");
            char command = sc.next().toUpperCase().charAt(0);

            switch (command) {
                case 'P':
                    parkVehicle(sc);
                    break;

                case 'L':
                    leaveSlot(sc);
                    break;

                case 'S':
                    showStatus();
                    break;

                case 'K':
                    showSlot(sc);
                    break;

                case 'Q':
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid command!");
            }
        }
    }
    static void showSlot(Scanner sc){
        System.out.print("Enter vehicle number: ");
        String vehicleNumber = sc.next();

        for (int i = 0; i < totalSlots; i++) {
            if (vehicleNumber.equals(parkingSlots[i])) {
                System.out.println("Vehicle " + vehicleNumber + " is parked at slot " + (i + 1));
                return;
            }
        }

        System.out.println("Not found: Vehicle " + vehicleNumber + " is not parked in the lot");
    }
    static void parkVehicle(Scanner sc) {
        int slot = findNearestEmptySlot();

        if (slot == -1) {
            System.out.println("Error: Parking is full");
            return;
        }

        System.out.print("Enter vehicle number: ");
        String vehicleNumber = sc.next();

        parkingSlots[slot] = vehicleNumber;
        System.out.println("Vehicle " + vehicleNumber + " parked at slot " + (slot + 1));

    }

    static int findNearestEmptySlot() {
        for (int i = 0; i < totalSlots; i++) {
            if (parkingSlots[i] == null) {
                return i;
            }
        }
        return -1; 
    }

    static void leaveSlot(Scanner sc) {
        System.out.print("Enter slot number to vacate: ");
        int slotNumber = sc.nextInt();

        if (slotNumber < 1 || slotNumber > totalSlots) {
            System.out.println("Error: Invalid slot number, valid range: 1-" + totalSlots);
            return;
        }

        int index = slotNumber - 1;

        if (parkingSlots[index] == null) {
            System.out.println("Error: Slot " + slotNumber + " is already empty");
        } else {
            System.out.println("Vehicle " + parkingSlots[index] + " has left slot " + slotNumber);
            parkingSlots[index] = null;
        }
    }

    static void showStatus() {
        System.out.println("\nParking Status:");
        int occupied = showStatusRec(0);
        System.out.println("Occupied " + occupied + "/" + totalSlots + " slots");
    }

    static int showStatusRec(int i) {
        if (i >= totalSlots) return 0;
        int occupied = 0;
        if (parkingSlots[i] == null) {
            System.out.println("Slot " + (i + 1) + ": Empty");
        } else {
            System.out.println("Slot " + (i + 1) + ": " + parkingSlots[i]);
            occupied = 1;
        }
        return occupied + showStatusRec(i + 1);
    }


}

