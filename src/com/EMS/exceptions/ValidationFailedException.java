package com.EMS.exceptions;

public class ValidationFailedException extends Exception
{
    private static final long serialVersionUID = 798354132L;

    public ValidationFailedException(String s)
    {
        super(s);
    }
}