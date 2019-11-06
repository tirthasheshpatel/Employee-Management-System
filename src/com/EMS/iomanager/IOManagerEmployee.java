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

package com.EMS.iomanager;

import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import com.EMS.core.Employee;
import com.EMS.core.Organization;
import com.EMS._abstract.IOManager;

public class IOManagerEmployee implements IOManager
{
    private String path;
    private String filename;
    private Employee emp;
    private File f;
    private FileWriter fw;
    private FileReader fr;
    private BufferedWriter bw;
    private BufferedReader br;

    public IOManagerEmployee()
    {
        this.path = "C:\\";
        this.filename = "employee.txt";
        this.emp = new Employee();
        this.f = null;
        this.fw = null;
        this.fr = null;
        this.bw = null;
        this.br = null;
    }

    public IOManagerEmployee(String path,
                             String filename,
                             Employee emp)
    {
        this.setPath(path);
        this.setFileName(filename);
        this.setEmployee(emp);
        this.fw = null;
        this.fr = null;
        this.bw = null;
        this.br = null;
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
        this.validatePath(path);
        this.path = path;
    }

    private void validatePath(String path)
    {
        try
        {
            File dir = new File(path);
            if(!dir.exists())
            {
                dir.mkdirs();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
        // this.fr = new FileReader(this.f);
        // this.br = new BufferedReader(this.fr);
        // String parsed_data = "";
        // String line;
        // while(null != (line=this.br.readLine()))
        // {
        //     String data[] = line.split(",");
        //     String id = data[0];
        //     String name = data[1];
        //     String 
        // }
        String pass = "";
        return pass;
    }

    public void save()
    {
        this.saveEmployeeDetails();
    }
}