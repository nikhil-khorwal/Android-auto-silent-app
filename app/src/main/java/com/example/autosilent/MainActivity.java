package com.example.autosilent;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final int ON_DO_NOT_DISTURB_CALLBACK_CODE = 1;

    Toolbar toolbar;
    FloatingActionButton addsched;
    ListView schedulelistView;
    List<schedulemodel> schedulemodels;
    schedulelistadepter schedulelistadepter;
    ImageView emptystate;
    Dialog permissiondialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestMutePhonePermsAndMutePhone();

        emptystate = findViewById(R.id.emptystate);
        addsched = findViewById(R.id.addschdule);
        schedulelistView = findViewById(R.id.schedulelist);
        toolbar = findViewById(R.id.toolbar);


        readinfile();

        if(schedulemodels.size() == 0)
        {
            SharedPreferences settings = getSharedPreferences("switch", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            emptystate.setVisibility(View.VISIBLE);
        }
        else
        {
            emptystate.setVisibility(View.GONE);
            schedulelistadepter = new schedulelistadepter((ArrayList<schedulemodel>) schedulemodels,this);
            schedulelistView.setAdapter(schedulelistadepter);
        }

        addsched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),newedit.class);
                startActivity(it);
            }
        });

    }











    @RequiresApi(api = Build.VERSION_CODES.O)
    public void readinfile()
    {
        schedulemodels = new ArrayList<>();
        try {

            FileInputStream fis = openFileInput("silentlist.txt");
            InputStreamReader streamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String msg="";
            int i=0;
            while ((msg = bufferedReader.readLine()) != null )
            {
                String[] arr = new String[20];
                arr = msg.split(",");
                schedulemodels.add(new schedulemodel(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9],arr[10]));
                i++;
                Arrays.toString(arr);
            }
            //Log.d("silentlog","\n\nFile data : \n"+msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("switch",Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i=0;i<schedulemodels.size();i++)
        {
            editor.putBoolean(String.valueOf(i),schedulemodels.get(i).getMyswitch());
        }
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("switch",Context.MODE_PRIVATE);
        for(int i=0;i<schedulemodels.size();i++)
        {
            Log.d("helor",i +" : "+String.valueOf(sharedPreferences.getBoolean(String.valueOf(i),false)));
            schedulemodels.get(i).setMyswitch(sharedPreferences.getBoolean(String.valueOf(i),false));
        }
    }

    private void requestMutePhonePermsAndMutePhone() {
        try {
            if (Build.VERSION.SDK_INT < 23) {

            } else if( Build.VERSION.SDK_INT >= 23 ) {
                this.requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp();
            }
        } catch ( SecurityException e ) {

        }
    }
    private void requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp() {
        //TO SUPPRESS API ERROR MESSAGES IN THIS FUNCTION, since Ive no time to figrure our Android SDK suppress stuff
        if( Build.VERSION.SDK_INT < 23 ) {
            return;
        }

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        if ( notificationManager.isNotificationPolicyAccessGranted())
        {

        }
        else
        {
            TextView cancle,ok;
            permissiondialog = new Dialog(this);
            permissiondialog.setContentView(R.layout.dialogpermission);
            permissiondialog.show();
            permissiondialog.setCancelable(false);

            cancle = permissiondialog.findViewById(R.id.concancle);
            ok = permissiondialog.findViewById(R.id.conok);

            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    permissiondialog.dismiss();
                    finishAffinity();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    startActivityForResult( intent, MainActivity.ON_DO_NOT_DISTURB_CALLBACK_CODE );
                    permissiondialog.dismiss();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.ON_DO_NOT_DISTURB_CALLBACK_CODE) {
            this.requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp();
        }
    }

}