package com.example.autosilent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class vibrateReceiver extends BroadcastReceiver {

    AudioManager myAudioManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("helor","get the vibrate");
        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }
}