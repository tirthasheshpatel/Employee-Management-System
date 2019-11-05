package com.EMS.iomanager;

import java.util.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import com.EMS.core.Employee;
import com.EMS.core.Organization;
import com.EMS._abstract.IOManager;

public class IOManagerEmployee implements IOManager
{
    String path;
    String filename;
    Employee emp;
    File f;
    FileWriter fw;
    BufferedWriter bw;

    public IOManagerEmployee()
    {
        this.path = "C:\\";
        this.filename = "<id>.txt";
        this.emp = new Employee();
        this.f = null;
        this.fw = null;
        this.bw = null;
    }

    public IOManagerEmployee(String path,
                             String filename,
                             Employee emp)
    {
        this.setPath(path);
        this.setFileName(filename);
        this.setEmployee(emp);
        this.fw = null;
        this.bw = null;
    }

    public void setEmployee(Employee emp)
    {
        this.emp = emp;
    }

    public Employee getEmployee()
    {
        return this.emp;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return this.path;
    }

    public void setFileName(String filename)
    {
        try
        {
            this.filename = filename;
            this.f = new File(path + "\\" + this.filename);
            if(!this.f.exists()) this.f.createNewFile();
            validateFileName(this.f, this.filename);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void validateFileName(File f, String filename)
    {
        try
        {
            if(!f.canRead()) throw new IllegalAccessError("Can't read in file -> " + f.getAbsolutePath());
            if(!f.canWrite()) throw new IllegalAccessError("Can't write in file -> " + f.getAbsolutePath());
            if(!f.isFile()) throw new IllegalStateException("Given filename is not a file -> " + filename + " at " + f.getAbsolutePath());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getFileName()
    {
        return this.filename;
    }

    public void saveEmployeeDetails()
    {
        String content = "Last Modified: " + new Date(this.f.lastModified());
        StringBuffer contentBuff = new StringBuffer(content);
        contentBuff.append("\n\n --- Employee Details ---");
        contentBuff.append("\nName: " + emp.getName());
        contentBuff.append("\nID: " + emp.getId());
        contentBuff.append("\nAddress: " + emp.getAddress());
        contentBuff.append("\nOrganization: " + emp.getOrg());
        contentBuff.append("\nOccupation: " + emp.getOccupation());
        contentBuff.append("\nAccount: " + emp.getAccount());
        contentBuff.append("\nSalary: " + emp.getSalary());
        contentBuff.append("\nBonus: " + emp.getBonus());
        contentBuff.append("\nTotal: " + emp.getTotal());

        content = contentBuff.toString();
        this.writeToFile(content);
    }

    public void saveEmployeeDetails(String newFileName)
    {
        File f = new File(this.path + "\\" + newFileName);
        this.validateFileName(f, filename);
        String content = "Last Modified: " + new Date(f.lastModified());
        StringBuffer contentBuff = new StringBuffer(content);
        contentBuff.append("\n\n --- Employee Details ---");
        contentBuff.append("\nName: " + emp.getName());
        contentBuff.append("\nID: " + emp.getId());
        contentBuff.append("\nAddress: " + emp.getAddress());
        contentBuff.append("\nOrganization: " + emp.getOrg().getName());
        contentBuff.append("\nOccupation: " + emp.getOccupation());
        contentBuff.append("\nAccount: " + emp.getAccount());
        contentBuff.append("\nSalary: " + emp.getSalary());
        contentBuff.append("\nBonus: " + emp.getBonus());
        contentBuff.append("\nTotal: " + emp.getTotal());

        content = contentBuff.toString();
        this.writeToFile(content, f);
    }

    public void writeToFile(String content)
    {
        try
        {
            this.fw = new FileWriter(this.f);
            this.bw = new BufferedWriter(this.fw);
            this.bw.write(content);
            this.bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void writeToFile(String content, File f)
    {
        try
        {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String readFromFile()
    {
        String pass = "";
        return pass;
    }

    public void save()
    {
        this.saveEmployeeDetails();
    }
}