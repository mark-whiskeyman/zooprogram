/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zooprogram;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Mark
 */
public class ReadFile {

    static String fileName;

    public ReadFile(String filename) {
        this.fileName = filename;
    }

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        String line = null;

        try {
            FileReader fr = new FileReader(fileName);
            try (BufferedReader br = new BufferedReader(fr)) {
                while ((line = br.readLine()) != null) {

                    QuotedRemover qr = new QuotedRemover();
                    line = qr.removeQuotes(line, "x");

                    Scanner inSS = new Scanner(line);

                    User user = new User();
                    String garbage = "";

                    user.setUserName(inSS.next());
                    user.setHashedPass(inSS.next());
                    garbage = inSS.next();
                    user.setUserType(inSS.next());
                    userList.add(user);
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return userList;
    }
}
