package com.EMS._abstract;

import java.util.*;

import com.EMS.core.Organization;

public interface BaseEmployee
{
    public String getOccupation();
    public void setOccupation(String occupation);
    public String getName();
    public void setName(String name);
    public Organization getOrg();
    public void setOrg(Organization org);
    public String getId();
    public void setId(String id);
    public String getAccount();
    public void setAccount(String account);
    public String getAddress();
    public void setAddress(String address);
    public double getSalary();
    public void setSalary(double salary);
    public double getBonus();
    public void setBonus(double bonus);
    public double getTotal();
    public boolean getStatus();
    public void setStatus(boolean status);
    public void pPrint(boolean print_critical_info, boolean print_org_det);
}