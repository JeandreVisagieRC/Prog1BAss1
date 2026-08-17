import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HospitalManager manager = new HospitalManager();
        manager.initializeBeds();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {//main menu loop
            System.out.println("\n MediCare Hospital System");
            System.out.println("1. Register Patient");
            System.out.println("2. Allocate Bed");
            System.out.println("3. View Reports");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); //moves to new line

            switch (choice) {//switch case for menu options
                case 1:
                    System.out.println("\n Register New Patient");
                    System.out.print("Enter Patient ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // move to next line

                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Enter Medical Condition: ");
                    String medicalCondition = scanner.nextLine();

                    System.out.println("Select Patient Category:");
                    System.out.println("1. Inpatient");
                    System.out.println("2. Outpatient");
                    System.out.println("3. Emergency");
                    System.out.print("Choice: ");
                    int categoryChoice = scanner.nextInt();
                    scanner.nextLine(); // move to next line

                    Patient newPatient = null;

                    if (categoryChoice == 1) {//inpatient registration
                        System.out.print("Enter Ward Number: ");
                        String wardNumber = scanner.nextLine();
                        newPatient = new Inpatient(id, firstName, lastName, age, gender, medicalCondition, wardNumber);
                    } else if (categoryChoice == 2) {
                        newPatient = new Patient(id, firstName, lastName, age, gender, medicalCondition, PatientCategory.OUTPATIENT);
                    } else if (categoryChoice == 3) {
                        newPatient = new Patient(id, firstName, lastName, age, gender, medicalCondition, PatientCategory.EMERGENCY);
                    } else {
                        System.out.println("Invalid category selected. Registration cancelled.");
                        break;
                    }

                    if (manager.registerPatient(newPatient)) {//registering patient
                        System.out.println("Patient registered successfully!");
                    } else {
                        System.out.println("Error: Registration failed. Patient ID already exists.");
                    }
                    break;

                case 2://bed allocation
                    System.out.print("Enter Inpatient ID for bed allocation: ");
                    String allocId = scanner.nextLine();
                    if (manager.allocateBed(allocId)) {
                        System.out.println("Bed allocated successfully.");
                    } else {
                        System.out.println("Failed to allocate bed. Ensure patient is an Inpatient, has no active bed, and beds are available.");
                    }
                    break;

                case 3://report viewing
                    System.out.println("\n--- Ward Reports ---");
                    manager.displayWardLayout();
                    manager.displayWardOccupancyPercentage();
                    break;

                case 4://exit option
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default://invalid option handling
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
    
  
