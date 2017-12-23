package com.neet.DiamondHunter.MapViewerController;


public class ExceptionHandler extends Exception {
	
	  String excptxt;

	    ExceptionHandler(String excptxt){
	        this.excptxt = excptxt;
	    }

	    @Override
	    public String getMessage(){
	        return excptxt;
	    }
}
