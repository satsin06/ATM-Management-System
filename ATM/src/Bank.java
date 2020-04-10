import com.sun.org.apache.xml.internal.utils.StringToIntTable;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("unused")
public class Bank {

    private String name;


    private ArrayList<User> users;


    private ArrayList<Account> accounts;

    Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUUID(){

        
        String uuid;
        Random rng = new Random();
        int len= 6;
        boolean nonUnique;

        do {


     
            uuid = "";
            for(int c = 0; c < len; c++){
            uuid += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for(User u : this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }


        }while (nonUnique);

        return uuid;

    }

 
    public String getNewAccountUUID(){
        String uuid;
        Random rng = new Random();
        int len= 10;
        boolean nonUnique;

        do {

            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }


        }while (nonUnique);

        return uuid;


    }

    public void addAccount(Account onAcct){
        this.accounts.add(onAcct);
    }

    public User addUser(String firstname, String lastname, String pin ){

        User newUser = new User(firstname, lastname, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;

    }

    public User userLogin(String userID, String pin){

        
        for(User u : this.users){

            
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }

       
        return null;

    }



    public String getName(){
        return this.name;
    }


}
