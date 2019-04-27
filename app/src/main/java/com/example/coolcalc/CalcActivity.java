package com.example.coolcalc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalcActivity extends Activity {

    TextView resultsView;

    public enum Operation {
        ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUAL
    }
    Operation currentOper;

    String runningNumber = "";
    String leftValueStr = "";
    String rightValueStr = "";

    double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Button oneBtn = (Button) findViewById(R.id.oneBtn);
        Button twoBtn = (Button) findViewById(R.id.twoBtn);
        Button threeBtn = (Button) findViewById(R.id.threeBtn);
        Button fourBtn = (Button) findViewById(R.id.fourBtn);
        Button fiveBtn = (Button) findViewById(R.id.fiveBtn);
        Button sixBtn = (Button) findViewById(R.id.sixBtn);
        Button sevenBtn = (Button) findViewById(R.id.sevenBtn);
        Button eightBtn = (Button) findViewById(R.id.eightBtn);
        Button nineBtn = (Button) findViewById(R.id.nineBtn);
        Button zeroBtn = (Button) findViewById(R.id.zeroBtn);

        ImageButton calcBtn = (ImageButton) findViewById(R.id.calcBtn);
        ImageButton divideBtn = (ImageButton) findViewById(R.id.divideBtn);
        ImageButton multiplyBtn = (ImageButton) findViewById(R.id.multiplyBtn);
        ImageButton subtractBtn = (ImageButton) findViewById(R.id.subtractBtn);
        ImageButton addBtn = (ImageButton) findViewById(R.id.addBtn);
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);

        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        resultsView = (TextView) findViewById(R.id.resultsText);

        numberPressed(0);

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(1);
            }
        });

        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(2);
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(3);
            }
        });

        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(4);
            }
        });

        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(5);
            }
        });

        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(6);
            }
        });

        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(7);
            }
        });

        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(8);
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(9);
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(0);
            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.EQUAL);
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.DIVIDE);
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.MULTIPLY);
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.SUBTRACT);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.ADD);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePressed();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftValueStr = "";
                rightValueStr = "";
                result = 0;
                runningNumber = "";
                currentOper = null;
                numberPressed(0);
            }
        });
    }

    void processOperation(Operation oper) {

        if(currentOper != null) {
            if(runningNumber != "") {
                rightValueStr = runningNumber;
                runningNumber = "";

                switch (currentOper) {
                    case ADD:
                        result = Double.parseDouble(leftValueStr) + Double.parseDouble(rightValueStr);
                        break;
                    case SUBTRACT:
                        result = Double.parseDouble(leftValueStr) - Double.parseDouble(rightValueStr);
                        break;
                    case MULTIPLY:
                        result = Double.parseDouble(leftValueStr) * Double.parseDouble(rightValueStr);
                        break;
                    case DIVIDE:
                        result = Double.parseDouble(leftValueStr) / Double.parseDouble(rightValueStr);
                        break;
                }

                leftValueStr = String.valueOf(result);
                resultsView.setText(leftValueStr);
            }
        } else {
            leftValueStr = runningNumber;
            runningNumber = "";
        }

        currentOper = oper;
    }

    void numberPressed(int number) {
        if(!resultsView.getText().equals("0")) {
            runningNumber += String.valueOf(number);
            resultsView.setText(runningNumber);
        } else {
            runningNumber = String.valueOf(number);
            resultsView.setText(runningNumber);
        }
    }

    void deletePressed() {
        //String s = resultsView.getText().toString();
        int c = 0, d = 0;

        if(!runningNumber.equals("")) {
            while(runningNumber.length() > 0 && c < 1) {
                runningNumber = runningNumber.substring(0, runningNumber.length()-1);
                c++;
            }

            if(runningNumber.length() == 0) {
                numberPressed(0);
            } else {
                resultsView.setText(runningNumber);
            }
        } else if(!leftValueStr.equals("")) {
            while(leftValueStr.length() > 0 && d < 1) {
                if(leftValueStr.charAt(0) != 45) {
                    result = Double.parseDouble(leftValueStr);
                    Log.i("result", String.valueOf(result));
                } else {
                    result = 0.0;
                    Log.i("result", String.valueOf(result));
                }
                leftValueStr = leftValueStr.substring(0, leftValueStr.length()-1);
                d++;
            }

            if(leftValueStr.length() == 0) {
                leftValueStr = "0";
                result = Double.parseDouble(leftValueStr);
                numberPressed(0);
            } else {
                resultsView.setText(leftValueStr);
            }
        }
        Log.i("leftValueStr", leftValueStr);
        Log.i("runningNumber", runningNumber);
        Log.i("result", String.valueOf(result));
    }
}
