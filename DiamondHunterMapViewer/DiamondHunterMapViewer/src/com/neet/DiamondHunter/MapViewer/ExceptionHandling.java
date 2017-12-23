package com.neet.DiamondHunter.MapViewer;

@SuppressWarnings("serial")
public class ExceptionHandling extends Exception{
	
	//make sure that every exception is handled so not cause the program to crash
	
	String exceptionmessage;
	
	ExceptionHandling(String excpmessage)
	{
		this.exceptionmessage = excpmessage;
	}
	
	public String getExcp()
	{
		return exceptionmessage;
	}

}