import java.util.*;
import java.io.*;
import com.EMS.core.*;

public class Menu
{
    public static void main(String[] args) {
        try 
        {
            BufferedReader license_file = new BufferedReader(new FileReader( new File("../LICENSE")));
            String license = "";
            String line = "";
            while((line = license_file.readLine()) != null)
            {
                license += line + "\n";
            }
            
            license_file.close();
            System.out.println(license);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
        Scanner in = new Scanner(System.in);

        System.out.print("Do you agree to the terms and conditions?(y/n) ");
        char agreement = in.nextLine().charAt(0);

        if(agreement != 'y')
        {
            System.out.println("Thank you for considering our services!\nWe hope you have a nice day ahead!");
            in.close();
            return;
        }

        System.out.println("#### Employee Management System ####");
        System.out.println("########### Version 1.0 ############");
        System.out.println();

        Organization org[] = new Organization[1000];
        int numOrganizationsRegistered = 0;
        Employee emp[] = new Employee[1000];
        int numEmployeesRegistered = 0;

        while(true)
        {
            printMenu();
            int command = in.nextInt();
            in.nextLine();
            if(command == 1) numOrganizationsRegistered = registerOrganization(org, in, numOrganizationsRegistered);
            else if(command == 2) numEmployeesRegistered = registerEmployee(emp, org, in, numEmployeesRegistered, numOrganizationsRegistered);
            else if(command == 3) numEmployeesRegistered = removeEmployee(emp, numEmployeesRegistered, in);
            else if(command == 4) numOrganizationsRegistered = removeOrganization(org, numOrganizationsRegistered, in);
            else if(command == 5) printOrganization(org, numOrganizationsRegistered, in);
            else if(command == 6) printEmployee(emp, numEmployeesRegistered, in);
            else if(command == 7) saveOrganization(org, numOrganizationsRegistered, in);
            else if(command == 8) saveEmployee(emp, numEmployeesRegistered, in);
            else break;
        }
    }

    private static void saveEmployee(Employee[] emp, int numEmployeesRegistered, Scanner in)
    {
        String path = ".\\emp_data";
        for(int i=0;i<numEmployeesRegistered;i++)
        {
            emp[i].serialize(path+"_raw", emp[i].getId() + ".ser");
            emp[i].save(path, emp[i].getId() + ".txt");
        }
        System.out.println("\nAll the details of employees have been stored in a folder 'emp_data'\n");
    }

    private static void saveOrganization(Organization[] org, int numOrganizationsRegistered, Scanner in)
    {
        String path = ".\\org_data";
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            org[i].serialize(path+"_raw", org[i].getName() + ".ser");
            org[i].save(path, org[i].getName() + ".txt");
        }
        System.out.println("\nAll the details of organization have been stored in a folder 'org_data'\n");
    }

    private static void printEmployee(Employee[] emp, int numEmployeesRegistered, Scanner in)
    {
        System.out.print("\nEnter the id of the employee you want to know about: ");
        String id = in.nextLine();
        for(int i=0;i<numEmployeesRegistered;i++)
        {
            if(emp[i].getId().equals(id))
            {
                System.out.println();
                System.out.println("Employee Found!\n");
                System.out.println("***********************************");
                System.out.println();
                emp[i].pPrint(true, true);
                System.out.println();
                System.out.println("***********************************\n");
                return;
            }
        }
        System.out.println("\nEmployee not found!\n");
        return;
    }

