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

package com.EMS.core;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.EMS._abstract.BaseEmployee;
import com.EMS.iomanager.IOManagerEmployee;

public class Employee implements BaseEmployee, Serializable
{
    private static final long serialVersionUID = 12324567854L;
    private String name;
    private String id;
    private String address;
    private Organization org;
    private String occupation;
    private String account;
    private double salary;
    private double bonus;
    private boolean status;
    transient private IOManagerEmployee ioe;

    public Employee() {
        this.name = "<employee-placeholder>";
        this.id = "<employee-placeholder>";
        this.address = "<employee-placeholder>";
        this.org = new Organization();
        this.occupation = "<placeholder> manager";
        this.account = "xxxx-xxxx-xxxx-xxxx";
        this.salary = 0.;
        this.bonus = 0.;
        this.status = false;
    }

    public Employee(String name,
                    String id,
                    String address,
                    Organization org,
                    String occupation,
                    String account,
                    double salary,
                    double bonus,
                    boolean status)
    {
        this.setName(name);
        this.setId(id);
        this.setAddress(address);
        this.setOrg(org);
        this.setOccupation(occupation);
        this.setAccount(account);
        this.setSalary(salary);
        this.setBonus(bonus);
        this.setStatus(status);
    }

    public String getOccupation()
    {
        return this.occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Organization getOrg()
    {
        return this.org;
    }

    public void setOrg(Organization org)
    {
        this.org = org;
        org.setEmployeeDetails(this);
    }

    public String getId()
    {
		return this.id;
    }

    public void setId(String id)
    {
		this.id = id;
    }

    public String getAccount()
    {
		return this.account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getSalary()
    {
        return this.salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public double getBonus()
    {
        return this.bonus;
    }

    public void setBonus(double bonus)
    {
        this.bonus = bonus;
    }

    public double getTotal()
    {
        return this.salary + this.bonus;
    }

    public boolean getStatus()
    {
        return this.status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void pPrint(boolean print_critical_info, boolean print_org_det)
    {
        System.out.println();
        System.out.println("id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Address: " + this.address);
        System.out.println("Organization: " + this.org.getName());
        System.out.println("Occupation: " + this.occupation);
        System.out.println("Available: " + this.status);
        if(print_critical_info)
        {
            System.out.println("\n /!\\ CRITICAL INFORMATION /!\\ \n");
            System.out.println("Accout: " + this.account);
            System.out.println("Salary: " + this.salary);
            System.out.println("Bonus: " + this.bonus);
            System.out.println("Total: " + this.getTotal());
        }
        if(print_org_det)
        {
            this.org.pPrint(false);
        }
        System.out.println();
    }

    public void save(String path, String filename)
    {
        if(null == this.ioe)
        {
            this.ioe = new IOManagerEmployee(path, filename, this);
        }
        this.ioe.save();
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

    public static Employee deserialize(String path, String filename)
    {
        try
        {
            FileInputStream file = new FileInputStream(path + filename);
            ObjectInputStream in = new ObjectInputStream(file);
            
            Employee e = (Employee)in.readObject();
            
            in.close();
            file.close();
            return e;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}