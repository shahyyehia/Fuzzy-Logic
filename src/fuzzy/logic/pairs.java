/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy.logic;

import java.util.ArrayList;

/**
 *
 * @author Nada Hossam
 */
class pairs {

  
    String x;
    double r;
    ArrayList<Double> arr = new ArrayList<>();

    pairs() {

    }

    pairs(String x, ArrayList <Double> arr) {
     this.x=x;
        this.arr = arr;
    }
   pairs(String x, double r) {
     this.x=x;
        this.r = r;
    }

    

}
