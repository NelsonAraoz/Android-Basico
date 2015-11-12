package com.example.zuki.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.zuki.calculator.managers.CalculatorManager;
public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private CalculatorManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult=(TextView)findViewById(R.id.txt_result);
        manager=new CalculatorManager();
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

    public void doClick(View view)
    {
        if(view.getId()==R.id.plus_btn || view.getId()==R.id.minus_btn || view.getId()==R.id.btn_product || view.getId()==R.id.btn_div || view.getId()==R.id.btn_open || view.getId()==R.id.btn_close ||  view.getId()==R.id.btn_dot)
        {
            Button button = (Button) view;
            txtResult.setText(manager.putOperation(button.getText().toString()));
        }
        else if(view.getId()==R.id.btn_equal)
        {
            txtResult.setText(manager.getResult());
        }
        else if(view.getId()==R.id.btn_clear)
        {
          txtResult.setText(manager.clear());
        }
        else if(view.getId()==R.id.btn_ce)
        {
            txtResult.setText(manager.removeOne());
        }
        else{
            Button button = (Button) view;
            txtResult.setText(manager.putNumber(button.getText().toString()));
        }

    }
}
