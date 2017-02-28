package com.example.android.task1;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fab;
    private TextView textView;
    private Button clear, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnc;
    private EditText etincome, etoutcome;
    private TextInputLayout til_income, til_outcome;
    private Vibrator vibrator;
    Animation animShake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_val);
        etincome = (EditText) findViewById(R.id.et_income);
        etoutcome = (EditText) findViewById(R.id.et_outcome);
        til_income = (TextInputLayout) findViewById(R.id.til_income);
        til_outcome = (TextInputLayout) findViewById(R.id.til_outcome);

        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btnc = (Button) findViewById(R.id.btn_c);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        clear = (Button) findViewById(R.id.clear);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnc.setOnClickListener(this);
        fab.setOnClickListener(this);
        clear.setOnClickListener(this);

        animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        validate();
    }

    private boolean validate() {
        etincome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateIncome();
            }
        });

        etoutcome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateOutcome();
            }
        });
        return true;
    }

    private boolean validateIncome() {
        if (etincome.getText().toString().trim().isEmpty()) {
            til_income.setError("Enter your income");
            etincome.setAnimation(animShake);
            etincome.startAnimation(animShake);
            vibrator.vibrate(120);
            requestFocus(etincome);
            return false;
        } else {
            til_income.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateOutcome() {
        if (etoutcome.getText().toString().trim().isEmpty()) {
            til_outcome.setError("Enter your outcome");
            etoutcome.setAnimation(animShake);
            etoutcome.startAnimation(animShake);
            vibrator.vibrate(120);
            requestFocus(etoutcome);
            return false;
        } else {
            til_outcome.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                inputButton(0);
                if (etincome.getText().toString().startsWith("0")) {
                    etincome.setText(etincome.getText().toString().replace("0", ""));
                    Toast.makeText(this, "Enter a number except zero in the first digit!", Toast.LENGTH_SHORT).show();
                } else if (etoutcome.getText().toString().startsWith("0")) {
                    etoutcome.setText(etoutcome.getText().toString().replace("0", ""));
                    Toast.makeText(this, "Enter a number except zero in the first digit!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_1:
                inputButton(1);
                break;
            case R.id.btn_2:
                inputButton(2);
                break;
            case R.id.btn_3:
                inputButton(3);
                break;
            case R.id.btn_4:
                inputButton(4);
                break;
            case R.id.btn_5:
                inputButton(5);
                break;
            case R.id.btn_6:
                inputButton(6);
                break;
            case R.id.btn_7:
                inputButton(7);
                break;
            case R.id.btn_8:
                inputButton(8);
                break;
            case R.id.btn_9:
                inputButton(9);
                break;
            case R.id.fab:
                String income = etincome.getText().toString();
                String outcome = etoutcome.getText().toString();
                if (!validateIncome())
                    return;
                else if (!validateOutcome())
                    return;
                else {
                    int val = Integer.parseInt(income) - Integer.parseInt(outcome);
                    textView.setText("" + val);
                }
                break;
            case R.id.clear:
                etincome.setText("");
                etoutcome.setText("");
                textView.setText("Val");
                break;
            case R.id.btn_c:
                int length_in = etincome.getText().length();
                int length_ex = etoutcome.getText().length();
                if (etincome.hasFocus()) {
                    if (length_in > 0) {
                        etincome.getText().delete(length_in - 1, length_in);
                    }
                } else if (etoutcome.hasFocus()) {
                    if (length_ex > 0) {
                        etoutcome.getText().delete(length_ex - 1, length_ex);
                    }
                } else
                    Toast.makeText(this, "Click EditText!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public int inputButton(int i) {
        if (etincome.hasFocus())
            etincome.setText(etincome.getText() + "" + i);
        else if (etoutcome.hasFocus())
            etoutcome.setText(etoutcome.getText() + "" + i);
        else
            Toast.makeText(this, "Click EditText!", Toast.LENGTH_SHORT).show();
        return i;
    }
}
