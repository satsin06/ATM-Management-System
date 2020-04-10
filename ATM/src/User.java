import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {

    private String firstname;

    @SuppressWarnings("unused")
	private String lastname;

    private String uuid;

    private byte pinHash[];

    private ArrayList<Account> accounts;
    public User(String firstname, String lastname, String pin, Bank theBank){

        this.firstname = firstname;
        this.lastname = lastname;

        try {
            MessageDigest nd = MessageDigest.getInstance("MD5");
            this.pinHash = nd.digest(pin.getBytes());
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        this.uuid = theBank.getNewUserUUID();

  
        this.accounts = new ArrayList<Account>();

      
        System.out.printf("New user %s, %s with ID %s created.\n", lastname, firstname, this.uuid);

    }

    
    public void addAccount(Account onAcct) {
        this.accounts.add(onAcct);
    }

   
    public String getUUID(){
        return this.uuid;
    }

    
    public boolean validatePin(String oPin){

        try {
            MessageDigest nd = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(nd.digest(oPin.getBytes()), this.pinHash);
        }catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }


    public String getFirstname(){
        return this.firstname;
    }

    /**
     * Print summaries for the accounts of this user.
     */
    public void printAccountsSummary(){

        System.out.printf("\n\n%s's accounts summary\n", this.firstname);
        for(int a = 0; a < this.accounts.size(); a++){
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();

    }

  
    public int numAccounts(){
        return this.accounts.size();
    }

   
    public void printAccTransHistory(int acctIdx){

        this.accounts.get(acctIdx).printTransHistory();

    }


  
    public double getAcctBalance(int acctIdx){
        return this.accounts.get(acctIdx).getBalance();
    }


   
    public String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }


    public  void addAcctTransaction(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }


}
