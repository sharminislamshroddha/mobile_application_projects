package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String showCal = "" ;
    ArrayList<String> inputStr = new ArrayList<String>();
    float output;

    TextView displayCal , result;
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_Add, button_Sub,
            button_Mul, button_Div, button_Equ, button_Del, button_open, button_close, button_CE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_CE = (Button) findViewById(R.id.btn_CE);
        button_open = (Button) findViewById(R.id.btn_open);
        button_close = (Button) findViewById(R.id.btn_close);
        button_0 = (Button) findViewById(R.id.btn0);
        button_1 = (Button) findViewById(R.id.btn1);
        button_2 = (Button) findViewById(R.id.btn2);
        button_3 = (Button) findViewById(R.id.btn3);
        button_4 = (Button) findViewById(R.id.btn4);
        button_5 = (Button) findViewById(R.id.btn5);
        button_6 = (Button) findViewById(R.id.btn6);
        button_7 = (Button) findViewById(R.id.btn7);
        button_8 = (Button) findViewById(R.id.btn8);
        button_9 = (Button) findViewById(R.id.btn9);
        button_Add = (Button) findViewById(R.id.btn_add);
        button_Sub = (Button) findViewById(R.id.btn_sub);
        button_Mul = (Button) findViewById(R.id.btn_mul);
        button_Div = (Button) findViewById(R.id.btn_div);
        button_Del = (Button) findViewById(R.id.btnAC);
        button_Equ = (Button) findViewById(R.id.btnEQ);

        displayCal = (TextView) findViewById(R.id.textViewCal);
        result = (TextView) findViewById(R.id.textViewResult);

        button_open.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                showCal= showCal+"(";
                inputStr.add("(");
                displayCal.setText(showCal);
            }
        });

        button_close.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                showCal= showCal+")";
                inputStr.add(")");
                displayCal.setText(showCal);
            }
        });

        button_CE.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                inputStr.remove(inputStr.size()-1);
                showCal = "";
                for(String i: inputStr){
                    showCal = showCal + i;
                }
                displayCal.setText(showCal);
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")

            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }

            @Override
            public void onClick(View v) {
                showCal= showCal+"1";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"1");
                }
                else{
                    inputStr.add("1");
                }
                displayCal.setText(showCal);
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")

            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }

            @Override
            public void onClick(View v) {
                showCal= showCal+"2";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"2");
                }
                else{
                    inputStr.add("2");
                }
                displayCal.setText(showCal);
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"3";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"3");
                }
                else{
                    inputStr.add("3");
                }
                displayCal.setText(showCal);
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"4";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"4");
                }
                else{
                    inputStr.add("4");
                }
                displayCal.setText(showCal);
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"5";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"5");
                }
                else{
                    inputStr.add("5");
                }
                displayCal.setText(showCal);
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"6";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"6");
                }
                else{
                    inputStr.add("6");
                }
                displayCal.setText(showCal);
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"7";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"7");
                }
                else{
                    inputStr.add("7");
                }
                displayCal.setText(showCal);
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"8";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"8");
                }
                else{
                    inputStr.add("8");
                }
                displayCal.setText(showCal);
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"9";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"9");
                }
                else{
                    inputStr.add("9");
                }
                displayCal.setText(showCal);
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public boolean checkPrevIsANumber(){
                if(inputStr.get(inputStr.size()-1) == "+" || inputStr.get(inputStr.size()-1) == "-" || inputStr.get(inputStr.size()-1) == "x" ||
                        inputStr.get(inputStr.size()-1) == "/" || inputStr.get(inputStr.size()-1) == "(" || inputStr.get(inputStr.size()-1) == ")"){
                    return false;
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                showCal= showCal+"0";
                if(inputStr.size()>0 && checkPrevIsANumber()){
                    inputStr.set(inputStr.size()-1, inputStr.get(inputStr.size()-1)+"0");
                }
                else{
                    inputStr.add("0");
                }
                displayCal.setText(showCal);
            }
        });
        button_Add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                inputStr.add("+");
                if(result.getText()!=""){
                    showCal = result.getText().toString();
                }
                result.setText("");
                showCal+="+";
                displayCal.setText(showCal);
            }
        });


        button_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.getText()!=""){
                    showCal = result.getText().toString();
                }
                result.setText("");
                showCal+="-";
                inputStr.add("-");
                displayCal.setText(showCal);
            }
        });

        button_Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.getText()!=""){
                    showCal = result.getText().toString();
                }
                result.setText("");
                showCal+="x";
                inputStr.add("x");
                displayCal.setText(showCal);
            }
        });

        button_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.getText()!=""){
                    showCal = result.getText().toString();
                }
                result.setText("");
                showCal+="/";
                inputStr.add("/");
                displayCal.setText(showCal);
            }
        });

        button_Equ.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")

            public String viewArrayList(ArrayList<String>input){
                String st = "";
                for(String i: input){
                    st = st + i;
                }
                return st;
            }

            public ArrayList<String> operatorCal(ArrayList<String>input,String operator){
                while(input.contains(operator)) {
                    int ind;
                    float prevEl = 0, nextEl = 0;
                    ind = input.indexOf(operator);
                    prevEl = Float.parseFloat(input.get(ind-1));
                    nextEl = Float.parseFloat(input.get(ind+1));

                    String n = "";
                    if(operator == "+"){
                        n = Float.toString(prevEl+nextEl);
                    }
                    else if(operator == "-"){
                        n = Float.toString(prevEl-nextEl);
                    }
                    else if(operator == "x"){
                        n = Float.toString(prevEl*nextEl);
                    }
                    else if(operator == "/"){
                        n = Float.toString(prevEl/nextEl);
                    }

                    input.set(ind-1, n);
                    input.remove(ind);
                    input.remove(ind);
                }
                return input;
            }

            public String simpleCal(ArrayList<String> input){
                input = operatorCal(input,"/");
                input = operatorCal(input,"x");
                input = operatorCal(input,"+");
                input = operatorCal(input,"-");
                return input.get(0);
            }

            public ArrayList<String> paranthesisCal(ArrayList<String> input){

                ArrayList<String> tmp = new ArrayList<String>();

                int closeInd = input.indexOf(")");
                int openInd = 0;
                for(int i=0; i<inputStr.size(); i++){
                    if(input.get(i)=="("){
                        openInd = i;
                    }
                }

                for(int k = openInd+1; k<closeInd; k++ ){
                        tmp.add(input.get(k));
                        System.out.println("input char: "+input.get(k));
                }
                result.setText(viewArrayList(tmp));

                String st = simpleCal(tmp);
                inputStr.set(openInd, st);
                System.out.println(viewArrayList(inputStr));

                for(int k = openInd+1; k<=closeInd; k++ ){
                    System.out.println("k = "+k);
                    System.out.println("input: "+viewArrayList(inputStr));
                    inputStr.remove(openInd+1);
                }
                tmp.clear();
                return input;
            }

            public boolean canCalculate(){
                int cnt =0;
                for(int i=0; i<inputStr.size(); i++){
                    if(inputStr.get(i) == "("){
                        cnt++;
                    }
                    else if(inputStr.get(i) == ")"){
                        cnt--;
                    }
                }
                if(cnt != 0){
                    return false;
                }

                for(int i=1; i<inputStr.size(); i++){
                    if((inputStr.get(i-1) == "+" || inputStr.get(i-1) == "-" || inputStr.get(i-1) == "x" || inputStr.get(i-1) == "/") &&
                            (inputStr.get(i) == "+" || inputStr.get(i) == "-" || inputStr.get(i) == "x" || inputStr.get(i) == "/")){
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void onClick(View v) {

                if(canCalculate()){
                    while(inputStr.contains("(")){
                        inputStr = paranthesisCal(inputStr);
                    }

                    String s = simpleCal(inputStr);

                    result.setText(s);
                }
                else{
                    result.setText("Error! Click on the AC button.");
                }

            }
        });

        button_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               displayCal.setText("");
               result.setText("");
               showCal = "";
               inputStr.clear();
            }
        });

    }
}
