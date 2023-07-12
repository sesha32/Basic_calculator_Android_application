package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;

public class MainActivity extends AppCompatActivity {

    private EditText etResult;
    private double operand1 = Double.NaN;
    private double operand2 = 0.0;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etResult = findViewById(R.id.etResult);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String currentText = etResult.getText().toString();

        switch (buttonText) {
            case "C":
                operand1 = Double.NaN;
                operand2 = 0.0;
                operator = ' ';
                etResult.setText("");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!Double.isNaN(operand1)) {
                    operand2 = Double.parseDouble(currentText);
                    calculateResult();
                    operator = buttonText.charAt(0);
                    etResult.setText(String.valueOf(operand1));
                } else {
                    operand1 = Double.parseDouble(currentText);
                    operator = buttonText.charAt(0);
                    etResult.setText("");
                }
                break;
            case "=":
                if (!Double.isNaN(operand1)) {
                    operand2 = Double.parseDouble(currentText);
                    calculateResult();
                    operator = ' ';
                    etResult.setText(String.valueOf(operand1));
                }
                break;
            default:
                etResult.setText(currentText + buttonText);
                break;
        }
    }

    private void calculateResult() {
        if (!Double.isNaN(operand2)) {
            switch (operator) {
                case '+':
                    operand1 += operand2;
                    break;
                case '-':
                    operand1 -= operand2;
                    break;
                case '*':
                    operand1 *= operand2;
                    break;
                case '/':
                    if (operand2 != 0) {
                        operand1 /= operand2;
                    }
                    break;
            }
        } else {
            operand1 = Double.parseDouble(etResult.getText().toString());
        }
    }
}
