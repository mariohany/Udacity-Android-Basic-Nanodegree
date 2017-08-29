package com.mycompany.project2;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.text.*;
import android.net.http.*;

import java.net.*;public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	int aGoal=0;
	int bGoal=0;
	int aFoul=0;
	int bFoul=0;
	public void displaygoalb(int number){
		String x=Integer.toString(number);
		TextView bpoints=(TextView) findViewById(R.id.team_b_points);
		bpoints.setText(x);
	}
	public void addb(View view){
		bGoal+=1;
		displaygoalb(bGoal);
	}
	public void displaygoala(int number){
		String x=Integer.toString(number);
		TextView apoints=(TextView) findViewById(R.id.team_a_points);
		apoints.setText(x);
	}
	public void adda(View view){
		aGoal+=1;
		displaygoala(aGoal);
	}
	public void reset(View view){
		aGoal=0;
		bGoal=0;
		aFoul=0;
		bFoul=0;
		displaygoalb(bGoal);
		displaygoala(aGoal);
		displayfoula(aFoul);
		displayfoulb(bFoul);
	}
	public void addfoula(View view){
		aFoul+=1;
		displayfoula(aFoul);
	}
	public void displayfoula(int number){
		String x=Integer.toString(number);
		TextView afouls=(TextView) findViewById(R.id.team_a_foul);
		afouls.setText(x);
	}
	public void addfoulb(View view){
		bFoul+=1;
		displayfoulb(bFoul);
	}
	public void displayfoulb(int number){
		String x=Integer.toString(number);
		TextView bfouls=(TextView) findViewById(R.id.team_b_foul);
		bfouls.setText(x);
	}
}
