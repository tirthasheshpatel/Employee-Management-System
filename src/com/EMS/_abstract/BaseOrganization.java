package com.EMS._abstract;

import java.util.Date;
import com.EMS.core.Employee;

public interface BaseOrganization
{
    public void setName(String name);
    public String getName();
    public void setLink(String link);
    public String getLink();
    public void setAddress(String address);
    public String getAddress();
    public void setNumEmployees(int numEmployees);
    public int getNumEmployees();
    public void setDateOfEstablishment(String date, String format);
    public Date getDateOfEstablishment();
    public void setEmployeeDetails(Employee emp);
    public Employee[] getEmployeeDetails();
    public void pPrint(boolean print_employee_det);
}