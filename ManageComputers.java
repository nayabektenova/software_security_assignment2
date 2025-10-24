//Manage Computers program: maintains an ArrayList of ComputerLike objects
//can be either Laptop or Desktop
//Includes whitelist-based input validation for all computer attributes

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class ManageComputers {

    //Whitelists for input validation - only these values are allowed
    private static final List<String> VALID_CPU = Arrays.asList("i5", "i7");
    private static final List<String> VALID_RAM = Arrays.asList("16", "32");
    private static final List<String> VALID_DISK = Arrays.asList("512", "1024");
    private static final List<String> VALID_GPU = Arrays.asList("Nvidia", "AMD");
    private static final List<String> VALID_SCREEN = Arrays.asList("13", "14");

    public static void main(String args[]) {

        //This ArrayList will hold all the computers in the system
        //Uses ComputerLike interface so both Desktop and Laptop can be stored
        ArrayList<ComputerLike> computers = new ArrayList<ComputerLike>(); 

        Scanner s = new Scanner(System.in);
        String menuOption="";

        do { //Start of main program loop

            //Show computer data in ArrayList
            showComputers(computers); 

            //Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch(menuOption) {
                //Add new computer
                case "a": 
                    addComputer(computers,s);
                    break;

                //Delete a computer    
                case "d": 
                    deleteComputer(computers,s);
                    break;

                //Edit a computer    
                case "e": 
                    editComputer(computers, s);
                    break;

            }

        } while ( ! menuOption.equals("x") ); //Stop when "x" is entered

        s.close(); //Close keyboard scanner

    } //End of main

    //-----------------------------
    //Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption="";

        //Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        //Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); //Make lower case for comparison purposes

        return menuOption;
    } //End of getMenuSelection

    //-----------------------------
    //Show data for all laptops and desktops stored in ArrayList
    private static void showComputers(ArrayList<ComputerLike> computers) {
        int computerListNumber=0; //This variable is used to hold the "list number" for each computer, starting at 1.

        System.out.println("=========");

        System.out.println("LIST OF COMPUTERS:-");

        for (ComputerLike c: computers) {

            computerListNumber++; //Increment list number for each computer

            //Call overridden toString() method for current object to get and display its data
            System.out.println(computerListNumber + ": " + c.toString());
        }

        System.out.println("=========");

    } //End of showComputers

    //-----------------------------
    //Add a new Laptop or Desktop computer to the ArrayList
    private static void addComputer(ArrayList<ComputerLike> computers, Scanner s) {
        String computerType="";

        System.out.println("ADDING COMPUTER:-");

        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType=s.nextLine();
        computerType=computerType.toLowerCase(); //Convert to lower case for comparison purposes

        switch(computerType) {

            //Add a laptop
            case "l": 

                //Get validated CPU, RAM and Disk info
                String laptopCPU = getValidatedInput(s, "CPU", VALID_CPU);
                String laptopRAM = getValidatedInput(s, "RAM", VALID_RAM);
                String laptopDisk = getValidatedInput(s, "Disk", VALID_DISK);
                String screenSize = getValidatedInput(s, "screen size", VALID_SCREEN);

                //Add new Laptop to ArrayList in main() method
                computers.add(new Laptop(laptopCPU, laptopRAM, laptopDisk, screenSize)); 

                break;
            
            //Add a desktop    
            case "d": 

                //Get validated CPU, RAM and Disk info
                String desktopCPU = getValidatedInput(s, "CPU", VALID_CPU);
                String desktopRAM = getValidatedInput(s, "RAM", VALID_RAM);
                String desktopDisk = getValidatedInput(s, "Disk", VALID_DISK);
                String GPUType = getValidatedInput(s, "GPU", VALID_GPU);

                //Add new Desktop to ArrayList in main() method
                computers.add(new Desktop(desktopCPU, desktopRAM, desktopDisk, GPUType)); 

                break;

            //Invalid computer type to add entered
            default:

                System.out.println("Invalid computer type entered!");

        }
    } //End of addComputer

    //-----------------------------
    //Delete a specified computer from the ArrayList
    private static void deleteComputer(ArrayList<ComputerLike> computers, Scanner s) {
        int computerListNumberToDelete=0;

        System.out.println("DELETE COMPUTER:-");

        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        //Check if computer list number is valid before deleting computer from list
        if (computerListNumberToDelete>=1 && computerListNumberToDelete<=computers.size()) {
            //Subtract 1 to get ArrayList index from on-screen list number to create correct index in ArrayList to delete
            computers.remove(computerListNumberToDelete-1); 
        }   
        else {
            System.out.println("Invalid computer number entered!");
        }

    } //End of deleteComputer

    //-----------------------------
    //Edit a computer. Since Laptop and Desktop are now immutable classes, we cannot use setter methods.
    //Instead, we get new data values and create a new object with the updated values, then replace the
    //old object in the ArrayList with the new one.
    private static void editComputer(ArrayList<ComputerLike> computers, Scanner s) {
        int computerListNumberToEdit=0;
        String computerType="";

        System.out.println("EDIT COMPUTER:-");

        System.out.print("Enter number of computer to edit:");
        computerListNumberToEdit = Integer.parseInt(s.nextLine());

        //Check that computerListNumberToEdit is valid first
        if (computerListNumberToEdit>=1 && computerListNumberToEdit<=computers.size()) {

            //Determine exact type of computer being edited
            //Subtract 1 to get ArrayList index from on-screen list number
            if (computers.get(computerListNumberToEdit-1) instanceof Laptop) { 
                computerType="laptop";
            }
            //Subtract 1 to get ArrayList index from on-screen list number
            else if (computers.get(computerListNumberToEdit-1) instanceof Desktop) { 
                computerType="desktop";
            }

        
            //Edit computer
            switch(computerType) {

                //Editing a laptop
                case "laptop": 
            
                    System.out.println("Editing a Laptop:");

                    //Get validated new values for all attributes
                    String newLaptopCPU = getValidatedInput(s, "CPU", VALID_CPU);
                    String newLaptopRAM = getValidatedInput(s, "RAM", VALID_RAM);
                    String newLaptopDisk = getValidatedInput(s, "Disk", VALID_DISK);
                    String newScreenSize = getValidatedInput(s, "screen size", VALID_SCREEN);

                    //Create new immutable Laptop object with the new values
                    Laptop newLaptop = new Laptop(newLaptopCPU, newLaptopRAM, newLaptopDisk, newScreenSize);

                    //Replace the old Laptop object in the ArrayList with the new one
                    computers.set(computerListNumberToEdit-1, newLaptop);

                    break;

                //Editing a desktop
                case "desktop": 

                    System.out.println("Editing a Desktop:");

                    //Get validated new values for all attributes
                    String newDesktopCPU = getValidatedInput(s, "CPU", VALID_CPU);
                    String newDesktopRAM = getValidatedInput(s, "RAM", VALID_RAM);
                    String newDesktopDisk = getValidatedInput(s, "Disk", VALID_DISK);
                    String newGPUType = getValidatedInput(s, "GPU", VALID_GPU);

                    //Create new immutable Desktop object with the new values
                    Desktop newDesktop = new Desktop(newDesktopCPU, newDesktopRAM, newDesktopDisk, newGPUType);

                    //Replace the old Desktop object in the ArrayList with the new one
                    computers.set(computerListNumberToEdit-1, newDesktop);

                    break;

            }

        }
        else {
            System.out.println("Invalid computer number entered!");
        }


    } //End of editComputer

    //-----------------------------
    //Helper method to get validated input based on a whitelist
    //Loops until valid input is received from the user
    private static String getValidatedInput(Scanner s, String fieldName, List<String> validValues) {
        String input = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter " + fieldName + ":");
            input = s.nextLine();

            //Check if input is in the whitelist
            if (validValues.contains(input)) {
                valid = true;
            } else {
                System.out.println("Invalid " + fieldName + "! Valid values are: " + validValues);
                System.out.println("Please try again.");
            }
        }

        return input;
    } //End of getValidatedInput


} //End of ManageComputers class