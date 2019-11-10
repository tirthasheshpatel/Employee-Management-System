package com.EMS.exceptions;

public class NoVacancyException extends Exception
{
    private static final long serialVersionUID = 123456786L;

    public NoVacancyException(String s)
    {
        super(s);
    }
}