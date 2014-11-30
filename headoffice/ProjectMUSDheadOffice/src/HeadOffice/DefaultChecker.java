/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeadOffice;

/**
 *
 * @author tharaka
 */
public class DefaultChecker {
    
    // check whether the given string is a string
public boolean isInt(String input){
    try{
        int i = Integer.parseInt(input);
    }catch(Exception e){
        return false;
    }
    return true;
}

public boolean isDouble(String input){
    try{
        double i = Double.parseDouble(input);
    }catch(Exception e){
        return false;
    }
    return true;
}

}