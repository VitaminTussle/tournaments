package tournaments;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class tournaments {
	
	//scanner:
	public static Scanner scan = new Scanner(System.in); //create a scanner
	
	//input indicators:
	public static String type = "";
	public static String tourn = "";
	public static String name = "";
	public static String onward = "";
	
	//tournament arraylists:
	public static ArrayList<String> ssbpm1 = new ArrayList<String>();
	public static ArrayList<String> ssbpm2 = new ArrayList<String>();
	public static ArrayList<String> ssb41 = new ArrayList<String>();
	public static ArrayList<String> ssb42 = new ArrayList<String>();
	public static ArrayList<String> ssbu1 = new ArrayList<String>();
	public static ArrayList<String> ssbu2 = new ArrayList<String>();
	public static ArrayList<String> pokemon = new ArrayList<String>();
	public static ArrayList<String> smashketball = new ArrayList<String>();
	public static ArrayList<String> civ = new ArrayList<String>();
	public static ArrayList<String> arms = new ArrayList<String>();
	
	//extra arraylists (for efficiency mostly):
	public static ArrayList<String> byes = new ArrayList<String>();
	public static ArrayList<String> types = new ArrayList<String>();
	public static ArrayList<String> tourns = new ArrayList<String>();
	
	//array for putting together brackets:
	public static String[][] matches;
	
	//making maps work:
	public static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	
	//reusable boolean for moving on to the next step:
	public static boolean go = false;
	
	//let's begin.
	public static void main(String[] args) {
		//create individual maps to translate between user string input and arraylist:
		map.put("ssbpm1", ssbpm1);
		map.put("ssbpm2", ssbpm2);
		map.put("ssb41", ssb41);
		map.put("ssb42", ssb42);
		map.put("ssbu1", ssbu1);
		map.put("ssbu2", ssbu2);
		map.put("pokemon", pokemon);
		map.put("smashketball", smashketball);
		map.put("civ", civ);
		map.put("arms", arms);
		
		//add tournament strings to arraylist tourns for if statement efficiency and readability:
		tourns.add("ssbpm1");
		tourns.add("ssbpm2");
		tourns.add("ssb41");
		tourns.add("ssb42");
		tourns.add("ssbu1");
		tourns.add("ssbu2");
		tourns.add("pokemon");
		tourns.add("smashketball");
		tourns.add("civ");
		tourns.add("arms");
		
		//add action strings to arraylist types for if statement efficiency and readability:
		types.add("add");
		types.add("remove");
		types.add("clear");
		types.add("list");
		types.add("bracket");
		
		while(true) {//forever loop
			
		System.out.println("What would you like to do? (add, remove, clear, list, bracket)"); //find user desired action
		type = scan.next().toLowerCase();
		go = false;
		while(!go) { //go until they input an accepted action
			if(types.contains(type)) {
				go = true;
			}
			else if(type.equals("exit")) {
				System.out.println("Bye!");
				System.exit(0);
			}
			else {
				System.out.println("Didn't quite catch that. Try again.");
				type = scan.next().toLowerCase();
			}
		}
				
		System.out.println("Which Tournament? (SSBPM1, SSBPM2, SSBU1, SSBU2, Pokemon, Smashketball, Civ, Arms)"); //find user desired tournament
		tourn = scan.next().toLowerCase();
		go = false;
		while(!go) { //go until they input an accepted tournament
			if(tourns.contains(tourn)) {
				go = true;
			}
			else {
				System.out.println("Didn't quite catch that. Try again.");
				tourn = scan.next().toLowerCase();
			}
		}
		
		if(type.equals("add")) { //if adding, get a name, confirm, and pass to add function
			System.out.println("Who's being added?");
			name = scan.next();
			go = false;
			while(!go) {
				System.out.println("You're adding " + name + "? (y/n)");
				onward = scan.next();
				if(onward.toLowerCase().equals("y")) {
					name = name.toLowerCase();
					add(tourn, name);
					go = true;
				}
				else if(onward.toLowerCase().equals("n")) {
					System.out.println("Who's being added?");
					name = scan.next();
				}
				else {
					System.out.println("Sorry, didn't catch that. You're adding " + name + "? (y/n)");
					onward = scan.next();
				}
			}
		}
		else if(type.equals("remove")) { //if removing, get a name, confirm, and pass to remove function
			System.out.println("Who's being removed?");
			name = scan.next();
			go = false;
			while(!go) {
				System.out.println("You're removing " + name + "? (y/n)");
				onward = scan.next();
				if(onward.toLowerCase().equals("y")) {
					name = name.toLowerCase();
					remove(tourn, name);
					go = true;
				}
				else if(onward.toLowerCase().equals("n")) {
					System.out.println("Who's being removed?");
					name = scan.next();
				}
				else {
					System.out.println("Sorry, didn't catch that. You're removing " + name + "? (y/n)");
					onward = scan.next();
				}
			}
		}
		else if(type.equals("list")) { //simply list tournament members
			list(tourn);
		}
		else if(type.equals("bracket")) { //create a bracket
			bracket(tourn);
		}
		else if(type.equals("clear")) { //clear bracket members
			clear(tourn);
		}
		else if(type.equals("exit")) { //exit on command
			System.exit(0);
		}
		System.out.println("\n");
		
		}//end of forever loop
	}
	
	public static void add(String tourn, String name) { //check for duplicates in tourn, if none, add name to tourn
		if(!map.get(tourn).contains(name)) {
			map.get(tourn).add(name);
			System.out.println("Successfully added " + name + " to the " + tourn + " tournament.");
		}
		else {
			System.out.println("Error: Could not add " + name + ", they're already in the " + tourn + " tournament.");
		}
	}
	
	public static void remove(String tourn, String name) { //ensure name exists in tourn, remove name from tourn
		if(map.get(tourn).contains(name)) {
			map.get(tourn).remove(name);
			System.out.println("Successfully removed " + name + " from the " + tourn + " tournament.");
		}
		else {
			System.out.println("Error: " + name + " is not in the " + tourn + " tournament.");
		}
	}
	
	public static void list(String tourn) { //list each entrant in given tournament
		System.out.print(tourn + ": ");
		System.out.println(map.get(tourn).toString());
	}
	
	public static void bracket(String tourn) { //ensure tourn is not too large, then create bracket
		if(!(map.get(tourn).size() > 32)) {
			createBracket(tourn);
		} else {
			System.out.println("Error: the " + tourn + " tournament is too big. It has " + map.get(tourn).size() + " members, and the maximum is 32.");
		}
	}
	
	public static void createBracket(String tourn) { //create random bracket matches, including (inefficient) byes
		int xDim = 0; //number of straight matches in first round
		int counterX = 0;
		int counterY = 0;
		int rand = 0;
		boolean empty = false;
		
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < map.get(tourn).size(); i++) { //make temp copy array so we don't edit original
			temp.add(map.get(tourn).get(i));
		}
		
		
		if(map.get(tourn).size() == 32) { //create x dimensions of tournament for perfect rounds, extras get byes (bad)
			xDim = 16;
		}
		else if(map.get(tourn).size() > 32) {
			xDim = 0;
			System.out.println("Sorry, there are too many members in the " + tourn + "tournament.");
			System.out.println("Please shorten the list of entrants and try again.");
			empty = true;
		}
		else if(map.get(tourn).size() >= 16) {
			xDim = 8;
		}
		else if(map.get(tourn).size() >= 8) {
			xDim = 4;
		}
		else if(map.get(tourn).size() >= 4) {
			xDim = 2;
		}
		else if(map.get(tourn).size() >= 2) {
			xDim = 1;
		}
		else if(map.get(tourn).size() == 0) {
			xDim = 0;
			System.out.println("The " + tourn + " tournament is empty.");
			empty = true;
		}
		else {
			xDim = 0;
			System.out.println(map.get(tourn).get(0) + " wins by default. They were the only one in the " + tourn + " tournament.");
			temp.remove(temp.get(0));
			empty = true;
		}
		
		if(!empty) {
			matches = new String[xDim][2]; //array of matches where each row contains two people and is therefore a match
			while(temp.size() > 0 && counterY <= 1/* && matches.length > 0 ((small inefficiency))*/) {
				rand = (int)(Math.random() * temp.size()); //random index in temp
//				temp = randomArrList(temp); //randomize temp array (double random? unnecessary, function below commented out)
				
				matches[counterX][counterY] = temp.get(rand);
				counterX++;
				if(counterX >= matches.length) {
					counterX = 0;
					counterY++;
				} //place random temp index at spots in matches in order...
				temp.remove(temp.get(rand)); //then remove possibility of doubles and recursion
			}
			
			byes = new ArrayList<String>();
			for(int i = 0; i < temp.size(); i++) {
				byes.add(temp.get(i));
			} //simply adds all extras (outside power of 2) to byes to be added to preliminaries in subBracket
			
			
			
			//idea: subBracket function takes matches array and remaining temp array to form tournament matches
			//	from existing byes. pumps out array of pre-round-1 matches to seed round 1 from byes and place
			//	extra byes in actual round 1. how will that work?
			
			//like this:
			subBracket(matches, byes, tourn);
			
			
			
			//old, bad version:
//			System.out.println("The " + tourn + " matches are as follows:");
////			System.out.println(matches.toString()); //bugfixing
//			for(int i = 0; i < matches.length; i++) { //print tourn matches
//				System.out.println(matches[i][0] + " VS " + matches[i][1]);
//			}
//			if(byes.size() != 0) { //print byes if there are any
//				System.out.println("These people have byes in this order: ");
//				for(int i = 0; i < byes.size(); i++) {
//					System.out.println(byes.get(i));
//				}
//			}
//			else { //inform of lack of byes if applicable
//				System.out.println("There were no byes needed.");
//			}
		}
	}
	
	//used to form brackets of unusual shapes an sizes from 2 up to 32 members
	public static void subBracket(String[][] currentMatches, ArrayList<String>currentByes, String tourn) {
		String[][] preliminaries = new String[currentByes.size()][2];
		if(preliminaries.length != 0) {
			for(int i = 0; i < preliminaries.length; i++) {
				preliminaries[i][0] = currentByes.get(i);
				
				if(i < currentMatches.length) { //as long as there are fewer leftover people than matches
					preliminaries[i][1] = currentMatches[i][1];
					currentMatches[i][1] = "Winner of Pre-" + (i + 1);
				} else { //makes it able to feed 2 preliminary matches into one place in bracket (try 7 names)
					preliminaries[i][1] = currentMatches[i - currentMatches.length + 1][0];
					currentMatches[i - currentMatches.length + 1][0] = "Winner of Pre-" + (i + 1);
				}
			}
		
			System.out.println("The preliminary round will consist of " + preliminaries.length + " match(es).");
			System.out.println("Preliminaries:");
		
			for(int i = 0; i < preliminaries.length; i++) {
				System.out.println("Pre " + (i + 1) + ": " + preliminaries[i][0] + " VS " + preliminaries[i][1]);
			}
			
			System.out.println("");
			System.out.println("The remaining " + tourn + " match(es) are as follows:");
		}
		else {
			System.out.println("No preliminary round is necessary.");
			System.out.println("The " + tourn + " match(es) are as follows:");
		}
		
		for(int i = 0; i < currentMatches.length; i++) { //print tourn matches
			System.out.println(currentMatches[i][0] + " VS " + currentMatches[i][1]);
		}
	}
	
//	public static ArrayList<String> randomArrList(ArrayList<String> list) { //unnecessary, double random factor
//		int index1 = -1;
//		int index2 = -1;
//		String temp = "";
//		
//		for(int i = 0; i < list.size() * 4; i++) {
//			index1 = (int)(Math.random() * list.size());
//			index2 = (int)(Math.random() * list.size());
//			
//			temp = list.get(index1);
//			list.set(index1, list.get(index2));
//			list.set(index2, temp);
//		}
//		
//		return list;
//	}
	
	public static void clear(String tourn) { //ez clear
		map.get(tourn).clear();
		System.out.println(tourn + " tournament successfully cleared.");
	}

}
