//Basic Input getter By MARK WHISKEYMAN

import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Exception;

public class GetUserInput {

    static Scanner keyboard = new Scanner(System.in);

    public GetUserInput() {

    }

    /**
     * GETYESNO METHOD gets a y/n response from user
     */
    public boolean getYesNo() {
        char chr = getNextChar();
        boolean trueFalse = false;
        switch (chr) {
            case 'Y':
            case 'y':
            case '1':
                trueFalse = true;
                break;
            case 'N':
            case 'n':
            case '0':
                trueFalse = false;
                break;
            default:
                errorBadInput();
                break;
        }
        return trueFalse;
    }

    public char getNextChar() {
        //System.out.println("Inside getNextChar...");
        String input = keyboard.nextLine();
        boolean end = false;
        char chr = 'a';
        while (end == false) {
            try {
                chr = input.charAt(0);
                end = true;
            } catch (StringIndexOutOfBoundsException e) {
                errorBadInput();
                input = keyboard.nextLine();
            }
        }
        //input = keyboard.nextLine();
        return chr;
    }

    private void errorBadInput() {
        System.out.println(" Unrecognized Command, Please Try Again");
    }

    public String getString() {
        return keyboard.nextLine();
    }

    public void pause() {
        System.out.print(">>>");
        getString();
    }

    public int getInt() {
        boolean end = false;
        int userIn = 0;
        String garbage = "";
        while (end == false) {
            try {
                userIn = keyboard.nextInt();
                garbage = keyboard.nextLine();
                end = true;
            } catch (InputMismatchException e) {
                System.out.println("Unrecognized Number, Please enter an integer");
                keyboard.nextLine();
            }
        }
        return userIn;
    }

    public long getLong() {
        boolean end = false;
        long userIn = 0;
        String garbage = "";
        while (end == false) {
            try {
                userIn = keyboard.nextLong();
                garbage = keyboard.nextLine();
                end = true;
            } catch (InputMismatchException e) {
                System.out.println("Unrecognized Number, Please enter an integer");
                keyboard.nextLine();
            }
        }
        return userIn;
    }

    public double getDouble() {
        boolean end = false;
        double userIn = 0;
        String garbage = "";
        while (end == false) {
            try {
                userIn = keyboard.nextDouble();
                garbage = keyboard.nextLine();
                end = true;
            } catch (InputMismatchException e) {
                System.out.println("Unrecognized Number, Please enter a Number");
                keyboard.nextLine();
            }
        }
        return userIn;
    }
}
