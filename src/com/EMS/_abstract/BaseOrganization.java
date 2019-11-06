/**
 * Package containing the abstraction of all base classes in com.EMS.core package.
 */
package com.EMS._abstract;

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


import java.util.Date;

import com.EMS.core.Employee;


/**
 * <h1>BaseOrganization Interface</h1>
 * <p>
 * Interface for API class Organization.
 * </p>
 * <p>
 * <b>Note:</b> Only meant for API use. Must not be used by end user!
 * </p>
 *
 * @author  Tirth Patel
 * @version 1.0
 * @since   2019-11-5
 */
public interface BaseOrganization
{
    /**
     * Set the name of the organization.
     * @param name name of the employee.
     */
    public void setName(String name);

    /**
     * Get the name of the organization.
     * @return name of the organization.
     */
    public String getName();

    /**
     * Set the link to organization webpage.
     * Format: https://link.somthing.
     * @param link link to organization page.
     * @throws IllegalArgumentException error is thrown if the link validation fails
     */
    public void setLink(String link);

    /**
     * Get the link to organization webpage.
     * @return link to organization webpage.
     */
    public String getLink();

    /**
     * Set the address of the organization.
     * @param address address of the organization
     */
    public void setAddress(String address);

    /**
     * Get the address of the organization.
     * @return address of the organization.
     */
    public String getAddress();

    /**
     * Set the number of employees in the organization.
     * @param numEmployees number of employees in the organization.
     * @throws IllegalArgumentException if a negative number is passed as argument.
     * @throws IllegalArgumentException if the new size is less than number of appointed employees.
     */
    public void setNumEmployees(int numEmployees);

    /**
     * Get the number of employees in the organization.
     * @return number of employees in the organization.
     */
    public int getNumEmployees();

    /**
     * Set the date of establishment of the organization.
     * Format: All supported formats of {@link java.util.Date}.
     * @param date date of establishment.
     * @param format format of passed date.
     * @throws IllegalArgumentException if the format validation fails.
     * @see java.util.Date
     */
    public void setDateOfEstablishment(String date, String format);

    /**
     * Get the date of establishment of the organization.
     * @return date of establishment.
     * @see java.util.Date
     */
    public Date getDateOfEstablishment();

    /**
     * Set the details of the appointed employee.
     * Details of all the employees are stored in
     * an array.
     * @param emp Appointed employee.
     * @throws IllegalStateException If no vacant seats are present.
     * @throws IllegalStateException if the array is not initialized
     * by calling the method setNumEmployees
     * @throws IllegalArgumentException If employee with same id exists.
     */
    public void setEmployeeDetails(Employee emp);

    /**
     * Get the array of appointed employees.
     * <b>Note: The array may not be fully initialized.</b>
     * @return array of appointed employee.
     */
    public Employee[] getEmployeeDetails();

    /**
     * Pretty print the details of the employee.
     * @param print_employee_det boolean indicating weather or not to print employee details.
     */
    public void pPrint(boolean print_employee_det);
}