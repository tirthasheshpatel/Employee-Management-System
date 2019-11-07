/**
 * Package containing the abstraction of all base classes in com.EMS.core package.
 */
package com.EMS._abstract;

/**
 * <h1>MIT License</h1>
 * 
 * <p><b>Copyright (c) 2019 Tirth Patel</b></p>
 * <br>
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:</p>
 * 
 * <p>The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.</p>
 * 
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.</p>
 * 
 */


import com.EMS.core.Organization;


/**
 * <h1>BaseEmployee Interface</h1>
 * <p>
 * Interface for API class Employee.
 * </p>
 * <p>
 * <b>Note:</b> Only meant for API use. Must not be used by end user!
 * </p>
 *
 * @author  Tirth Patel
 * @version 1.0
 * @since   2019-11-5
 */
public interface BaseEmployee
{
    /**
     * Get the current occupation of the employee.
     * @return String string containing occupation of the employee.
     */
    public String getOccupation();
    
    /**
     * Set the occupation of the employee.
     * @param occupation String Current occupation of the employee.
     */
    public void setOccupation(String occupation);

    /**
     * Get the name of the employee.
     * @return String name of the employee.
     */
    public String getName();
    
    /**
     * Set the name of the employee.
     * @param name String name of the employee.
     */
    public void setName(String name);

    /**
     * Get the current organization of the employee.
     * @return Organization object of organization employee wokrs in.
     * @see com.EMS.core.Organization
     */
    public Organization getOrg();

    /**
     * Set the current organization of the employee.
     * @param org Organization object of organization employee wokrs in.
     * @see com.EMS.core.Organization
     */
    public void setOrg(Organization org);

    /**
     * Get the ID of the employee.
     * @return String ID of the employee.
     */
    public String getId();

    /**
     * Set the ID of the employee.
     * @param id String ID of the employee.
     */
    public void setId(String id);

    /**
     * Get the account of the employee.
     * @return String Account of the employee.
     */
    public String getAccount();

    /**
     * Set the account of the employee.
     * @param account String account of the employee.
     */
    public void setAccount(String account);

    /**
     * Get the address of the employee.
     * @return String address of the employee.
     */
    public String getAddress();

    /**
     * Set the address of the employee.
     * @param address String address of the employee.
     */
    public void setAddress(String address);

    /**
     * Get the salary of the employee.
     * @return double Salary of the employee.
     */
    public double getSalary();

    /**
     * Set the salary of the employee.
     * @param salary double Salary of the employee.
     */
    public void setSalary(double salary);

    /**
     * Get the bonus of the employee.
     * @return double Bonus of the employee.
     */
    public double getBonus();

    /**
     * Set the bonus of the employee.
     * @param bonus double Bonus of the employee.
     */
    public void setBonus(double bonus);

    /**
     * Get the total salary of the employee. i.e. salary + bonus.
     * @return double get the total salary of the employee. i.e. salary + bonus.
     */
    public double getTotal();

    /**
     * Get the status of the employee. i.e. If the employee is available or not.
     * @return boolean indicating weather the emploee is available or not
     */
    public boolean getStatus();

    /**
     * Set the status of the employee. i.e. If the employee is available or not.
     * @param status boolean indicating weather the emploee is available or not.
     */
    public void setStatus(boolean status);

    /**
     * Pretty Print the attributes of the employee.
     * @param print_critical_info boolean indicating weather or not print critical info of the employee.
     * @param print_org_det boolean indicating weather or not print organization details of the employee.
     */
    public void pPrint(boolean print_critical_info, boolean print_org_det);
}