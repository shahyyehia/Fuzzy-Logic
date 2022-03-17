/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy.logic;

import java.util.Scanner;

/**
 *
 * @author Nada Hossam
 */
public class FuzzyLogic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double fund,exp;
      fund=scanner.nextDouble();
      exp=scanner.nextDouble();
        fuzzy f=new fuzzy(fund ,exp);
      //  f.fundFuzzification(50);
    }
    
}