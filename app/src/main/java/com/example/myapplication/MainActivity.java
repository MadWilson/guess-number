package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Integer guess;
    Boolean gameFinished;

    TextView tvInfo;
    EditText etInput;
    Button bControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo=(TextView)findViewById(R.id.textView);
        etInput=(EditText)findViewById(R.id.editTextNumber);
        bControl=(Button)findViewById(R.id.button);

        guess=(int)(Math.random()*100);
        gameFinished=false;

    }
    public void onClick(View v){

        if (! gameFinished) {
            //проверяем, введено ли что либо в поле ввода, если нет то вводим сами и информируем
            if (etInput.getText().toString().isEmpty()) {
                Toast toast=Toast.makeText(this,R.string.empty_input, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                etInput.setText(R.string.destiny);
            }
            //получаем число из поля ввода
            int inp=Integer.parseInt(etInput.getText().toString());
            //проверяем, входит ли введенное число в заданный диапазон
            //если нет входит то вводим сами и информируем пользователя
            if (inp>100 || inp<1) {
                Toast toast=Toast.makeText(this,R.string.error, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                etInput.setText(R.string.destiny);
            }
            //сравниваем с загаданным
            if(inp>guess)
                tvInfo.setText(getResources().getText(R.string.ahead));
            if(inp<guess)
                tvInfo.setText(getResources().getText(R.string.behind));
            if (inp==guess) {
                tvInfo.setText(getResources().getText(R.string.hit));
                bControl.setText(getResources().getText(R.string.play_more));
                gameFinished=true;
            }
        }
        // если угадали число то загадываем новое и опять начинаем игру
        else
        {
            guess=(int)(Math.random()*100);
            bControl.setText(getResources().getText(R.string.input_value));
            tvInfo.setText(getResources().getText(R.string.try_to_guess));
            gameFinished=false;
        }
        //очищаем поле воода
        etInput.setText("");
    }

}