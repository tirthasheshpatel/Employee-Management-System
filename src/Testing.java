import java.util.*;
import com.EMS.core.Organization;
import com.EMS.core.Employee;

class Testing
{
    public static void main(String[] args) {
        Organization org = new Organization("Google", "https://www.google.com/", "NYC, USA", 50000, "12/12/1994", "dd/MM/yyyy");
        Employee e1 = new Employee("Tirth Patel", "18BCE243", "West Virginia, USA", org, "Software Engineer", "1880-1994-1985-1977", 6000.0, 200.0, true);
        Employee e2 = new Employee("Tirth Hi", "18BCE244", "Las Vegas, USA", org, "ML Engineer", "1880-1994-1985-1978", 12000.0, 200.0, true);
        // e1.pPrint(true, true);
        org.setEmployeeDetails(e1);
        org.setEmployeeDetails(e2);
        org.pPrint(true);
        // org.setNumEmployees(-1);
        String path = "C:\\Users\\tirth\\Desktop\\Employee Management System\\saved_organization_data";
        String filename = "org_data.txt";
        org.save(path, filename);
        Employee e3 = new Employee("Tirth R", "18BCE245", "East Viriginia, USA", org, "Graphics Engineer", "1900-1997-1985-1877", 7000.0, 3400.0, true);
        org.setEmployeeDetails(e3);
        org.save(path, filename);
    }
}