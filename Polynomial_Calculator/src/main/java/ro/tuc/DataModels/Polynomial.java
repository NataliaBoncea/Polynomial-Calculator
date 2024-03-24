package ro.tuc.DataModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;

public class Polynomial {
    HashMap<Integer, Double> monomials;

    public Polynomial(){
        monomials = new HashMap<Integer, Double>();
    }

    public HashMap<Integer, Double> getMonomials() {
        return monomials;
    }

    public void setMonomials(HashMap<Integer, Double> monomials) {
        this.monomials = monomials;
    }

    public void addPolynomial(String p){
        ArrayList<String> terms = new ArrayList<String>();
        String[] numbers = new String[2];
        terms = monomialDiv(p);
        for(String t:terms) {
            if (t.contains("x^")) {
                if(t.charAt(0)=='x' || (t.charAt(1)=='x' && (t.charAt(0)=='+' || t.charAt(0)=='-'))){
                    numbers = t.split("\\^");
                    monomials.put(Integer.parseInt(numbers[1]), 1.0);
                    if(t.charAt(0)=='-') {
                        monomials.replace(Integer.parseInt(numbers[1]), -1.0);
                    }
                }
                else{
                    numbers = t.split("x\\^");
                    monomials.put(Integer.parseInt(numbers[1]), Double.parseDouble(numbers[0]));
                }
            } else if (t.contains("x")) {
                if(t.charAt(0)=='x' || (t.charAt(1)=='x' && (t.charAt(0)=='+' || t.charAt(0)=='-'))){
                    monomials.put(1, 1.0);
                    if(t.charAt(0)=='-'){
                        monomials.replace(1, -1.0);
                    }
                }
                else{
                    numbers = t.split("x");
                    monomials.put(1, Double.parseDouble(numbers[0]));
                }
            }
            else { monomials.put(0, Double.parseDouble(t)); }
        }
    }

    private ArrayList<String> monomialDiv(String p){
        ArrayList<String> terms = new ArrayList<String>();

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(p);
        int x=0;
        while (matcher.find()) {
            x=x+1;
            terms.add(matcher.group(1));
        }

        return terms;
    }

    public String toString(){
        String result = " ";
        String monomial;
        boolean zero = true;
        for(Map.Entry<Integer, Double> term: monomials.entrySet()){
            if(term.getValue()!=0){
                zero = false;
                monomial = findSign(term.getValue());
                if(abs(term.getValue())!=1.0 || term.getKey()==0){
                    monomial = monomial + abs(term.getValue());
                }
                if(term.getKey()==1){
                    monomial = monomial + "x";
                }
                else if(term.getKey()>1){
                    monomial = monomial + "x^" + term.getKey();
                }
                result = monomial + result;
            }
        }
        if(zero){
            result = "0";
            return result;
        };
        if(result.charAt(1)=='+'){
            result = result.substring(2);
        }
        return result;
    }

    private String findSign(Double coef){
        if(coef>0){
            return " + " ;
        }
        else if(coef<0){
            return " - " ;
        }
        return "";
    }
}
