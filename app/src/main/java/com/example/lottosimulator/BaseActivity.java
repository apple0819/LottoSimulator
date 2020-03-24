package com.example.lottosimulator;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    Context mContxt = this;

    public abstract void setupEvents();
    public abstract void setValues();

}
