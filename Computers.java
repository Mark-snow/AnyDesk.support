package Siginon;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.util.Scanner;

public class Computers {

    private String department;
    private String serNumber;
    private String upn;
    private String model;
    private String ADaddress;
    private String ip;

    Scanner reader = new Scanner(System.in);

    public Computers(){
       System.out.println("Enter the serial number of the new computer: ");
       serNumber = reader.nextLine();

       System.out.println("Enter the device model: ");
       model = reader.nextLine();

       System.out.println("What department is the new computer in? ");
       department = reader.nextLine();

       System.out.println("Enter the UPN that will be used in this new computer: ");
       upn = reader.nextLine();

       System.out.println("Enter the AnyDesk address of the new computer: ");
       ADaddress = reader.nextLine();

    }

    public void newComputer(){
        
        String confirmation = "Are you sure you want to add a new computer? \nPress 1 for Yes \nPress 2 for No"; 
        int response;

        System.out.println(confirmation);
        response = reader.nextInt();

        if (response == 1)
        {
            this.newComputer();
        }
        if (response == 2)
        {
            return;
        }
        else
        {
            System.out.println("Invalid choice");
            this.newComputer();
        }
    }

    public void getFullInfo(){
        System.out.println("1. Department  : " + department + "\n2. Serial number  : " + serNumber + "\n3. Device model  : " + model + "\n4. UPN  : " + upn + "\n5. AnyDesk address  : " + ADaddress);
    
    }
    
    public void getInfo(){
        int choice;
        
        System.out.println("What information do you want to get? \n1. Department \n2. Serial number \n3.Device model \n4.UPN \5.AnyDesk address \n6.ip");
        choice = reader.nextInt();

        if (choice == 1)
        {System.out.println(this.department);}
        if (choice == 2)
        {System.out.println(this.serNumber);}
        if (choice == 3)
        {System.out.println(this.model);}
        if (choice == 4)
        {System.out.println(this.upn);}
        if (choice == 5)
        {System.out.println(this.ADaddress);} 
        if (choice == 6)
        {System.out.println(this.ip);}
        else 
        {
            System.out.println("Invalid choice. Try again.");
            this.getInfo();
        }           
    }

    public void openAnyDesk(){
        
        StringSelection stringSelection = new StringSelection(this.ADaddress);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        String batchFilePath = "C:/Projects/Siginon/openAD.bat";
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", batchFilePath);

        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        try {
            Process process = processBuilder.start();
            // If you need to wait for the process to finish:
            int exitCode = process.waitFor();
            System.out.println("Batch file exited with code: " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args){
        Computers obj1;
        obj1 = new Computers();

        obj1.openAnyDesk();
    }
}



