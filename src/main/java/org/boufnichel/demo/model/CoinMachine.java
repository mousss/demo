package org.boufnichel.demo.model;

import javax.script.ScriptException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class CoinMachine implements Serializable {
    public static final String REGEX = "x";
    private Integer box = 0;

    private Map<Integer, Integer> ticket = new HashMap<>();

    public void putMoneyInside(String money)   {
        if (money != null && !money.isEmpty()) {
            String[] parts = money.split(REGEX);
            ticket.put(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        }
    }


    public int check(int i) {
        if (isNotNullOrEmptyMap(ticket)) {
            for (Map.Entry<Integer, Integer> v : ticket.entrySet()) {
                if (v.getValue() == i) {
                    return v.getKey();
                }
            }
        }
        return 0;
    }


    public Integer getBox() {

        if (isNotNullOrEmptyMap(ticket)) {
            for (Map.Entry<Integer, Integer> v : ticket.entrySet()) {
                this.box = this.box + (v.getKey() * v.getValue());
            }
        }
        return this.box;
    }

    public void setBox(Integer box) {
        this.box = box;
    }

    public void getMoney(int i) {
        if (isNotNullOrEmptyMap(ticket)) {
            Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
            Map<Integer, Integer> ticketUp = new HashMap<>();
            int maxValueInMap = (Collections.max(ticket.keySet()));
            int minValueInMap = (Collections.min(ticket.keySet()));
            linkedHashMap.put(maxValueInMap, ticket.get(maxValueInMap));
            linkedHashMap.put(minValueInMap, ticket.get(minValueInMap));
            Integer result = i;// initialisation value
            for (Map.Entry<Integer, Integer> v : linkedHashMap.entrySet()) {
                if (i >= v.getValue()) {
                    result = result - v.getValue();
                    Integer rest = v.getKey();
                    rest--;
                    if (result > 0 && result >= v.getValue()) {//check if result >0  and sup to max value map
                        result = result - v.getValue();
                        rest--;
                    }
                    ticketUp.put(rest, v.getValue());
                    if (result == 0) {
                        break;
                    }
                }
            }
            if (isNotNullOrEmptyMap(ticketUp)) {
                ticket.clear();
                ticket.putAll(ticketUp);
            }
        }

    }

    public double totalMoney() {
        return getBox();
    }

    public static boolean isNotNullOrEmptyMap(Map<?, ?> map) {
        return !isNullOrEmptyMap(map); // this method defined below
    }

    public static boolean isNullOrEmptyMap(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }
}
