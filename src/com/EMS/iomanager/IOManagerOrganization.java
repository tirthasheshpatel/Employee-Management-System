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
import java.io.BufferedWriter;
import java.io.FileWriter;

import com.EMS.core.Employee;
import com.EMS.core.Organization;
import com.EMS._abstract.IOManager;

public class IOManagerOrganization implements IOManager
{
    private String path;
    private String filename;
    private Organization org;
    private File f;
    private FileWriter fw;
    private BufferedWriter bw;

    public IOManagerOrganization()
    {
        this.path = "C:\\";
        this.filename = "placeholder.txt";
        this.org = new Organization();
        this.f = null;
        this.fw = null;
        this.bw = null;
    }

    public IOManagerOrganization(String path, String filename, Organization org)
    {
        this.setPath(path);
        this.setFileName(filename);
        this.setOrg(org);
        this.fw = null;
        this.bw = null;
    }

    public void setOrg(Organization org)
    {
        this.org = org;
    }

    public Organization getOrg()
    {
        return this.org;
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
                boolean success = dir.mkdirs();
                System.out.println(success);
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
            this.validateFileName(this.f, this.filename);
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

    public void saveOrganizationDetails()
    {
        String content = "Last Modified: " + new Date(this.f.lastModified());
        StringBuffer contentBuff = new StringBuffer(content);
        contentBuff.append("\n\n --- Organization Details ---");
        contentBuff.append("\nOrganization: " + org.getName());
        contentBuff.append("\nDate of Establishment: " + org.getDateOfEstablishment());
        contentBuff.append("\nAddress: " + org.getAddress());
        contentBuff.append("\nRead more about us on: " + org.getLink());
        contentBuff.append("\nSize of company: " + org.getNumEmployees() + " employees");
        contentBuff.append("\nNumber of Appointed employees: " + org.getNumAppointed());
        contentBuff.append("\nNumber of Vacant seats: " + org.getNumVacant());
        contentBuff.append("\n\n --- Employee Details ---");
        Employee e[] = org.getEmployeeDetails();
        if(null == e) throw new IllegalArgumentException("No employees to save!");
        for(int i=0;i<org.getNumAppointed();i++)
        {
            contentBuff.append("\nname: " + e[i].getName() + ", ");
            contentBuff.append("id: " + e[i].getId() + ", more details in the file: " + e[i].getId() + ".txt");
            IOManagerEmployee ioe = new IOManagerEmployee(this.path, e[i].getId()+".txt", e[i]);
            ioe.save();
        }
        content = contentBuff.toString();
        this.writeToFile(content);
    }

    public void writeToFile(String content)
    {
        try
        {
            this.fw = new FileWriter(this.f);
            this.bw = new BufferedWriter(this.fw);
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
        this.saveOrganizationDetails();
    }
}