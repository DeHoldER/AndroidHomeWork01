package ru.geekbrains.homework01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mFirstNumber = 0;
    private int mSecondNumber = 0;
    private int result = 0;
    private boolean isZero = true;
    private boolean isFirstNumber = true;
    private int action = 0;

    TextView outputScreen;

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


        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumButtonClick(mButton7);
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumButtonClick(mButton1);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumButtonClick(mButton2);
            }
        });

    }


    private void onNumButtonClick(Button button) {
        if (isZero) {
            outputScreen.setText(button.getText().toString());
            isZero = false;
        } else outputScreen.setText(outputScreen.getText() + button.getText().toString());
    }
}