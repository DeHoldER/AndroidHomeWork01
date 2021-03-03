package ru.geekbrains.homework01;

import android.os.Parcel;
import android.os.Parcelable;

public class CalculatorState implements Parcelable {
    protected CalculatorState(Parcel in) {
        firstNumber = in.readDouble();
        secondNumber = in.readDouble();
        isFirstNumEmpty = in.readByte() != 0;
        isCurrentFractionalNumber = in.readByte() != 0;
        isFractionalNumber = in.readByte() != 0;
        action = (char) in.readInt();
    }

    public static final Creator<CalculatorState> CREATOR = new Creator<CalculatorState>() {
        @Override
        public CalculatorState createFromParcel(Parcel in) {
            return new CalculatorState(in);
        }

        @Override
        public CalculatorState[] newArray(int size) {
            return new CalculatorState[size];
        }
    };

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

    public void addCurrentNumber() throws Exception {
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
        // Если не стоит флаг, что число дробное, или если на конце .0, то округляем строку форматом
        if (!isFractionalNumber || out.endsWith(".0")) {
            out = String.format("%.0f", firstNumber);
        } else {
            out = String.valueOf(outDouble);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(firstNumber);
        dest.writeDouble(secondNumber);
        dest.writeByte((byte) (isFirstNumEmpty ? 1 : 0));
        dest.writeByte((byte) (isCurrentFractionalNumber ? 1 : 0));
        dest.writeByte((byte) (isFractionalNumber ? 1 : 0));
        dest.writeInt((int) action);
    }
}
