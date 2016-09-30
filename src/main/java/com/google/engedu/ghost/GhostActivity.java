package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

//75 46 106 35
public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private SuggestionDictionary dictionary;

    private Random random = new Random();

    InputStream inputstr;

    TextView textTV;
    TextView suggestionTV;
    Button submitButton;
    Button restartButton;
    String text="";
    ArrayList<String> textlist=new ArrayList<String>();
    ArrayList<String> faultyList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        try {
            inputstr = getAssets().open("words.txt");


            dictionary=new SuggestionDictionary(inputstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        suggestionTV = (TextView) findViewById(R.id.gameStatus);
        textTV = (TextView) findViewById(R.id.ghostText);
        submitButton = (Button) findViewById(R.id.button);
        restartButton = (Button) findViewById(R.id.button2);
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onStart(View view) {
       textTV.setText("");
        Toast.makeText(getApplicationContext(), "restart clicked", Toast.LENGTH_SHORT);
        submitButton.setEnabled(true);
        restartButton.setEnabled(true);
      return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        String newChar=(char)event.getUnicodeChar()+"";


        if(textTV.getText()==null)
        {
            textTV.setText(newChar);
        }
      else
        {
            String alreadyTyped=textTV.getText().toString();
            textTV.setText(alreadyTyped+newChar);
        }
        return super.onKeyUp(keyCode, event);
    }

    public void submitMethod(View v)
    {
        submitButton.setEnabled(false);
        Toast.makeText(getApplicationContext(), "submit clicked", Toast.LENGTH_SHORT).show();

        text=textTV.getText().toString();

        String arr[]=text.split(" ");
        int i=0;
        for(i=0;i<arr.length;i++)
        {
            textlist.add(arr[i]);

            if(dictionary.isWord(arr[i])==false&&!arr[i].equals(" "))
                faultyList.add(arr[i]);
        }
        String temp="";
        String suggest="";
        //textTV.setText(" ");
        for(i=0;i<faultyList.size();i++)
        {
            suggest=dictionary.suggestion2(faultyList.get(i));
            temp=temp+" "+faultyList.get(i)+" : "+suggest+"\t";
        }
        suggestionTV.setText(temp);

    }
}
