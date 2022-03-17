/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy.logic;

import static java.lang.Double.max;
import static java.lang.Double.min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nada Hossam
 */
public class fuzzy {

    ArrayList< pairs> fund = new ArrayList<>();
    ArrayList< pairs> experience = new ArrayList<>();
    ArrayList< pairs> risk = new ArrayList<>();

    fuzzy(double f, double e) {

        fund.add(new pairs("Very Low", new ArrayList<>(Arrays.asList(0.0, 0.0, 10.0, 30.0))));
        fund.add(new pairs("Low", new ArrayList<>(Arrays.asList(10.0, 30.0, 40.0, 60.0))));
        fund.add(new pairs("Medium", new ArrayList<>(Arrays.asList(40.0, 60.0, 70.0, 90.0))));
        fund.add(new pairs("High", new ArrayList<>(Arrays.asList(70.0, 90.0, 100.0, 100.0))));
        experience.add(new pairs("Beginner", new ArrayList<>(Arrays.asList(0.0, 15.0, 30.0))));
        experience.add(new pairs("Intermediate", new ArrayList<>(Arrays.asList(15.0, 30.0, 45.0))));
        experience.add(new pairs("Expert", new ArrayList<>(Arrays.asList(30.0, 60.0, 60.0))));
        risk.add(new pairs("Low", new ArrayList<>(Arrays.asList(0.0, 25.0, 50.0))));
        risk.add(new pairs("Normal", new ArrayList<>(Arrays.asList(25.0, 50.0, 75.0))));
        risk.add(new pairs("High", new ArrayList<>(Arrays.asList(50.0, 100.0, 100.0))));

        ArrayList<Double> values = new ArrayList<>(Arrays.asList(0.0, 1.0, 1.0, 0.0));
        ArrayList<Double> values2 = new ArrayList<>(Arrays.asList(0.0, 1.0, 0.0));
        HashMap<String, Double> r1 = new HashMap<>();
        HashMap<String, Double> r2 = new HashMap<>();
        HashMap<String, Double> r3 = new HashMap<>();
        r1 = Fuzzification(f, fund, values);
        r2 = Fuzzification(e, experience, values2);
        r3 = Fuzzification(DeFuzzification(r1, r2), risk, values2);
        String value = "";
        if (r3.get("Low") >= r3.get("Normal") && r3.get("Low") >= r3.get("High")) {

            value = "Low";
        } else if (r3.get("Normal") >= r3.get("Low") && r3.get("Normal") >= r3.get("High")) {
            value = "Normal";
        } else if (r3.get("High") >= r3.get("Low") && r3.get("High") >= r3.get("Normal")) {
            value = "High";
        }
        System.out.println("Predicted Value (Risk) = " + DeFuzzification(r1, r2));
        System.out.println("Risk will be = " + value);
    }

    double DeFuzzification(HashMap<String, Double> r1, HashMap<String, Double> r2) {
        double cLow = 0, cNormal = 0, cHigh = 0, Low, Normal, High, w;
        for (int i = 0; i < risk.size(); i++) {
            cLow += risk.get(0).arr.get(i);
            cNormal += risk.get(1).arr.get(i);
            cHigh += risk.get(2).arr.get(i);
        }

        cLow = cLow / 3;
        cNormal = cNormal / 3;
        cHigh = cHigh / 3;
        Low = inference(r1, r2).get("Low");
        Normal = inference(r1, r2).get("Normal");
        High = inference(r1, r2).get("High");
        w = ((Low * cLow) + (Normal * cNormal) + (High * cHigh)) / (Low + Normal + High);
        return w;
    }

    HashMap<String, Double> inference(HashMap<String, Double> rFund, HashMap<String, Double> rExp) {
        double rLow = 0, rNormal = 0, rHigh = 0;
        HashMap<String, Double> Risk = new HashMap<>();
        Risk.put("Low", max(rFund.get("High"), rExp.get("Expert")));
        Risk.put("Normal", max(min(rFund.get("Medium"), rExp.get("Intermediate")), rExp.get("Beginner")));
        Risk.put("High", max(min(rFund.get("Low"), rExp.get("Beginner")), rFund.get("Very Low")));

        return Risk;

    }

    HashMap<String, Double> Fuzzification(double e, ArrayList<pairs> array, ArrayList<Double> values) {
        HashMap<String, Double> r = new HashMap<>();
        Boolean w = false;
        double y1 = 0.0, y2 = 0.0, x1 = 0.0, x2 = 0.0, a1 = 0.0, b1 = 0.0, y1Total = 0.0;
        for (int i = 0; i < array.size(); i++) {
            //System.out.println("ss "+array.get(i).arr.get(array.get(i).arr.size() - 1));
            if (e <= array.get(i).arr.get(array.get(i).arr.size() - 1)
                    && e >= array.get(i).arr.get(0)) {
                
                for (int j = 0; j < array.get(i).arr.size(); j++) {
                    if (e <= array.get(i).arr.get(j)) {
                        x2 = array.get(i).arr.get(j);
                        int p = j - 1;
                        if (j == 0) {
                            p = 1;
                        }
                        x1 = array.get(i).arr.get(p);
                        y2 = values.get(j);
                        y1 = values.get(p);

                        a1 = (y2 - y1) / (x2 - x1);
                        b1 = y1 - (a1 * x1);

                        y1Total = (e * a1) + b1;
                        //System.out.println(array.get(i).x+" "+y1Total);
                        r.put(array.get(i).x, y1Total);
                        //r.add(new pairs(array.get(i).x,y1Total));
                        break;

                    }
                }

            } else if ((e > array.get(i).arr.get(array.get(i).arr.size() - 1) || e < array.get(i).arr.get(0))) {
                r.put(array.get(i).x, 0.0);
            }

        }
        return r;
    }
}