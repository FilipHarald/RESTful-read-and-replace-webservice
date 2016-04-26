package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Storage {
	private static int counter; 

	public static int saveText(String fileText) {
		System.out.println("saveText");
		try {
			Files.write(Paths.get("TextFiles/" + (++counter) + ".txt"), fileText.getBytes());
			Files.write(Paths.get("conf"), ("" + counter).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counter;
	}

	public static void initCounter() {
		System.out.println("initCounter");
		counter = 0;
		try {
			List<String> lines = Files.readAllLines(Paths.get("conf"));
			counter = Integer.parseInt(lines.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getText(int fileNbr) {
		System.out.println("getText");
		String text = "";
		try {
			for(String s : Files.readAllLines(Paths.get("TextFiles/" + fileNbr + ".txt"))){
				text += s + "<br>";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
	public static String getTextIds(){
		LinkedList<String> fileNames = new LinkedList<String>();
		
		try {
			Files.walk(Paths.get("TextFiles/")).forEach(filePath-> {
			    if (Files.isRegularFile(filePath)) {
			        fileNames.add("" + filePath);
			    }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ids = "The text IDs are as follows: <br>";
		for(String fileName : fileNames){
			System.out.println(fileName);
			if(fileName.length() < 17){				
				ids += fileName.substring("TextFiles/".length(), fileName.length()-4) + "<br>";
			}
		}
		return ids;
	}
}
