/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zooprogram;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mark Whiskeyman Last updated 4/3/2019
 */
public class ZooProgram {

    /**
     * @param args the command line arguments
     */
    /**
     * Displays a splash screen.
     */
    private static void displaySplash(String quitIt) {
        System.out.println("\n\n\n"
                + "********  Welcome to the zoo!  *******");
        System.out.format("----(Type `%s` to exit program)----\n", quitIt);
    }
    
    /**
     * Prints a closing message
     */
    private static void goodbye() {
        System.out.println("\n Thank you for using Zoosoft\n"
                + " The leader in Zoo related software\n");
    }

    /**
     * Gets a user Id from the user. Checks to make sure data is scrubbed and
     * that field is not blank.
     *
     * @return Returns a user id
     */
    private static String getUserId(String quitIt) {
        Scanner input = new Scanner(System.in);
        String userId = "\\";

        boolean isValidId = false;
        while (isValidId == false) {
            System.out.print("\n Please enter UserID: ");
            userId = input.nextLine();

            boolean isSpecialCharacters = ((userId.contains("\\"))
                    || (userId.contains("$"))
                    || (userId.contains("%"))
                    || (userId.contains(" ")));

            boolean isBlank = (userId.length() == 0);

            if (isSpecialCharacters) {
                System.out.println("\n!!!WARNING!!!\n "
                        + " - Illegal Characters \\, $, %, \" \" Detected");
            }

            if (isBlank) {
                System.out.println("\n!!!WARNING!!!\n "
                        + " - Blank User Name Detected");
            }

            if (isSpecialCharacters == false && isBlank == false) {
                isValidId = true;
            }

            if (quitIt.equals(userId.toLowerCase())) {
                userId = quitIt;
            }
        }

        return userId;
    }

    /**
     * Converts a string to an MD5 hash.
     *
     * @param userPass Takes a string - usually a password
     * @return Returns an MD5 hash of in input
     */
    private static String convertToMD5(String userPass) {
        MD5Digest md5 = new MD5Digest();
        String hashedPass = new String();

        try {
            hashedPass = md5.convertToMD5(userPass);
        } catch (Exception ex) {
            Logger.getLogger(ZooProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashedPass;
    }

    /**
     * Gets the password from the user.
     *
     * @return
     */
    private static String getUserPass() {
        Scanner input = new Scanner(System.in);
        System.out.print(" Please enter User Password: ");
        String userPass = input.nextLine();

        return userPass;
    }
    
    /**?
     * Imports credentials from file
     * @return 
     */
    private static ArrayList<User> importCredentials() {
        ArrayList<User> userList = new ArrayList<>();
        ReadFile file = new ReadFile("credentials.txt");
        userList = file.getUserList();
        return userList;
    }

    /**
     * Makes a hashmap of an arraylist of user objects
     *
     * @param userValues takes an array of user objects
     * @return returns a hashmap
     */
    private static HashMap<String, User> makeHashMap(ArrayList userValues) {
        HashMap<String, User> dict = new HashMap<>();
        User usr = new User();

        for (int x = 0; x < userValues.size(); x++) {
            usr = (User) userValues.get(x);
            dict.put(usr.getUserName(), usr);
        }
        return dict;
    }

    /**
     * Verifies that a valid user name has been entered
     *
     * @param dict takes in a hashmap<String, user>
     * @param userInput takes in user entered value
     * @return returns true or false
     */
    private static boolean verifyUserName(HashMap<String, User> dict, String userInput) {
        boolean isUserNameValid = false;

        if (dict.containsKey(userInput)) {
            isUserNameValid = true;
        }
        return isUserNameValid;
    }

    /**
     * Verifies that password is correct
     *
     * @param dict Takes a hashmap of String and User
     * @param usrPass Takes a user's password
     * @param userName takes a username
     * @return
     */
    private static boolean verifyUserPass(HashMap<String, User> dict, String usrPass,
            String userName) {

        User usr = new User();
        User authUser = new User();
        authUser = dict.get(userName);
        usr.hashedPass = usrPass;

        boolean isValidPass = false;
        try {
            isValidPass = (authUser.hashedPass.equals(usr.hashedPass));
        } catch (NullPointerException ex) {
        }
        return isValidPass;
    }

    /**
     * Authenticates a user
     *
     * @param userName takes in a boolean userName
     * @param userPass takes in a boolean userPass
     * @return returns true or false, a good authentication returns true
     */
    private static boolean authenticateUser(boolean userName, boolean userPass) {
        return (userName && userPass);
    }
    
    /**
     * Warns user of invalid login attempt 
     * @param allowed Number of allowed attempts 
     * @param attempts number of logins attempted
     */
    private static void loginWarning(int allowed, int attempts) {
        int attemptsRemaining = allowed - attempts;

        System.out.println("\n!!!WARNING!!!\n - Invalid User Name or"
                + "\n - Invalid Password");

        if (attemptsRemaining > 0) {
            System.out.format("\nYou have %d attempts remaining!\n",
                    (attemptsRemaining));
        } else {
            System.out.println("\n I'm sorry, "
                    + "\n You have entered invalid credentials too"
                    + "\n many times. "
                    + "\n---------------------------------------"
                    + "\n If you are an unauthorized user "
                    + "\n Please turn yourself in to zoo authorities now.");
            goodbye();
        }
    }
    
    /**
     * Authenticates a user for the zoo
     * @param args 
     */
    public static void main(String[] args) {
        ArrayList<User> userList = importCredentials();
        HashMap<String, User> dict = makeHashMap(userList);
        User user = new User();
        String quitId = "quit";

        displaySplash(quitId);

        boolean isGoodUser = false;
        boolean isGoodPass = false;
        boolean isGoodAuth = false;
        boolean isQuit = false;
        int failedAttempts = 0;
        int allowedAttempts = 3;
        while (isGoodAuth == false
                && (failedAttempts < allowedAttempts)
                && (isQuit == false)) {
            user.userName = getUserId(quitId);
            isQuit = (user.userName.equals(quitId));

            if (isQuit == true) {
                failedAttempts = allowedAttempts;
                goodbye();
            } else {
                String userPass = getUserPass();
                userPass = convertToMD5(userPass);
                isGoodUser = verifyUserName(dict, user.userName);
                isGoodPass = verifyUserPass(dict, userPass, user.userName);

                if (authenticateUser(isGoodUser, isGoodPass) == true) {
                    isGoodAuth = true;
                } else {
                    failedAttempts++;
                    loginWarning(allowedAttempts, failedAttempts);
                }
            }

        }
        //else get job description
        //print ption
    }
}