    private static void printOrganization(Organization org[], int numOrganizationsRegistered, Scanner in)
    {
        System.out.print("\nEnter the name of the organization you want to know about: ");
        String name = in.nextLine();
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            if(org[i].getName().toLowerCase().equals(name.toLowerCase()))
            {
                System.out.println("\nOrganization Found!\n");
                System.out.println("***********************************");
                System.out.println();
                org[i].pPrint(true);
                System.out.println();
                System.out.println("***********************************\n");
                return;
            }
        }
        System.out.println("Organization not found!");
        return;
    }

    private static int removeOrganization(Organization[] org, int numOrganizationsRegistered, Scanner in) {
        return 0;
    }

    public static void printMenu()
    {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("Enter 1 to register organization.");
        System.out.println("Enter 2 to register employee.");
        System.out.println("Enter 3 to remove an employee.");
        System.out.println("Enter 4 to remove an organization.");
        System.out.println("Enter 5 to print organization details.");
        System.out.println("Enter 6 to print employee details.");
        System.out.println("Enter 7 to save all the organizations.");
        System.out.println("Enter 8 to save all the employees.");
        System.out.println("Enter any number >8 to exit.");
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.print("Enter command: ");
    }

    public static int registerOrganization(Organization[] org, Scanner in, int numOrganizationsRegistered)
    {
        System.out.println("\n\n*********** Registering Organization **************\n");
        Organization newOrg = new Organization();
        System.out.print("\nEnter the name of the organization: ");
        String name = in.nextLine();
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            if(org[i].getName().toLowerCase().equals(name.toLowerCase()))
            {
                System.out.println("\nOrganization already registered! Here are the details.\n");
                org[i].pPrint(false);
                return numOrganizationsRegistered;
            }
        }
        newOrg.setName(name);
        System.out.print("Enter the link to organization webpage: ");
        String link = in.nextLine();
        newOrg.setLink(link);
        System.out.print("Enter the address of the company: ");
        String address = in.nextLine();
        newOrg.setAddress(address);
        System.out.print("Enter the number of employees in the company: ");
        int numEmployees = in.nextInt();in.nextLine();
        newOrg.setNumEmployees(numEmployees);
        System.out.print("Enter the date of establishment: (dd/MM/yyyy) ");
        String date = in.nextLine();
        newOrg.setDateOfEstablishment(date, "dd/MM/yyyy");
        System.out.println("\nOrganization registered successfully!\n");
        org[numOrganizationsRegistered] = newOrg;
        return ++numOrganizationsRegistered;
    }

    public static int registerEmployee(Employee[] emp, Organization org[], Scanner in, int numEmployeesRegistered, int numOrganizationsRegistered)
    {
        System.out.println("\n\n*********** Registering Employee **************\n");
        Employee newEmp = new Employee();
        System.out.print("\nEnter the organization of the employee: ");
        String orgName = in.nextLine();
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            if(org[i].getName().toLowerCase().equals(orgName.toLowerCase()))
            {
                newEmp.setOrg(org[i]);
                break;
            }
            if(i == numOrganizationsRegistered-1)
            {
                System.out.println("\nOrganization not found! First register organization.");
                return numEmployeesRegistered;
            }
        }
        System.out.print("Enter the id of the employee: ");
        String id = in.nextLine();
        for(int i=0;i<newEmp.getOrg().getNumAppointed();i++)
        {
            if(newEmp.getOrg().getEmployeeDetails()[i].getId().equals(id))
            {
                System.out.println("\nThis employee is already registed. Here are the registration details.\n");
                newEmp.getOrg().getEmployeeDetails()[i].pPrint(false, true);
                return numEmployeesRegistered;
            }
        }
        newEmp.setId(id);
        System.out.print("Enter the name of the employee: ");
        String name = in.nextLine();
        newEmp.setName(name);
        System.out.print("Enter the address of the employee: ");
        String address = in.nextLine();
        newEmp.setAddress(address);
        System.out.print("Enter the occupation of the employee: ");
        String occupation = in.nextLine();
        newEmp.setOccupation(occupation);
        System.out.print("Enter the account of the employee: ");
        String account = in.nextLine();
        newEmp.setAccount(account);
        System.out.print("Enter the salary of the employee: ");
        double salary = in.nextDouble();
        newEmp.setSalary(salary);
        System.out.print("Enter the bonus of the employee: ");
        double bonus = in.nextDouble();in.nextLine();
        newEmp.setBonus(bonus);
        System.out.println("\nEmployee registered successfully!\n");
        emp[numEmployeesRegistered] = newEmp;
        return ++numEmployeesRegistered;
    }

    public static int removeEmployee(Employee[] emp, int numEmployeesRegistered, Scanner in)
    {
        System.out.print("Enter the id of the employee to be removed: ");
        String id = in.nextLine();
        Employee newEmp[] = new Employee[1000];
        int j=0;
        for(int i=0;i<numEmployeesRegistered;i++)
        {
            if(emp[i].getId().equals(id))
            {
                continue;
            }
            newEmp[j++] = emp[i];
        }
        if(j == numEmployeesRegistered)
        {
            System.out.println("\nEmployee not found!\n");
            return numEmployeesRegistered;
        }
        System.out.println("\nEmployee successfully registered!\n");
        return --numEmployeesRegistered;
    }

    
}