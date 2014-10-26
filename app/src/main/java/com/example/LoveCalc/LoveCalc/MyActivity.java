package com.example.LoveCalc.LoveCalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyActivity extends Activity implements android.view.View.OnClickListener{
    Button add, subtract, multiply, divide, mod;
    TextView  output;
    EditText firstInput, secondInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        firstInput = (EditText) findViewById(R.id.number1);
        secondInput = (EditText) findViewById(R.id.number2);
        output = (TextView) findViewById(R.id.output);
        add = (Button) findViewById(R.id.result);

        add.setOnClickListener(this);


    }

    public void onClick(View arg0) {
        // Get values from top two TextViews
        String firstInputValue = firstInput.getText().toString().trim();
        String secondInputValue = secondInput.getText().toString().trim();
        // Initialize output
        String outputValue;


        outputValue = firstInputValue + "love" + secondInputValue;

        // Add result to Running total stored in output TextView
        String result = firstInputValue +" <3s " + secondInputValue + " " + findPercentage(outputValue);
        TextView tvresult = (TextView) findViewById(R.id.output);
        tvresult.setText(result);

    }

    private String findPercentage(String lcString) {

        StringBuffer lcBuffer = new StringBuffer(lcString);
        int len = lcString.length();
        char[] lcCharArray = new char[len];
        lcCharArray = lcString.toCharArray();
        for(int init = 0, init2 = 1; init < lcBuffer.length(); init++, init2++) {
            String sTemp = lcBuffer.substring(init, init2);
            for(int firstIndex = lcBuffer.indexOf(sTemp), lastIndex = lcBuffer.lastIndexOf(sTemp); firstIndex < lastIndex; ) {
                lcBuffer.deleteCharAt(lcBuffer.lastIndexOf(sTemp));
                lastIndex = lcBuffer.lastIndexOf(sTemp);
            }
        }
        String lcBuffString = new String(lcBuffer);
        int lcBuff = lcBuffString.length();
        char[] lcbCharArray = new char[lcBuff];
        lcbCharArray = lcBuffString.toCharArray();
        int[] lcInt = new int[lcBuff];
        int count = 0;
        for(int start = 0; start < lcBuff; start++) {
            for(int start2 = 0; start2 < len; start2++) {
                if(lcbCharArray[start] == lcCharArray[start2]) {
                    count++;
                }
                lcInt[start] = count;
            }
            count = 0;
        }
        ArrayList<Integer> lc = new ArrayList<Integer>();
        for(int ind = 0; ind < lcBuff; ind++) {
            lc.add(lcInt[ind]);
        }
        while(lc.size() != 2) {
            for(int begin = 0, end = lc.size() - 1; begin < end; begin++, end--) {
                lc.set(begin, lc.get(begin) + lc.get(end));
            }
            int counter = lc.size() / 2;
            while(counter-- > 0) {
                lc.remove(lc.size() - 1);
            }
            for(int x = 0; x < lc.size(); x++) {
                if(lc.get(x) >= 10) {
                    int put = lc.get(x) - 10;
                    lc.set(x, 1);
                    lc.add(x, put);
                }
            }
        }
        int one = lc.get(0);
        int two = lc.get(1);
        return  one+""+two+"%";
    }

}
