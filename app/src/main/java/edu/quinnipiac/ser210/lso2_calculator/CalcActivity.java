package edu.quinnipiac.ser210.lso2_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {
    EditText input1, input2;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = (Button) findViewById(R.id.addition);
        Button sub = (Button) findViewById(R.id.subtraction);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operations, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        res = (TextView) findViewById(R.id.result);

        add.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
                performOp('+');
           }
        });

        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                performOp('-');
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                String selectedItem = parent.getItemAtPosition(pos).toString();

                if((input1.getText() != null) && (input2.getText() != null)){
                    switch(selectedItem){
                        case "*":
                            performOp('*');
                            break;
                        case "/":
                            performOp('/');
                            break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void performOp(char op){
        Log.v("Debug","method performOp called");
        if((input1.getText() != null) && (input2.getText() != null)){

            //read from input1 and input 2
            double num1 = Double.valueOf(input1.getText().toString());
            double num2 = Double.valueOf(input2.getText().toString());

            Log.v("Debug", "num 1 " + num1 + "num 2 " + num2);
            double result = 0;

            //perform operation on the two values

            switch(op){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            //update the value of text in the result textView
            res.setText(Double.toString(result));
        }
    }
}