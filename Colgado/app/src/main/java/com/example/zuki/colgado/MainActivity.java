package com.example.zuki.colgado;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<TextView> characters;
    String word;
    int guessed;
    int errors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        characters=new ArrayList<TextView>();
        word=getWord();
        guessed=0;
        errors=0;
        for(int i=0;i<word.length();i++)
            characters.add(createTextView());

     }

    public static int randInt(int min, int max) {


        Random rand=new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public String getWord()
    {
        String[] words={"ROJO","AMARILLO","VERDE"};
        return words[randInt(0,2)];
    }
    public TextView createTextView()
    {
        LinearLayout layout=(LinearLayout)findViewById(R.id.letras);
        TextView textView2 = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
        textView2.setLayoutParams(layoutParams);
        textView2.setText("-");
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        layout.addView(textView2);
        return textView2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public boolean verifyLetter(char letter)
    {
        boolean result=false;
        for(int i=0;i<word.length();i++)
        {
            if(word.charAt(i)==letter) {
                result=true;
                guessed++;
                characters.get(i).setText(letter+"");
            }
        }
        return result;
    }
    public void addError()
    {
        ImageView image=(ImageView)findViewById(R.id.imageView);
        int imageId=R.drawable.i1;
        switch(errors)
        {
            case 1:
                imageId=R.drawable.i2;
                break;
            case 2:
                imageId=R.drawable.i3;
                break;
            case 3:
                imageId=R.drawable.i4;
                break;
            case 4:
                imageId=R.drawable.i5;
                break;
            case 5:
                imageId=R.drawable.i6;
                break;
            case 6:
                imageId=R.drawable.i7;
                break;
        }
        image.setImageResource(imageId);
    }
    public void endGame(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("Juego Terminado")
                .setMessage(message)
                .setPositiveButton("Nuevo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }).show().setCanceledOnTouchOutside(false);
    }
    public void doClick(View view)
    {
        view.setEnabled(false);
        Button button=(Button) view;
        char letter=button.getText().toString().charAt(0);
        if(!verifyLetter(letter))
        {
            errors++;
            addError();
        }
        if(errors==6)
        {
            endGame("Perdiste!");
        }
        if(guessed==word.length())
            endGame("Ganaste!");

    }
}
