import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

// Class to represent a Student
class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Class to represent a Room
class Room {
    private final int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}

// Class to manage the Hostel Room Allocation
class Hostel {
    private final List<Room> rooms;
    private final Map<Room, List<Student>> roomAllocations;

    public Hostel(int numberOfRooms) {
        rooms = new ArrayList<>();
        roomAllocations = new HashMap<>();

        // Create rooms
        for (int i = 1; i <= numberOfRooms; i++) {
            Room room = new Room(i);
            rooms.add(room);
            roomAllocations.put(room, new ArrayList<>());
        }
    }

    public void allocateRooms(List<Student> students) {
        Random random = new Random();
        for (Student student : students) {
            // Randomly select a room from the existing rooms
            Room room = rooms.get(random.nextInt(rooms.size()));
            roomAllocations.get(room).add(student); // This will now work correctly
        }
    }

    public void displayAllocations() {
        System.out.println("Room Allocations:");
        for (Map.Entry<Room, List<Student>> entry : roomAllocations.entrySet()) {
            Room room = entry.getKey();
            List<Student> allocatedStudents = entry.getValue();
            System.out.print("Room " + room.getRoomNumber() + ": ");
            if (allocatedStudents.isEmpty()) {
                System.out.println("No students allocated.");
            } else {
                for (Student student : allocatedStudents) {
                    System.out.println(student.getName() + " ");
                }
                System.out.println();
            }
        }
    }
}

// Main class to run the application
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rooms: ");
        int numberOfRooms = scanner.nextInt();
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.print("Enter the name of student " + i + ": ");
            String name = scanner.nextLine();
            students.add(new Student(name));
        }

        Hostel hostel = new Hostel(numberOfRooms);
        hostel.allocateRooms(students);
        hostel.displayAllocations();
    }
}