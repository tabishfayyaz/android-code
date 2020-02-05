package com.androidtutz.anushka.didemo;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SmartPhone {

    private Battery battery;
    private MemoryCard memoryCard;
    private SIMCard simCard;
    private static final String TAG = "SmartPhone";

    @Inject
    public SmartPhone(Battery battery, MemoryCard memoryCard, SIMCard simCard) {
        this.battery = battery;
        this.memoryCard = memoryCard;
        this.simCard = simCard;

        battery.showType();
    }


    public void makeACall() {
        Log.d(TAG, " making a call......... ");
    }
}
