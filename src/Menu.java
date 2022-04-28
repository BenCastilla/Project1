

import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
	private LinkedList<String> option_tags = new LinkedList<String>();
	private LinkedList<MenuAction> option_action = new LinkedList<MenuAction>();
	public static Scanner scanner = new Scanner(System.in);
	protected String title;
	public Menu(String string) {
		super();
		this.title = string;
	}

	public String listOptions() {
		String menu = title+"\n";
		for(String s : option_tags)
			menu += option_tags.indexOf(s)+1+"  : "+s+"\n";
		menu += option_tags.size()+1+" : Quit/Exit";
		return menu;
	}
	
	/**Will continuously ask for user input until it has a valid option
	 * @param s
	 * @return
	 */
	public int pickOption() {
		int choice = 0;
		while(choice == 0 || choice > option_tags.size()+1) {
			String input = getUserInput("Pick an Option :");
			if(isInteger(input))
				choice = Integer.parseInt(input);
				
		}
		return choice;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public boolean pickExecution() {
		System.out.println(listOptions());
		try {
			if(option_action.get(pickOption()-1).execute())
				return pickExecution();
		} catch (IndexOutOfBoundsException e) {
			//Do Nothing
		}
		return false;
	}

	public void addOption(String option, MenuAction action) {
		option_tags.add(option);
		option_action.add(action);
	}

	public void clear() {
		// TODO Auto-generated method stub
		option_tags.clear();
		option_action.clear();
	}
	
	public String getUserInput(String prompt) {
		System.out.print(prompt);
		String input = scanner.next();
		return input;
	}
}
