package com.androidtutz.anushka.didemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SmartPhone smartPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.create();
        smartPhone = smartPhoneComponent.getSmartPhone();

        smartPhone.makeACall();
    }
}
