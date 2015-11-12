package com.example.zuki.calculator.managers;

/**
 * Created by Zuki on 10/9/2015.
 */
import com.example.zuki.calculator.utils.Parser;
public class CalculatorManager {
    private boolean syntaxError;
    private String result;
    public CalculatorManager()
    {
        syntaxError=false;
        result="0";
    }
    public String clear()
    {
        result="0";
        return result;
    }
    public String putOperation(String operation)
    {
        if(syntaxError)
        {
            syntaxError=false;
            result="0";
        }
        else
            result+=operation;
        return result;
    }
    public String putNumber(String number)
    {
        if(syntaxError)
        {
            syntaxError=false;
            result=number;
        }
        else {
            if(result.equals("0"))result="";
            result += number;
        }
        return result;
    }
    public String removeOne()
    {
        if(result.length()>1)
            result=result.substring(0,result.length()-1);
        else
            result="0";
        return result;
    }
    private String checkDecimals(double value)
    {
        if(value%1==0)
        {
            int res=(int) value;
            return res+"";
        }
        else
            return value+"";
    }
    public String getResult()
    {
        try {
            Parser parser = new Parser(result);
            result=checkDecimals(parser.parse());
            return result;
        }
        catch (Exception ex)
        {
            syntaxError=true;
            return "Syntax Error!";
        }

    }

}
