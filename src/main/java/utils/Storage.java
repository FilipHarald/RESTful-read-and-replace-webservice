package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
	private static int counter; 

	public static void saveText(String fileText) {
		System.out.println("saveText");
		try {
			Files.write(Paths.get("TextFiles/" + (++counter) + ".txt"), fileText.getBytes());
			Files.write(Paths.get("TextFiles/conf"), ("" + counter).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initCounter() {
		System.out.println("initCounter");
		counter = 0;
		try {
			List<String> lines = Files.readAllLines(Paths.get("TextFiles/conf"));
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
				text += s + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}