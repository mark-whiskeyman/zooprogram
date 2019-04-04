/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zooprogram;

/**
 * March 22nd, 2019
 * @author Mark Whiskeyman 
 * Removes quoted material from a string
 * Can currently only remove one block of quotes 
 */
public class QuotedRemover {

    /**
     * @param string takes a string as input
     * @param placeholder takes something to replace the quoted material
     * @return returns a string w/o quotes
     */
    public String removeQuotes(String string, String placeholder) {
        int index1 = -1;
        int index2 = -1;
        String remove = new String();

        while (string.contains("\"")) {
          
            // Find first quotation
            index1 = string.indexOf('\"');
            
            // Find Second quotation 
            index2 = string.lastIndexOf('\"');
            
            // Create a string to be removed
            remove = string.substring(index1, (index2 + 1));
            
            // Replace the string to be removed with a place holder 
            // This is done by the program calling the method 
            // to ensure maximum compatibility
            string = string.replaceAll(remove, placeholder);

        }
        return string;
    }
}
