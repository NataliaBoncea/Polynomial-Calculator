package ro.tuc.BusinessLogic;

import ro.tuc.DataModels.Polynomial;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    public void adunare(Map<Integer, Double> p1, Map<Integer, Double> p2, Map<Integer, Double> res){
        for (Map.Entry<Integer, Double> m: p1.entrySet()){
            if(p2.containsKey(m.getKey())){
                Double t2 = p2.get(m.getKey());
                res.put(m.getKey(), t2+m.getValue());
            }
            else{
                res.put(m.getKey(), m.getValue());
            }
        }
        for (Map.Entry<Integer, Double> m: p2.entrySet()){
            if(!p1.containsKey(m.getKey())){
                res.put(m.getKey(), m.getValue());
            }
        }
    }

    public void scadere(Map<Integer, Double> p1, Map<Integer, Double> p2, Map<Integer, Double> res){
        for (Map.Entry<Integer, Double> m: p1.entrySet()){
            if(p2.containsKey(m.getKey())){
                Double t2 = p2.get(m.getKey());
                res.put(m.getKey(), m.getValue()-t2);
            }
            else{
                res.put(m.getKey(), m.getValue());
            }
        }
        for (Map.Entry<Integer, Double> m: p2.entrySet()){
            if(!p1.containsKey(m.getKey())){
                res.put(m.getKey(), -m.getValue());
            }
        }
    }

    public void inmultire(Map<Integer, Double> p1, Map<Integer, Double> p2, Map<Integer, Double> res){
        for (Map.Entry<Integer, Double> m1: p1.entrySet()) {
            for (Map.Entry<Integer, Double> m2 : p2.entrySet()) {
                if (res.containsKey(m1.getKey() + m2.getKey())) {
                    double res_part = res.get(m1.getKey() + m2.getKey());
                    res.replace(m1.getKey() + m2.getKey(), res_part + m1.getValue() * m2.getValue());
                } else {
                    res.put(m1.getKey() + m2.getKey(), m1.getValue() * m2.getValue());
                }
            }
        }
    }

    public TreeMap<Integer, Double> impartire(Map<Integer, Double> p1, Map<Integer, Double> p2, Map<Integer, Double> res){
        TreeMap<Integer, Double> h = new TreeMap<>(p1);
        TreeMap<Integer, Double> l = new TreeMap<>(p2);
        TreeMap<Integer, Double> mod_partial = new TreeMap<>();
        TreeMap<Integer, Double> h_partial;
        TreeMap<Integer, Double> l_partial = new TreeMap<>();

        if(h.lastKey()<l.lastKey()){
            TreeMap<Integer, Double> aux = h;
            h = l;
            l = aux;
        }
        h_partial = new TreeMap<>(h);
        for (int i = h.lastKey(); i>=0; i--) {
            if(h.containsKey(i)){
                if(i <l.lastKey()){
                    break;
                }
                double part_res = h.get(i)/l.lastEntry().getValue();
                res.put(i-l.lastKey(), part_res);
                for (Map.Entry<Integer, Double> ml: l.entrySet()){
                    if(!l_partial.containsKey(ml.getKey()+i-l.lastKey())){
                        l_partial.put(ml.getKey()+i-l.lastKey(), ml.getValue()*part_res);
                    }
                }
                scadere(h_partial, l_partial, mod_partial);
                h_partial.clear();
                h_partial.putAll(mod_partial);
                l_partial.clear();
                mod_partial.clear();
            }
        }
        return h_partial;
    }

    public void modulo(Map<Integer, Double> p1, Map<Integer, Double> p2, Map<Integer, Double> mod){
        HashMap<Integer, Double> res = new HashMap<Integer, Double>();

        mod.putAll(impartire(p1, p2, res));
    }

    public void derivare(Map<Integer, Double> p, Map<Integer, Double> res){
        for (Map.Entry<Integer, Double> m: p.entrySet()) {
            if(m.getKey()>0){
                res.put(m.getKey() - 1, m.getValue() * m.getKey());
            }
        }
    }

    public void integrare(Map<Integer, Double> p, Map<Integer, Double> res){
        for (Map.Entry<Integer, Double> m: p.entrySet()) {
            res.put(m.getKey() + 1, m.getValue() / (m.getKey()+1));
        }
    }

    public boolean verifInput(String s){
        if (s.isEmpty()){
            return true;
        }
        String patternString = "([^\\d^+\\-x*])|(x[^\\^+-])|(x\\^[^\\d])|(x\\^\\d+[^\\d+-])" +
                "|([^x]\\^)|([+-][^\\dx])|([^\\d]\\*)|(\\*[^x])";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}
