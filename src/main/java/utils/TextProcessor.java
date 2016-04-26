package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {

	public static String[] getTextFileLines(String body) {
		System.out.println("getTextFileLines");
		String[] bodyLines = body.split("\\r?\\n"); 
		return Arrays.copyOfRange(bodyLines, 4, bodyLines.length-1);
	}

	public static String fooBar(String[] textLines) {
		System.out.println("fooBar");
		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
		for(String line : textLines){
			Matcher matcher = Pattern.compile("[a-zA-Z]+\\b").matcher(line);
			while(matcher.find()) {
				String word = matcher.group().toLowerCase();
				if(wordCountMap.containsKey(word)){
					wordCountMap.put(word, wordCountMap.get(word) + 1);
				}else{
					wordCountMap.put(word, 1);
				}
			}
		}
		ArrayList<String> mostUsedWords = getMostUsedWord(wordCountMap);
		return doFooBar(textLines, mostUsedWords);
	}

	private static String doFooBar(String[] textLines, ArrayList<String> mostUsedWords) {
		System.out.println("doFooBar");
		String text = "";
		int counter = 0;
		System.out.println("processed lines....");
		for(String line : textLines){
//			System.out.println(line);
			String temp = line;
			if(++counter%10000==0){
				System.out.println("......" + counter);
			}
			for(String mostUsedWord : mostUsedWords){
				temp = temp.replaceAll("(?i)\\b" + mostUsedWord + "\\b", "FOO$0BAR");
            }
			text += temp + "\n";
		}
		return text;
	}

	private static ArrayList<String> getMostUsedWord(HashMap<String, Integer> wordCountMap){
		System.out.println("getMostUsedWord");
		int highest = 0;
		String word = "";
		for(Entry<String, Integer> e : wordCountMap.entrySet()){
			if(e.getValue() > highest){
				highest = e.getValue();
			}
		}
		ArrayList<String> mostUsedWords = new ArrayList<String>();
		for(Entry<String, Integer> e : wordCountMap.entrySet()){
			if(e.getValue() == highest){
				mostUsedWords.add(e.getKey());
			}
		}
		System.out.println("Most used word(s) are: ");
		for (String w : mostUsedWords) {
			System.out.println("        " + w);
		}
		System.out.println("occurrences: " + mostUsedWords.size()*highest);
		return mostUsedWords;
	}
}
