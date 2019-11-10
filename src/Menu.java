import java.util.*;
import java.io.*;
import com.EMS.core.*;

public class Menu
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        try
        {
            BufferedReader license_file = new BufferedReader(new FileReader( new File("../../LICENSE")));
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
            else if(command == 3)
            {
                int numArr[] = removeOrganization(org, numOrganizationsRegistered, emp, numEmployeesRegistered, in);
                numEmployeesRegistered = numArr[0];
                numOrganizationsRegistered = numArr[1];
            }
            else if(command == 4) numEmployeesRegistered = removeEmployee(org, numOrganizationsRegistered, emp, numEmployeesRegistered, in);
            else if(command == 5) printOrganization(org, numOrganizationsRegistered, in);
            else if(command == 6) printEmployee(emp, numEmployeesRegistered, in);
            else if(command == 7) saveOrganization(org, numOrganizationsRegistered, in);
            else if(command == 8) saveEmployee(emp, numEmployeesRegistered, in);
            else if(command == 9)
            {
                numEmployeesRegistered = retriveEmployees(emp);
                numOrganizationsRegistered = retriveOrganizations(org);
            }
            else if(command == 10) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else break;
        }
    }

    public static void printMenu()
    {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                     #### Employee Management System ####                    ");
        System.out.println("                     ########### Version 1.0 ############                    ");
        System.out.println("Enter 1 to register organization.");
        System.out.println("Enter 2 to register employee.");
        System.out.println("Enter 3 to remove an organization.");
        System.out.println("Enter 4 to remove an employee.");
        System.out.println("Enter 5 to print organization details.");
        System.out.println("Enter 6 to print employee details.");
        System.out.println("Enter 7 to save all the organizations.");
        System.out.println("Enter 8 to save all the employees.");
        System.out.println("Enter 9 to retrive all the previously stored employees and organizations.");
        System.out.println("Enter 10 to clear console.");
        System.out.println("Enter any number >11 to exit.");
        System.out.println("-----------------------------------------------------------------------------");
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
        try
        {
            newOrg.setLink(link);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return numOrganizationsRegistered;
        }
        System.out.print("Enter the address of the company: ");
        String address = in.nextLine();
        newOrg.setAddress(address);
        System.out.print("Enter the number of employees in the company: ");
        int numEmployees = in.nextInt();in.nextLine();
        try
        {
            newOrg.setNumEmployees(numEmployees);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return numOrganizationsRegistered;
        }
        System.out.print("Enter the date of establishment: (dd/MM/yyyy) ");
        String date = in.nextLine();
        try
        {
            newOrg.setDateOfEstablishment(date, "dd/MM/yyyy");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return numOrganizationsRegistered;
        }
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

    public static int[] removeOrganization(Organization[] org, int numOrganizationsRegistered, Employee[] emp, int numEmployeesRegistered, Scanner in)
    {
        System.out.print("Enter the name of the organization to be removed: ");
        String name = in.nextLine();
        Organization newOrg[] = new Organization[1000];
        int j=0;
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            if(org[i].getName().toLowerCase().equals(name.toLowerCase()))
            {
                while(org[i].getNumAppointed() != 0)
                {
                    String id = org[i].getEmployeeDetails()[0].getId();
                    org[i].removeEmployee(id);
                    numEmployeesRegistered = removeEmployee(emp, numEmployeesRegistered, id);
                    removeEmployeeFiles(id);
                }
                continue;
            }
            newOrg[j++] = org[i];
        }
        if(j == numOrganizationsRegistered)
        {
            System.out.println("\nOrganization not found!\n");
            int arr[] = {numEmployeesRegistered, numOrganizationsRegistered};
            return arr;
        }
        System.out.println("\nOrganization successfully deleted!\n");
        int arr[] = {numEmployeesRegistered, --numOrganizationsRegistered};
        removeOrganizationFiles(name);
        return arr;
    }

    private static void removeOrganizationFiles(String name)
    {
        try
        {
            String path = ".\\org_data";
            String filename = name;
            File f = new File(path + "\\" + filename + ".txt");
            File f_raw = new File(path + "_raw" + "\\" + filename + ".ser");
            f.delete();
            f_raw.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int removeEmployee(Organization org[], int numOrganizationsRegistered, Employee[] emp, int numEmployeesRegistered, Scanner in)
    {
        System.out.println("\nEnter the organization of the employee: ");
        String name = in.nextLine();
        int index = -1;
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            if(org[i].getName().toLowerCase().equals(name.toLowerCase()))
            {
                index = i;
                break;
            }
            if(i == numOrganizationsRegistered-1)
            {
                System.out.println("\nOrganization not found!\n");
                return numEmployeesRegistered;
            }
        }
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
        org[index].removeEmployee(id);
        System.out.println("\nEmployee successfully deleted!\n");
        removeEmployeeFiles(id);
        return --numEmployeesRegistered;
    }

    public static int removeEmployee(Organization org[], int numOrganizationsRegistered, Employee[] emp, int numEmployeesRegistered, String name, String id)
    {
        int index = -1;
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            if(org[i].getName().toLowerCase().equals(name.toLowerCase()))
            {
                index = i;
                break;
            }
            if(i == numOrganizationsRegistered-1)
            {
                System.out.println("\nOrganization not found!\n");
                return numEmployeesRegistered;
            }
        }
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
        org[index].removeEmployee(id);
        System.out.println("\nEmployee successfully deleted!\n");
        removeEmployeeFiles(id);
        return --numEmployeesRegistered;
    }

    private static int removeEmployee(Employee[] emp, int numEmployeesRegistered, String id) 
    {
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
        System.out.println("\nEmployee successfully deleted!\n");
        removeEmployeeFiles(id);
        return --numEmployeesRegistered;
    }

    private static void removeEmployeeFiles(String id)
    {
        try
        {
            String path = ".\\emp_data";
            String filename = id;
            File f = new File(path + "\\" + filename + ".txt");
            File f_raw = new File(path + "_raw" + "\\" + filename + ".ser");
            f.delete();
            f_raw.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void printOrganization(Organization org[], int numOrganizationsRegistered, Scanner in)
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

    public static void printEmployee(Employee[] emp, int numEmployeesRegistered, Scanner in)
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

    public static void saveOrganization(Organization[] org, int numOrganizationsRegistered, Scanner in)
    {
        String path = ".\\org_data";
        for(int i=0;i<numOrganizationsRegistered;i++)
        {
            org[i].serialize(path+"_raw", org[i].getName() + ".ser");
            org[i].save(path, org[i].getName() + ".txt");
        }
        System.out.println("\nAll the details of organization have been stored in a folder 'org_data'\n");
    }

    public static void saveEmployee(Employee[] emp, int numEmployeesRegistered, Scanner in)
    {
        String path = ".\\emp_data";
        for(int i=0;i<numEmployeesRegistered;i++)
        {
            emp[i].serialize(path+"_raw", emp[i].getId() + ".ser");
            emp[i].save(path, emp[i].getId() + ".txt");
        }
        System.out.println("\nAll the details of employees have been stored in a folder 'emp_data'\n");
    }

    public static int retriveOrganizations(Organization[] org)
    {
        int numOrganizationsRegistered = 0;
        String path = ".\\org_data_raw";
        File f = new File(path);
        String filenames[] = f.list();
        for(String file: filenames)
        {
            org[numOrganizationsRegistered++] = Organization.deserialize(path, file);
            System.out.println("Organization: " + file + " retrieved!");
        }
        return numOrganizationsRegistered;
    }

    public static int retriveEmployees(Employee[] emp)
    {
        int numEmployeesRegistered = 0;
        String path = ".\\emp_data_raw";
        File f = new File(path);
        String filenames[] = f.list();
        for(String file: filenames)
        {
            emp[numEmployeesRegistered++] = Employee.deserialize(path, file);
            System.out.println("Employee: " + file + " retrieved!");
        }
        return numEmployeesRegistered;
    }
}