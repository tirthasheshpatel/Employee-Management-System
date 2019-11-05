package com.EMS.core;

import java.util.*;
import java.util.regex.Pattern;

import javax.naming.NameNotFoundException;

import java.util.regex.Matcher;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.EMS._abstract.BaseOrganization;

public class Organization implements BaseOrganization
{
    private String name;
    private String address;
    private String link;
    private Date date;
    private int numEmployees;
    private Employee e[];
    private int numAppointed;
    private int numVacant;
    private boolean isAllSet;

    public Organization()
    {
        this.name = "<placeholder>";
        this.address = "<placeholder>";
        this.link = "https://www.placeholder.com/";
        this.numEmployees = 0;
        this.numAppointed = 0;
        this.numVacant = 0;
        this.isAllSet = false;
        e = null;
    }

    public Organization(String name,
                        String link,
                        String address,
                        int numEmployees,
                        String date,
                        String format)
    {
        this.setName(name);
        this.setLink(link);
        this.setAddress(address);
        this.setNumEmployees(numEmployees);
        this.setDateOfEstablishment(date, format);
        this.isAllSet = true;
    }

    public int getNumAppointed()
    {
        return this.numAppointed;
    }

    public int getNumVacant()
    {
        return this.numVacant;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setLink(String link)
    {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        if(!validateLink(link, regex))
        {
            throw new IllegalArgumentException("Link validation failed! Check format and try again...");
        }
        this.link = link;
    }

    public String getLink()
    {
        return this.link;
    }

    private boolean validateLink(String s, String pattern)
    {
        try
        {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        }
        catch (RuntimeException e)
        {
            return false;
        }
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setNumEmployees(int numEmployees)
    {
        if(!this.validateNumEmployees(numEmployees))
            throw new IllegalArgumentException("Validation of parameter `numEmployees` failed!");
        this.numEmployees = numEmployees;
        if(null == this.e)
        {
            this.e = new Employee[numEmployees];
            this.numAppointed = 0;
            this.numVacant = this.numEmployees;
        }
        else
        {
            Employee newE[] = new Employee[this.numEmployees];
            if(this.numEmployees <= this.numAppointed)
            {
                this.numEmployees = this.e.length;
                throw new IllegalArgumentException("New employee size can't be less than appointed emploees!");
            }
            else
            {
                for(int i=0;i<this.numAppointed;i++) newE[i] = e[i];
                this.e = newE;
                this.numVacant = this.numEmployees - this.numAppointed;
                return;
            }
        }
    }

    public int getNumEmployees()
    {
        return this.numEmployees;
    }

    private boolean validateNumEmployees(int numEmployees)
    {
        if(numEmployees <= 0)
        {
            System.out.println("A company must have more than 0 employees!");
            return false;
        }
        return true;
    }

    public void setDateOfEstablishment(String date, String format)
    {
        try
        {
            this.date = new SimpleDateFormat(format).parse(date);
        }
        catch(Exception exp)
        {
            throw new IllegalArgumentException("Date Validation falied! Check format and try again...");
        }
    }

    public Date getDateOfEstablishment()
    {
        return this.date;
    }

    public void setEmployeeDetails(Employee emp)
    {
        if(0 == this.numVacant)
        {
            System.out.println("Oopsie Daisy!");
            throw new IllegalStateException("No vacant spaces left as of now! Please apply again later...");
        }
        else if(null == this.e)
        {
            String msg = "First call the method `setNumEmployees` of this class to initialize the employee array!";
            throw new IllegalStateException(msg);
        }
        else
        {
            for(int i=0;i<this.numAppointed;i++)
            {
                if(this.e[i].getId() == emp.getId())
                {
                    String msg = "Employee with same ID exists!";
                    throw new IllegalArgumentException(msg);
                }
            }
        }
        this.numAppointed++;
        this.numVacant = this.numEmployees - this.numAppointed;
        this.e[numAppointed-1] = emp;
    }

    public Employee searchEmployeeById(String id)
    {
        for(int i=0;i<this.numAppointed;i++)
        {
            if(id == e[i].getId())
            {
                return e[i];
            }
        }
        throw new IllegalArgumentException("Employee with id " + id + " not found in the database of organization " + this.name);
    }

    public Employee searchEmployeeByName(String name)
    {
        name = name.toLowerCase();
        for(int i=0;i<this.numAppointed;i++)
        {
            if(name == e[i].getName().toLowerCase())
            {
                return e[i];
            }
        }
        throw new IllegalArgumentException("Employee with name " + name + " not found in the database of organization " + this.name);
    }

    public Employee[] getEmployeeDetails()
    {
        return this.e;
    }

    public void pPrint(boolean print_employee_det)
    {
        System.out.println("Organization: " + this.name);
        System.out.println("Date of Establishment: " + this.date);
        System.out.println("Address: " + this.address);
        System.out.println("Read more about us on: " + this.link);
        System.out.println("Size of company: " + this.numEmployees + " employees");
        System.out.println("Number of Appointed employees: " + this.numAppointed);
        System.out.println("Number of Vacant seats: " + this.numVacant);
        if(print_employee_det && this.numAppointed != 0)
        {
            e[0].pPrint(false, false);
            if(this.numAppointed == 1) return;
            System.out.println(".");
            System.out.println(".");
            System.out.println(".");
            e[this.numAppointed-1].pPrint(false, false);
        }
        System.out.println();
    }
}