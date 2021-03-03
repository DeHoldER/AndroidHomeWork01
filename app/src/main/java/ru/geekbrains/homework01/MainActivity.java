package ru.geekbrains.homework01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CalculatorState calculator = new CalculatorState();

    TextView outputScreen;
    TextView actionChar;

    Button mButton0;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    Button mButton5;
    Button mButton6;
    Button mButton7;
    Button mButton8;
    Button mButton9;
    Button mButtonPt;

    Button mButtonCancel;
    Button mButtonPlus;
    Button mButtonMinus;
    Button mButtonMultiply;
    Button mButtonDivide;
    Button mButtonEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputScreen = findViewById(R.id.output_screen);
        actionChar = findViewById(R.id.action_char);

        mButton0 = findViewById(R.id.button0);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton5 = findViewById(R.id.button5);
        mButton6 = findViewById(R.id.button6);
        mButton7 = findViewById(R.id.button7);
        mButton8 = findViewById(R.id.button8);
        mButton9 = findViewById(R.id.button9);
        mButtonPt = findViewById(R.id.buttonPt);

        mButtonCancel = findViewById(R.id.button_cancel);
        mButtonPlus = findViewById(R.id.button_plus);
        mButtonMinus = findViewById(R.id.button_minus);
        mButtonMultiply = findViewById(R.id.button_multiply);
        mButtonDivide = findViewById(R.id.button_divide);
        mButtonEquals = findViewById(R.id.button_equals);

        addButtonListener(mButton0);
        addButtonListener(mButton1);
        addButtonListener(mButton2);
        addButtonListener(mButton3);
        addButtonListener(mButton4);
        addButtonListener(mButton5);
        addButtonListener(mButton6);
        addButtonListener(mButton7);
        addButtonListener(mButton8);
        addButtonListener(mButton9);

        addFuncButtonListener(mButtonPt);
        addFuncButtonListener(mButtonPlus);
        addFuncButtonListener(mButtonMinus);
        addFuncButtonListener(mButtonMultiply);
        addFuncButtonListener(mButtonDivide);

        addFuncButtonListener(mButtonEquals);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.totalReset();
                outputScreen.setText("0");
                actionChar.setText("   ");
            }
        });
    }

    // СЛУШАТЕЛЬ ЦИФРОВЫХ КНОПОК
    private void addButtonListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumButtonClick(button);
            }
        });
    }

    // СЛУШАТЕЛЬ ФУНКЦИОНАЛЬНЫХ КНОПОК
    private void addFuncButtonListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFuncButtonClick(button);
            }
        });
    }

    private void onNumButtonClick(Button button) {
        // Если число = 0, то блокируем добавление нулей по нажатию на кнопку 0
        if (!(calculator.stringBuilder.length() == 0 && button == mButton0)) {
            calculator.stringBuilder.append(button.getText().toString());
            printNumOnScreen();
        }
        logPrnt();
    }

    private void onFuncButtonClick(Button button) {
        if (button == mButtonPt) {
            onButtonPointClick();
        }

        // Если нажаты не кнопки равно и не точка
        if (button != mButtonEquals && button != mButtonPt) {
            if (calculator.stringBuilder.length() == 0) {
                changeAction(button);
            } else {
                calculator.addCurrentNumber();
                changeAction(button);
                printNumOnScreen(calculator.getStringResult());
            }
        }
        if (button == mButtonEquals) {
            if (calculator.getAction() != '0') {
                calculator.addCurrentNumber();
                clearAction();
                printNumOnScreen(calculator.getStringResult());
            }
        }
        logPrnt();

    }

    private void clearAction() {
        actionChar.setText("   ");
        calculator.setAction('0');
    }

    private void changeAction(Button button) {
        actionChar.setText(button.getText().toString() + "  ");
        calculator.setAction(button.getText().toString().charAt(0));
    }

    private void printNumOnScreen() {
        outputScreen.setText(calculator.stringBuilder);
    }

    private void printNumOnScreen(String s) {
        outputScreen.setText(s);
    }

    public void onButtonPointClick() {
        calculator.setFractionalNumber(true);
        if (calculator.stringBuilder.length() == 0) {
            outputScreen.setText("0");
            calculator.stringBuilder.append(0);
        }

        if (!calculator.isCurrentFractionalNumber()) {
            outputScreen.setText("." + outputScreen.getText());
            calculator.stringBuilder.append(".");
            calculator.setCurrentFractionalNumber(true);
        }
    }

    private void logPrnt() {
        System.out.println("aplog (sb): " + calculator.stringBuilder.toString());
        System.out.println("aplog (firstNum): " + calculator.getFirstNumber());
        System.out.println("aplog (bufNum): " + calculator.getSecondNumber());
//        System.out.println("aplog (isFirstCalc): " + calculator.isFirstCalculation());
//        System.out.println("aplog (curFracNum): " + calculator.isCurrentFractionalNumber());
//        System.out.println("aplog (secondNum): " + calculator.getSecondNumber());
    }
}