package com.android.trovi.BackgroundCollect;

import android.bluetooth.BluetoothAdapter;

public class BluetoothCollect {
    public String checkBluetooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return "null";
        } else if (!mBluetoothAdapter.isEnabled()) {
            return "0";
        } else {
            return "1";
        }
    }
}
