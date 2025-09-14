package by.bsuir.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView historyText;
    private TextView outputText;
    private double currentNumber = 0;
    private double memoryNumber = 0;
    private String operator = "";
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyText = findViewById(R.id.historytext);
        outputText = findViewById(R.id.outputtext);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        Button dotButton = findViewById(R.id.dotbutton);
        Button computationButton = findViewById(R.id.computationbutton);
        Button additionButton = findViewById(R.id.additionbutton);
        Button subtractionButton = findViewById(R.id.substractionbutton);
        Button multiplyButton = findViewById(R.id.multiplybutton);
        Button divisionButton = findViewById(R.id.divisionbutton);
        Button invertButton = findViewById(R.id.invertbutton);
        Button sqrtButton = findViewById(R.id.sqrtbutton);
        Button clearButton = findViewById(R.id.clearbutton);
        Button plusminusbutton = findViewById(R.id.plusminusbutton);

        Button m_plus_button = findViewById(R.id.m_plus_button);
        Button m_minus_button = findViewById(R.id.m_minus_button);
        Button msbutton = findViewById(R.id.msbutton);
        Button mrbutton = findViewById(R.id.mrbutton);
        Button mcbutton = findViewById(R.id.mcbutton);

        button0.setOnClickListener(v -> appendNumber("0"));
        button1.setOnClickListener(v -> appendNumber("1"));
        button2.setOnClickListener(v -> appendNumber("2"));
        button3.setOnClickListener(v -> appendNumber("3"));
        button4.setOnClickListener(v -> appendNumber("4"));
        button5.setOnClickListener(v -> appendNumber("5"));
        button6.setOnClickListener(v -> appendNumber("6"));
        button7.setOnClickListener(v -> appendNumber("7"));
        button8.setOnClickListener(v -> appendNumber("8"));
        button9.setOnClickListener(v -> appendNumber("9"));

        dotButton.setOnClickListener(v -> appendDecimal());
        computationButton.setOnClickListener(v -> computeResult());
        additionButton.setOnClickListener(v -> setOperator("+"));
        subtractionButton.setOnClickListener(v -> setOperator("-"));
        multiplyButton.setOnClickListener(v -> setOperator("*"));
        divisionButton.setOnClickListener(v -> setOperator("/"));
        plusminusbutton.setOnClickListener(v -> invertSign());
        invertButton.setOnClickListener(v -> invertInput());
        sqrtButton.setOnClickListener(v -> computeSqrt());
        clearButton.setOnClickListener(v -> clearInput());

        m_plus_button.setOnClickListener(v -> memoryPlus());
        m_minus_button.setOnClickListener(v -> memoryMinus());
        msbutton.setOnClickListener(v -> memoryStore());
        mrbutton.setOnClickListener(v -> memoryRead());
        mcbutton.setOnClickListener(v -> memoryClear());

        Button backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(v -> removeLastDigit());
    }

    private void removeLastDigit() {
        String currentInput = outputText.getText().toString();
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            outputText.setText(currentInput);
        }
    }

    private void invertSign() {
        double input = Double.parseDouble(outputText.getText().toString());
        if (outputText.getText().toString().isEmpty()) {
            return;
        }
        if (input == 0) {
            return;
        }
        outputText.setText(String.valueOf(-1 * input));
    }

    private void appendNumber(String digit) {
        String currentString = outputText.getText().toString();
        if (!currentString.isEmpty() && currentString.charAt(0) == '0') {
            if (currentString.length() == 1) {
                isNewInput = true;
            }
            if (currentString.length() > 1 && !(currentString.charAt(1) == '.')) {
                isNewInput = true;
            }
        }
        if (isNewInput) {
            outputText.setText(digit);
            isNewInput = false;
        } else {
            outputText.append(digit);
        }
    }

    private void appendDecimal() {
        if (!outputText.getText().toString().contains(".")) {
            outputText.append(".");
        }
    }

    private void setOperator(String op) {
        if (outputText.getText().toString().isEmpty()) {
            currentNumber = Double.valueOf(0);
        }
        else {
            currentNumber = Double.parseDouble(outputText.getText().toString());
        }
        operator = op;
        historyText.setText(currentNumber + " " + operator);
        isNewInput = true;
    }

    private void computeResult() {
        double newNumber;
        if (outputText.getText().toString().isEmpty()) {
            newNumber = Double.valueOf(0);
        }
        else {
            newNumber = Double.parseDouble(outputText.getText().toString());
        }
        switch (operator) {
            case "+":
                currentNumber += newNumber;
                break;
            case "-":
                currentNumber -= newNumber;
                break;
            case "*":
                currentNumber *= newNumber;
                break;
            case "/":
                if (newNumber == 0) {
                    historyText.setText("Attempt to divide by 0");
                    outputText.setText("");
                    isNewInput = true;
                    return;
                }
                currentNumber /= newNumber;
                break;
            case "":
                return;
        }
        outputText.setText(String.valueOf(currentNumber));
        historyText.setText("");
        operator = "";
        isNewInput = true;
    }

    private void invertInput() {
        double input;
        if (outputText.getText().toString().isEmpty()) {
            historyText.setText("Attempt to divide by 0");
            return;
        } else {
            input = Double.parseDouble(outputText.getText().toString());
        }
        if (Double.parseDouble(outputText.getText().toString()) == 0) {
            historyText.setText("Attempt to divide by 0");
            return;
        }
        outputText.setText(String.valueOf(1 / input));
    }

    private void computeSqrt() {
        double input;
        if (outputText.getText().toString().isEmpty()) {
            input = 0;
        }
        else {
            input =  Double.parseDouble(outputText.getText().toString());
        }
        if (input > 0 || input == 0) {
            outputText.setText(String.valueOf(Math.sqrt(input)));
        }
        else {
            historyText.setText("Attempt to compute square root of a number lower than 0");
        }
        isNewInput = true;
    }

    private void clearInput() {
        outputText.setText("");
        historyText.setText("");
        currentNumber = 0;
        isNewInput = true;
    }

    private void memoryRead() {
        outputText.setText(String.valueOf(memoryNumber));
        isNewInput = true;
    }

    private void memoryStore() {
        double input = Double.parseDouble(outputText.getText().toString());
        if (outputText.getText().toString().isEmpty()) {
            input = 0;
        }
        memoryNumber = Double.valueOf(input);
        isNewInput = true;
    }

    private void memoryClear() {
        memoryNumber = 0;
        isNewInput = true;
    }

    private void memoryPlus () {
        double input = Double.parseDouble(outputText.getText().toString());
        if (outputText.getText().toString().isEmpty()) {
            input = 0;
        }
        memoryNumber = memoryNumber + input;
        isNewInput = true;
    }

    private void memoryMinus() {
        double input = Double.parseDouble(outputText.getText().toString());
        if (outputText.getText().toString().isEmpty()) {
            input = 0;
        }
        memoryNumber = memoryNumber - input;
        isNewInput = true;
    }
}