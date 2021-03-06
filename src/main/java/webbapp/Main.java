package webbapp;

import static spark.Spark.*;

import utils.Storage;
import utils.TextProcessor;

public class Main {
    public static void main(String[] args) {
    	Storage.initCounter();
    	
        get("/texts/:nbr", (request, response) -> {
        	String param = request.params(":nbr");
            if ( param.matches("[0-9]+")){
            	return Storage.getText(Integer.parseInt(param));
            };
            return "Not a valid file";
        });
        
        post("/texts", (request, response) -> {
        	String[] textLines = TextProcessor.getTextFileLines(request.body());
        	System.out.println("In main: textLines is: " + textLines.length);
        	String processedText = TextProcessor.fooBar(textLines);
//        	System.out.println(processedText);
        	int id = Storage.saveText(processedText);
        	return "File processed and saved" + 
        			"<br> It can be found at http://localhost:4567/texts/" + id +
        			"<br> Here is the processed text:" + 
        			processedText;
        });
        
        get("/texts", (request, response) -> {
        	return Storage.getTextIds();
        });
        
    }
}