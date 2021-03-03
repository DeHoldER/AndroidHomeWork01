package ru.geekbrains.homework01;

public class CalculatorState {
    public void totalReset() {
        firstNumber = 0;
        secondNumber = 0;
        isFirstNumEmpty = true;
        isCurrentFractionalNumber = false;
        isFractionalNumber = false;

        action = '0';

        stringBuilder.setLength(0);
    }

    public CalculatorState() {
        totalReset();
    }

    private double firstNumber;
    private double secondNumber;
    private boolean isFirstNumEmpty;
    private boolean isCurrentFractionalNumber;
    private boolean isFractionalNumber;

    private char action;

    StringBuilder stringBuilder = new StringBuilder();

    public void calculate() {
        if (!isFirstNumEmpty) {
            Double outDouble = 0.0;

            {
                if (action == '+') {
                    outDouble = firstNumber + secondNumber;
                }
                if (action == '-') {
                    outDouble = firstNumber - secondNumber;
                }
                if (action == '*') {
                    outDouble = firstNumber * secondNumber;
                }
                if (action == '/') {
                    isFractionalNumber = true;
                    outDouble = firstNumber / secondNumber;
                }
            }

            firstNumber = outDouble;
        }
    }

    public void addCurrentNumber() {
        if (isFirstNumEmpty) {
            firstNumber = Double.parseDouble(stringBuilder.toString());
            if (action == '-') {
                firstNumber = -(firstNumber);
            }
            isFirstNumEmpty = false;
        } else {
            secondNumber = Double.parseDouble(stringBuilder.toString());
            calculate();
            secondNumber = 0;
        }

        stringBuilder.setLength(0);
        isCurrentFractionalNumber = false;
    }

    public String getStringResult() {
        String out = String.valueOf(firstNumber);
        Double outDouble = firstNumber;
        int outInt;
        // Если не стоит флаг, что число дробное, или если на конце .0, то делаем из числа int и делаем из этого стрингу
        if (!isFractionalNumber || out.endsWith(".0")) {
//            outInt = outDouble.intValue();
            out = String.format("%.0f", firstNumber);
        } else {
            out = String.valueOf(outDouble);
//            if (out.endsWith(".0")) {
//                out = out.substring(0, out.length() - 2);
//            }
        }
        return out;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public boolean isCurrentFractionalNumber() {
        return isCurrentFractionalNumber;
    }

    public void setCurrentFractionalNumber(boolean currentFractionalNumber) {
        isCurrentFractionalNumber = currentFractionalNumber;
    }

    public void setFractionalNumber(boolean fractionalNumber) {
        isFractionalNumber = fractionalNumber;
    }

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }

}
