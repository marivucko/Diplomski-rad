package com.gymdroid.services;

public class StringService extends BaseService {

    public boolean isStringEmpty(String someString) {
        return someString == null || "".equals(someString);
    }

    public String repsFormat(int reps) {
        if (reps < 10)
            return "   " + reps;
        else if (reps < 100)
            return  "  " + reps;
        else
            return  " " + reps;
    }

    public String weightFormat(double weight) {
        if (weight % 1 == 0) {
            return repsFormat((int) weight);
        }
        else {
            weight = (int) (weight * 10);
            weight = weight / 10;
            return "" + weight;
        }
    }

}
