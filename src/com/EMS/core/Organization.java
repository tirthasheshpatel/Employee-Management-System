/**
 * MIT License
 * 
 * Copyright (c) 2019 Tirth Patel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

/**
 * Core package for base classes of Organization and Employee.
 */
package com.EMS.core;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.EMS._abstract.BaseOrganization;
import com.EMS.iomanager.IOManagerOrganization;
import com.EMS.exceptions.*;

/**
 * 
 */
public class Organization implements BaseOrganization, Serializable
{
    private static final long serialVersionUID = 10934356412L;
    private String name;
    private String address;
    private String link;
    private Date date;
    private int numEmployees;
    private Employee e[];
    private int numAppointed;
    private int numVacant;
    transient private IOManagerOrganization ioo;

    public Organization() {
        this.name = "<placeholder>";
        this.address = "<placeholder>";
        this.link = "https://www.placeholder.com/";
        this.numEmployees = 0;
        this.numAppointed = 0;
        this.numVacant = 0;
        this.e = null;
        this.ioo = null;
    }

    public Organization(String name, String link, String address, int numEmployees, String date, String format) throws ValidationFailedException{
        this.setName(name);
        this.setLink(link);
        this.setAddress(address);
        this.setNumEmployees(numEmployees);
        this.setDateOfEstablishment(date, format);
        this.ioo = null;
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

    public void setLink(String link) throws ValidationFailedException
    {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        if(!validateLink(link, regex))
        {
            throw new ValidationFailedException("Link validation failed! Check format and try again...");
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

    public void setNumEmployees(int numEmployees) throws ValidationFailedException
    {
        if(!this.validateNumEmployees(numEmployees))
            throw new ValidationFailedException("Validation of parameter `numEmployees` failed!");
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
                throw new ValidationFailedException("New employee size can't be less than appointed emploees!");
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

    public void setDateOfEstablishment(String date, String format) throws ValidationFailedException
    {
        try
        {
            this.date = new SimpleDateFormat(format).parse(date);
        }
        catch(Exception exp)
        {
            throw new ValidationFailedException("Date Validation falied! Check format and try again...");
        }
    }

    public Date getDateOfEstablishment()
    {
        return this.date;
    }

    public void setEmployeeDetails(Employee emp) throws NoVacancyException, IllegalCallException
    {
        if(0 == this.numVacant)
        {
            System.out.println("Oopsie Daisy!");
            throw new NoVacancyException("No vacant spaces left as of now! Please apply again later...");
        }
        else if(null == this.e)
        {
            String msg = "First call the method `setNumEmployees` of this class to initialize the employee array!";
            throw new IllegalCallException(msg);
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

    public Employee searchEmployeeById(String id) throws IllegalArgumentException
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

    public Employee searchEmployeeByName(String name) throws IllegalArgumentException
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

    public void removeEmployee(String id)
    {
        if(null == this.e)
        {
            System.out.println("\nNo employees present in the company.\n");
            return;
        }
        else if(this.numAppointed == 0)
        {
            System.out.println("\nNo employees appointed in the company.\n");
            return;
        }
        Employee e_new[] = new Employee[this.numEmployees];
        int j=0;
        boolean deleted = false;
        for(int i=0;i<numAppointed;i++)
        {
            if(e[i].getId().equals(id))
            {
                deleted = true;
                continue;
            }
            e_new[j++] = this.e[i];
        }
        if(!deleted)
        {
            System.out.println("Employee not found!");
            return;
        }
        this.e = e_new;
        this.numAppointed = j;
        this.numVacant = this.numEmployees - this.numAppointed;
        return;
    }

    public void pPrint(boolean print_employee_det)
    {
        System.out.println("\n---Organization Details---\n");
        System.out.println("Organization: " + this.name);
        System.out.println("Date of Establishment: " + this.date);
        System.out.println("Address: " + this.address);
        System.out.println("Read more about us on: " + this.link);
        System.out.println("Size of company: " + this.numEmployees + " employees");
        System.out.println("Number of Appointed employees: " + this.numAppointed);
        System.out.println("Number of Vacant seats: " + this.numVacant);
        System.out.println();
        if(print_employee_det && this.numAppointed != 0)
        {
            System.out.println("---Employee Details---\n");
            e[0].pPrint(false, false);
            if(this.numAppointed == 1) return;
            System.out.println(".");
            System.out.println(".");
            System.out.println(".");
            e[this.numAppointed-1].pPrint(false, false);
        }
    }

    public void save(String path, String filename)
    {
        if(null == this.ioo)
        {
            this.ioo = new IOManagerOrganization(path, filename, this);
        }
        this.ioo.save();
    }

    public void serialize(String path)
    {
        String filename = this.name + ".ser";
        try
        {
            FileOutputStream file = new FileOutputStream(path + "\\" + filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            out.writeObject(this);
            
            out.close();
            file.close();
            
            System.out.println("Object has been serialized");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void serialize(String path, String filename)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(path + "\\" + filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            out.writeObject(this);
            
            out.close();
            file.close();
            
            System.out.println("Object has been serialized");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Organization deserialize(String path, String filename)
    {
        try
        {
            FileInputStream file = new FileInputStream(path + "\\" + filename);
            ObjectInputStream in = new ObjectInputStream(file);
            
            Organization org = (Organization)in.readObject();
            
            in.close();
            file.close();
            return org;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}