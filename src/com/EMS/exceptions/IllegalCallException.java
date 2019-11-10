package com.EMS.exceptions;

public class IllegalCallException extends Exception
{
    private static final long serialVersionUID = 905678324L;

    public IllegalCallException(String s)
    {
        super(s);
    }
}