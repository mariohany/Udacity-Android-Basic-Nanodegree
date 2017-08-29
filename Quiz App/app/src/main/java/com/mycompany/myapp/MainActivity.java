package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	public void SubmitAnswer(View view){
		int counter=0;
		String wrongQuestions = "\nPlease Check:";
		if(question1()){
			counter++;
		}else{
			wrongQuestions+="\nQuestion 1";
		}
		if(question2()){
			counter++;
		}else{
			wrongQuestions+="\nQuestion 2";
		}
		if(question3()){
			counter++;
		}else{
			wrongQuestions+="\nQuestion 3";
		}
		if(question4()){
			counter++;
		}else{
			wrongQuestions+="\nQuestion 4";
		}
		if(question5()){
			counter++;
		}else{
			wrongQuestions+="\nQuestion 5";
		}
		if(question6()){
			counter++;
		}else{
			wrongQuestions+="\nQuestion 6";
		}
		String submitMsg="you answerd "+counter+"/6 Right";
		if(counter==6){
			Toast toast = Toast.makeText(this,"Awesome!\n"+submitMsg,Toast.LENGTH_LONG);
			toast.show();
		}else{
			Toast toast = Toast.makeText(this,submitMsg+wrongQuestions,Toast.LENGTH_LONG);
			toast.show();
		}
	}

	public boolean question1(){
		RadioButton test = (RadioButton) findViewById(R.id.q1_a1_radio_button);
		if(test.isChecked())
			return true;
		return false;
	}

	public boolean question2(){
		CheckBox answer1 = (CheckBox) findViewById(R.id.hammer_checkbox);
		CheckBox answer2 = (CheckBox) findViewById(R.id.saw_checkbox);
		CheckBox answer3 = (CheckBox) findViewById(R.id.nails_checkbox);
		CheckBox answer4 = (CheckBox) findViewById(R.id.spoon_checkbox);
		if(answer1.isChecked()&&answer2.isChecked()&&answer3.isChecked()&&!answer4.isChecked()){
			return true;
		}
		return false;
	}

	public boolean question3(){
		EditText answer = (EditText) findViewById(R.id.question3_edittext);
		if(answer.getText().toString().equalsIgnoreCase("Google")){
			return true;
		}
		return false;
	}

	public boolean question4(){
		CheckBox answer1 = (CheckBox) findViewById(R.id.cplusplus_checkbox);
		CheckBox answer2 = (CheckBox) findViewById(R.id.java_checkbox);
		CheckBox answer3 = (CheckBox) findViewById(R.id.english_checkbox);
		CheckBox answer4 = (CheckBox) findViewById(R.id.french_checkbox);
		if(answer1.isChecked()&&answer2.isChecked()&&!answer3.isChecked()&&!answer4.isChecked()){
			return true;
		}
		return false;
	}

	public boolean question5(){
		EditText answer2 = (EditText) findViewById(R.id.question5_edittext);
		if(answer2.getText().toString().equalsIgnoreCase("Uber")){
			return true;
		}
		return false;
	}

	public boolean question6(){
		RadioButton answer =(RadioButton) findViewById(R.id.q6_a2_radio_button);
		if(answer.isChecked()){
			return true;
		}
		return false;
	}
}
