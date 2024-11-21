import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Employee {
    private String employeeId;
    private ArrayList<String> timeRecords;

    public Employee(String employeeId) {
        this.employeeId = employeeId;
        this.timeRecords = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void addTimeRecord(String record) {
        timeRecords.add(record);
    }

    public ArrayList<String> getTimeRecords() {
        return timeRecords;
    }
}

public class DTRSystem {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nDTR System Menu:");
            System.out.println("1. Clock In");
            System.out.println("2. Clock Out");
            System.out.println("3. View Time Records");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    clockIn();
                    break;
                case 2:
                    clockOut();
                    break;
                case 3:
                    viewTimeRecords();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void clockIn() {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();
        Employee employee = findOrCreateEmployee(employeeId);
        String currentTime = dtf.format(LocalDateTime.now());
        employee.addTimeRecord("Clock In: " + currentTime);
        System.out.println("Clocked in at " + currentTime);
    }

    private static void clockOut() {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();
        Employee employee = findOrCreateEmployee(employeeId);
        String currentTime = dtf.format(LocalDateTime.now());
        employee.addTimeRecord("Clock Out: " + currentTime);
        System.out.println("Clocked out at " + currentTime);
    }

    private static void viewTimeRecords() {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();
        Employee employee = findEmployee(employeeId);
        if (employee != null) {
            System.out.println("Time Records for Employee ID: " + employeeId);
            for (String record : employee.getTimeRecords()) {
                System.out.println(record);
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static Employee findOrCreateEmployee(String employeeId) {
        Employee employee = findEmployee(employeeId);
        if (employee == null) {
            employee = new Employee(employeeId);
            employees.add(employee);
        }
        return employee;
    }

    private static Employee findEmployee(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }
}
//DTR V1.0.1 For Agoo 11/21/2024
