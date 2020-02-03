package com.androidtutz.anushka.didemo;

import dagger.Binds;
import dagger.Module;

@Module
abstract class NCBatteryModule {

    @Binds
    abstract Battery bindNCBattery(NickelCadmiumBattery nickelCadmiumBattery);
}

/* YOU CAN ALSO DO:
public class NCBatteryModule {

    @Provides
    Battery provideNCBattery(NickelCadmiumBattery nickelCadmiumBattery){
        nickelCadmiumBattery.showType();
        return nickelCadmiumBattery;
    }
}*/