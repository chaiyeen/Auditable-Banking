import java.util.Scanner;
import java.util.StringTokenizer;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           AudtableBanking
//Files:           P04AuditableBanking
//Course:          CS300, Fall 2018
//
//Author:          Chaiyeen Oh
//Email:           coh26@wisc.edu
//Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    (name of your pair programming partner)
//Partner Email:   (email address of your programming partner)
//Partner Lecturer's Name: (name of your partner's lecturer)
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         (identify each person and describe their help in detail)
//Online Sources:  Piazza (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/* AuditableBanking project helps user to quickly determine their balance, number of overdrafts by simply clicking the app button.
 * Overall the bank user were allowed to withdrawl/ deposit their account three different ways.
 * Binary Amount Transactions : 1 dollar deposit or withdrawal
 * Integer Amount Trasactions : deposit or withdraws specific amount the user wants
 * Quick Withdraw Transactions : non negative integers that shows amount of certain amount of integers.
 * 
* @author Chaiyeen Oh
*/

public class AuditableBanking {
	
	public static void main(String[] args) {   
    int[][] allTransactions = new int [100][];
	int allTransactionsCount = 0;	
  	Scanner sc = new Scanner(System.in);
	boolean key = true;
		
		while(key) {
		System.out.println("========== Welcome to the Auditable Banking App ==========");
		System.out.println(" COMMAND MENU: ");
		System.out.println("   Submit a Transaction (enter sequence of integers separated by spaces)");
		System.out.println("   Show Current [B]alance");
		System.out.println("   Show Number of [O]verdrafts");
		System.out.println("   [Q]uit Program");
		System.out.println("ENTER COMMAND: ");
        
		String input = sc.nextLine();
		if(input.charAt(0) == 'Q' || input.charAt(0) == 'q') {
			System.out.println("============ Thank you for using this App!!!! ============");
			break;
		    }
		if(input.charAt(0) == 'B' || input.charAt(0) == 'b') { 
		    System.out.println(calculateCurrentBalance(allTransactions, allTransactionsCount)); 
		    break;
		   }
		if(input.charAt(0) == 'O' || input.charAt(0) == 'o') { 
			System.out.println(calculateNumberOfOverdrafts(allTransactions, allTransactionsCount)); 
			break;
			}	
	   }
	}
	
      /**
	    * Adds a transaction group to an array of transaction groups. 
	    * 
	    * If the allTransactions array is already full 
	    * then this method will do nothing other than return allTransactionCount.
	    * 
	    * @param newTransactions is the new transaction group being added (perfect size).
	    * @param allTransactions is the collection that newTransactions is being added to (oversize).
	    * @param allTransactionsCount is the number of transaction groups within allTransactions 
	    *        (before newTransactions is added.
	    * @return the number of transaction groups within allTransactions after newTransactions is added.
	    */
	
	public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
		    int allTransactionsCount) {
		   if(allTransactionsCount == allTransactions.length) { //when full
			   return allTransactionsCount;
		   }
		
		   else { //not full
				allTransactions[allTransactionsCount] = newTransactions; //adds newTransactions to the collection
				allTransactionsCount++;			
				return allTransactionsCount;
			}
		}
	
      /** adds a new transaction group to allTransactions 
       * and categorize them into a specific transaction group array of either 0,1,2
	   * 
	   * when first character within a command does not correspond to a valid transaction encoding OR
	   * the allTransactions array starts out full,
	   * this method should not make any changes to allTransactions.
	   * 
	   * @return changed allTransactionsCount after adding new transaction
	   */
	
	public static int processCommand(String command, int[][] allTransactions, int allTransactionsCount) {
		command = command.trim();
		command.toUpperCase();
		char ch = command.charAt(0);
		
		
		String splittedValues[] = command.split(" "); //split input/command by empty space			
		int numberOfTransactions = splittedValues.length;
		int[] arrayOfIntegers = new int[numberOfTransactions];
		int balance = 0;
		
		// if first index is either 0, 1 or 2
		if (Integer.parseInt(splittedValues[0])>=0 && Integer.parseInt(splittedValues[0])<=2) {
		   for(int i=0; i<splittedValues.length; i++) {
			arrayOfIntegers[i] = Integer.parseInt(splittedValues[i]); 
			allTransactionsCount = submitTransactions(arrayOfIntegers, allTransactions, allTransactionsCount);
			// make all the element string numbers into integer numbers
		      }
		   }
			return allTransactionsCount;
			}
	
		
	 /** Go through allTransactionsCount in the array and add all the elements
	  * first determine the type of transaction
	  * for binary amount transactions, add up all the $1 deposits
	  * for integer Amount transaction, add all the deposits and withdraws
	  * for quick withdraw transaction, add each categories ($20, $40, $80, $100)
	  * 
	  * @return changed balance after calculating
	  *  
	  */	
      //when user typed B or b -> processCommand(string) -> 
	  // System.out.println(calculateCurrentBalance(allTransactions, allTransactionsCount));

	public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
	       int balance = 0;
	       for(int i = 0; i<allTransactionsCount; i++) { // set number
	            	  if(allTransactions[i][0] == 0) { // BINARY
	            		  for(int j = 1; j<allTransactions[i].length; j++) { // elements in array starting at index 1
	            			  if(allTransactions[i][j] == 1) { // only add to the balance if the value is 1
	            				  balance = balance + 1;
	            				  }
	            			  else if(allTransactions[i][j] == 0){
	            				  balance = balance -1; // if array value is 0, add zero
	            			  }
	            	          }
	            	      }
	       }
	       
	       for(int i=0; i<allTransactionsCount; i++) {
	    	          if(allTransactions[i][0]==1 && allTransactions[i]!=null) {  // INTEGER
	            		  for(int j = 1; j<allTransactions[i].length; j++) { // elements in array starting at index 1
	    	        	  balance += allTransactions[i][j]; // add value in the array
	    	                 }
	    	              }
	       }
	       
	       for(int i=0; i<allTransactionsCount; i++) {
	    	          if(allTransactions[i][0]==2) {  // QUICKWITHDRAW ($20, $40, $80, $100)
	            			  balance = balance - ((allTransactions[i][1] * 20) + (allTransactions[i][2] * 40) + 
	            			            (allTransactions[i][3] * 80) + (allTransactions[i][4] * 100));
	            	      }
	                  }
	       return balance;  
	}
	 
	
	
	 /** An overdraft happens when a withdraw from the account is made that results in a negative balance. 
	  * And to detect the number of overdrafts, process collections of transaction groups 
	  * in order of increasing indexes starting at zero.
	  * within each group of transactions, process increasing indexes.
	  * Once the balance goes below zero, increase the count of overdrafts
	  * 
	  * @return changed balance after adding
	  * 
	  */
	 public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
	       int numberOfOverdrafts = 0;
	       int balance = 0;
	       for(int i = 0; i<allTransactionsCount; i++) {
	           for(int j = 0; j<allTransactions[i].length; j++) {      
	               balance += allTransactions[i][j];

	               if(allTransactions[i][j]<0 && balance<0)   
	                   numberOfOverdrafts++;
	           }
	       }
	       return numberOfOverdrafts;
	   }
	 
}

