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

package com.EMS._abstract;

/**
 * <h1>IOManager Interface</h1>
 * <p> 
 * Abstraction fro Input/Output managers for API classes.
 * </p>
 * <p>
 * <b>Note: Only for API usage. Not to be used by the end user</b>
 * </p>
 * 
 * @author Tirth Patel
 * @version 1.0
 * @since   2019-11-5
 */
public interface IOManager
{
    /**
     * Set the destination path to save all the produced files.
     * <b>Note: Directories in the path must exists before usage.</b>
     * @param path destination path
     */
    public void setPath(String path);

    /**
     * Get the destination path of the IOManager.
     * @return destination path.
     */
    public String getPath();

    /**
     * Set the name of the file to be saved.
     * @param filename file name
     */
    public void setFileName(String filename);

    /**
     * Get the name of the file to be saved.
     * @return file name
     */
    public String getFileName();

    /**
     * Write all the parameters of respective class to the file.
     * @param content content to write
     */
    public void writeToFile(String content);

    /**
     * Read from file
     * <b>CURRENTLY NOT IMPLEMENTED. Present here for API consistency.</b>
     * @return none
     */
    public String readFromFile();

    /**
     * Save the file.
     */
    public void save();
}