package com.example.autosilent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

public class ringerReceiver extends BroadcastReceiver {

    AudioManager myAudioManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("helor","get the ringer");
        Boolean chk = intent.getBooleanExtra("repeat",false);
        if(chk)
        {
            schedulelistadepter.modellist.get(Integer.parseInt(intent.getStringExtra("loc"))).setMyswitch(false);
            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(intent2);
        }

        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
}