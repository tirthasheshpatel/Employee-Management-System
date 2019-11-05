import java.util.*;
import com.EMS.core.Organization;
import com.EMS.core.Employee;
import com.EMS.iomanager.IOManagerEmployee;
import com.EMS.iomanager.IOManagerOrganization;

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
        IOManagerEmployee ioe = new IOManagerEmployee("C:\\Users\\tirth\\Desktop\\Employee Management System\\saved_employee_data", "tirth.txt", e1);
        ioe.save();
        IOManagerOrganization ioo = new IOManagerOrganization("C:\\Users\\tirth\\Desktop\\Employee Management System\\saved_organization_data", "org_data.txt", org);
        ioo.save();
    }
}