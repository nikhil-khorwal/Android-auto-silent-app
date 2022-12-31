package com.example.autosilent;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

public class silentReceiver extends BroadcastReceiver {

    AudioManager myAudioManager;
    @SuppressLint("WrongConstant")
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("helor","get the sient");

        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }
}