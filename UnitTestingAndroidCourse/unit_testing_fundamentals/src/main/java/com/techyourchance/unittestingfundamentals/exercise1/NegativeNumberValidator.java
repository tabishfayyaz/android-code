package com.techyourchance.unittestingfundamentals.exercise1;

public class NegativeNumberValidator {

    public boolean isNegative(int number) {

        if (number == 0)
            return false;
        return number < 0;
    }
}
