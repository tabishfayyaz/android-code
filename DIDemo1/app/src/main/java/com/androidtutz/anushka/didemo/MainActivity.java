package com.androidtutz.anushka.didemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

//    SmartPhone smartPhone;

    @Inject
    SmartPhone smartPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.create();
//        smartPhone = smartPhoneComponent.getSmartPhone();


        //providing state to a module
        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.builder()
                .memoryCardModule(new MemoryCardModule(100))
                .build();
        smartPhoneComponent.inject(this);

        smartPhone.makeACall();
    }
}
