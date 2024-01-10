package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView screen;

    Button b1;
    Button b2;
    Button b3;
    Button badd;

    Button b4;
    Button b5;
    Button b6;
    Button bsubtract;

    Button b7;
    Button b8;
    Button b9;
    Button bmultiply;

    Button bclear;
    Button b0;
    Button bequals;
    Button bdivide;

    TextView name;

    boolean clearZeroPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        badd = findViewById(R.id.buttonadd);

        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        bsubtract = findViewById(R.id.buttonsubtract);

        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        bmultiply = findViewById(R.id.buttonmultiply);

        bclear = findViewById(R.id.buttonclear);
        b0 = findViewById(R.id.button0);
        bequals = findViewById(R.id.buttonequals);
        bdivide = findViewById(R.id.buttondivide);

        name = findViewById(R.id.name);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        badd.setOnClickListener(this);

        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        bsubtract.setOnClickListener(this);

        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bmultiply.setOnClickListener(this);

        bclear.setOnClickListener(this);
        b0.setOnClickListener(this);
        bequals.setOnClickListener(this);
        bdivide.setOnClickListener(this);

        clearZeroPresent = true;

        b1.setBackgroundColor(Color.rgb(49, 62, 99));
        b2.setBackgroundColor(Color.rgb(49, 62, 99));
        b3.setBackgroundColor(Color.rgb(49, 62, 99));
        badd.setBackgroundColor(Color.rgb(49, 62, 99));

        b4.setBackgroundColor(Color.rgb(49, 62, 99));
        b5.setBackgroundColor(Color.rgb(49, 62, 99));
        b6.setBackgroundColor(Color.rgb(49, 62, 99));
        bsubtract.setBackgroundColor(Color.rgb(49, 62, 99));

        b7.setBackgroundColor(Color.rgb(49, 62, 99));
        b8.setBackgroundColor(Color.rgb(49, 62, 99));
        b9.setBackgroundColor(Color.rgb(49, 62, 99));
        bmultiply.setBackgroundColor(Color.rgb(49, 62, 99));

        bclear.setBackgroundColor(Color.rgb(49, 62, 99));
        b0.setBackgroundColor(Color.rgb(49, 62, 99));
        bequals.setBackgroundColor(Color.rgb(49, 62, 99));
        bdivide.setBackgroundColor(Color.rgb(49, 62, 99));

    }


    @Override
    public void onClick(View view) {
        Button b = (Button)view;

        if(screen.getText().equals("ERROR")){
            clearZeroPresent = true;
        }
        if(view.getId() == R.id.buttonclear){
            screen.setText("0");
            clearZeroPresent = true;
        }
        else if(view.getId() == R.id.buttonequals){
            screen.setText(calculate(screen.getText().toString()));

        }
        else if(clearZeroPresent){
            screen.setText(b.getText());
            clearZeroPresent = false;
        }
        else{
            screen.setText(screen.getText().toString() + b.getText());
            clearZeroPresent = false;
        }

        checkParams(screen.getText().toString());

    }

    public void checkParams(String ans){
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) screen.getLayoutParams();

        if(ans.length() > 13){
         params.bottomMargin = 2;
        }
        else{
            params.bottomMargin = 164;
        }

    }

    public String calculate(String calc) {

        StringTokenizer st = new StringTokenizer(calc, "+-*/", true);
        ArrayList<String> pieces = new ArrayList<String>();
        String result = "";

        while (st.hasMoreTokens()) {
            String current = st.nextToken();
            pieces.add(current);

        }

        if(pieces.get(0).equals("+") || pieces.get(0).equals("-") || pieces.get(0).equals("*") || pieces.get(0).equals("/")){
            result = "ERROR";
        }
        else if(pieces.get(pieces.size() - 1).equals("+") || pieces.get(pieces.size() - 1).equals("-") || pieces.get(pieces.size() - 1).equals("*") || pieces.get(pieces.size() - 1).equals("/")){
            result = "ERROR";
        }
        else{
            for(int i=0;i<pieces.size() - 1;i++){
                if((pieces.get(i).equals("+") || pieces.get(i).equals("-") || pieces.get(i).equals("*") || pieces.get(i).equals("/")) && (pieces.get(i + 1).equals("+") || pieces.get(i + 1).equals("-") || pieces.get(i + 1).equals("*") || pieces.get(i + 1).equals("/"))){
                    result = "ERROR";
                }
            }

            if(!result.equals("ERROR")){
                try{
                    for (int i = 0; i < pieces.size(); i++) {

                        if (pieces.get(i).equals("*")) {
                            double product = Double.parseDouble(pieces.remove(i - 1)) * Double.parseDouble(pieces.remove(i));

                            if (product%1 == 0) {
                                pieces.set(i - 1, Integer.toString((int)product));
                            } else {
                                pieces.set(i - 1, Double.toString(product));
                            }
                            i--;
                        } else if (pieces.get(i).equals("/")) {
                            double quotient = Double.parseDouble(pieces.remove(i - 1)) / Double.parseDouble(pieces.remove(i));

                            if (quotient%1 == 0) {
                                pieces.set(i - 1, Integer.toString((int)quotient));
                            } else {
                                pieces.set(i - 1, Double.toString(quotient));
                            }
                            i--;
                        }
                    }

                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).equals("+")) {
                            double sum = Double.parseDouble(pieces.remove(i - 1)) + Double.parseDouble(pieces.remove(i));

                            if (sum%1 == 0) {
                                pieces.set(i - 1, Integer.toString((int)sum));
                            } else {
                                pieces.set(i - 1, Double.toString(sum));
                            }
                            i--;
                        } else if (pieces.get(i).equals("-")) {
                            double difference = Double.parseDouble(pieces.remove(i - 1)) - Double.parseDouble(pieces.remove(i));

                            if (difference%1 == 0) {
                                pieces.set(i - 1, Integer.toString((int)difference));
                            } else {
                                pieces.set(i - 1, Double.toString(difference));
                            }
                            i--;
                        }
                    }
                }catch(ArithmeticException e){
                    result = "ERROR";
                }

                if(pieces.get(0).equals("Infinity") || pieces.get(0).equals("NaN")){
                    result = "ERROR";
                }
                else{
                    result = pieces.get(0);
                }
            }
        }

        return result;
    }
}