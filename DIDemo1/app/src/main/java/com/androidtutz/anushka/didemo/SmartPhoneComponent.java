package com.androidtutz.anushka.didemo;

import javax.inject.Singleton;

import dagger.Component;

//if at-least one dependency (in our case SmartPhone) associated with a component is a singleton then we have to mark it singleton here as well
@Singleton
@Component(modules = {MemoryCardModule.class, NCBatteryModule.class})
public interface SmartPhoneComponent {
//    SmartPhone getSmartPhone();   //when we wanted to access objects through getter as oppose to field injection

    void inject(MainActivity mainActivity);     //inject is just a convention, could be any name, now you can do field injection
}