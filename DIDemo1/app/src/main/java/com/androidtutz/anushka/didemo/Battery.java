package com.androidtutz.anushka.didemo;

//assume Battery is an interface so we have to show Dagger how to get object for an interface using NCBatteryModule
public interface Battery {

    public void showType();
}
