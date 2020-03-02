import java.util.Arrays;
public class AuditableBankingTests {
	public static void main(String[] args){
		testProcessCommand();
		testCalculateCurrentBalance();
		testCalculateNumberOfOverdrafts();
	}
    
	public static boolean testProcessCommand() {
    	boolean foundProblem = false;
		int [][] transactions= new int[100][];
    	int transactionsCount = 0;
    	transactionsCount= AuditableBanking.processCommand("0 0 1 1 0 1 1", transactions, transactionsCount);
    	if (transactions[0][2] != 1) {
    		System.out.println("Failure: The third element of the transaction array referred to by the first element of the oversize array, transactions, was not equal to 1");
    		foundProblem = false;
    	}
    	
    	else if(transactions[0][2] == 1) {
    	System.out.println("PASSED TEST 1/2 of testProcessCommand");
    	foundProblem = true;
        }
    	
    	if(transactionsCount != 1) {
    		System.out.println("Failure: The number of transactions represented by transactionsCount is not equal to 1 ");
    		foundProblem= false;
    	}
    	
    	else if (transactionsCount == 1) {
    		System.out.println("PASSED TEST 2/2 of testProcessCommand");
    		foundProblem = true;
    	}
    	return foundProblem;
    	}
	
	public static boolean testCalculateCurrentBalance() {
		boolean foundProblem = false;
		int[][] transactions= {{0,0,1,1}, {1,2,-1}, {2,3,0,1,1}};
    	int transactionsCount = 3;
		int balance = AuditableBanking.calculateCurrentBalance(transactions, transactionsCount);
		if (balance != -238) {
			System.out.println("Failure: calcuateCurrentBalance did not return 62 in the given conditions");
			foundProblem = false;
		}
		else if(balance == -238) {
			System.out.println("PASSED TEST 1/2 of testCalculateCurrentBalance");
			foundProblem = true;
		}
		return foundProblem;
	
	}
	
	public static boolean testCalculateNumberOfOverdrafts() {
		 boolean foundProblem = false;
		 int[][] transactions = new int[][] {
		    {1,10,-20,+30,-20,-20}, // +2 overdrafts (ending balance:  -20)
		    {0,1,1,1,0,0,1,1,1,1},  // +2 overdrafts (ending balance:  -15)
		    {1,115},                // +0 overdrafts (ending balance: +100)
		    {2,3,1,0,1},            // +1 overdrafts (ending balance: -100)
		  };
		  
		  // test with a single transaction of the Integer Amount encoding
		  int transactionCount = 1;    
		  int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		  if(overdrafts != 2) {
		    System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: "+Arrays.deepToString(transactions));
		    foundProblem = true;
		  } else
		    System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts");
		 
		  // test with four transactions: including one of each encoding
		  transactionCount = 4;
		  overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		  if(overdrafts != 5) {
		    System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: "+Arrays.deepToString(transactions));
		    foundProblem = true;
		  } else
		    System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts");
		  
		  return !foundProblem;
		}
	

}
