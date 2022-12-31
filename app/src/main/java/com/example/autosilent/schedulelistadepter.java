package com.example.autosilent;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static android.content.Context.ALARM_SERVICE;

public class schedulelistadepter extends BaseAdapter {

    static int size=0;
    static List<schedulemodel> modellist;
    Context context;
    int[] days = new int[7];
    int[] alarms = new int[50];
    int cn=0;
    public schedulelistadepter(ArrayList<schedulemodel> modellist, Context context) {
        this.modellist = modellist;
        this.context = context;
    }
    Dialog deletedialog;

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.schedulelayout,parent,false);
        TextView scstart,scend,scname,d1,d2,d3,d4,d5,d6,d7,onday;
        ImageView scimg;
        Switch sw1;
        FloatingActionButton editbtn,deletebtn;


        scstart = convertView.findViewById(R.id.staringtime);
        scend = convertView.findViewById(R.id.endingtime);
        scimg = convertView.findViewById(R.id.imageviewmain);
        scname = convertView.findViewById(R.id.setname);
        d1 = convertView.findViewById(R.id.day1);
        d2 = convertView.findViewById(R.id.day2);
        d3 = convertView.findViewById(R.id.day3);
        d4 = convertView.findViewById(R.id.day4);
        d5 = convertView.findViewById(R.id.day5);
        d6 = convertView.findViewById(R.id.day6);
        d7 = convertView.findViewById(R.id.day7);
        onday = convertView.findViewById(R.id.onday);
        LinearLayout alldays = convertView.findViewById(R.id.alldays);
        sw1 = convertView.findViewById(R.id.setswitch);
        editbtn = convertView.findViewById(R.id.editschedule);
        deletebtn = convertView.findViewById(R.id.deleteschedule);



        scname.setText(modellist.get(position).getSchedname());
        scstart.setText("Starting Time : "+modellist.get(position).getSchedstart());
        scend.setText("Ending   Time : "+modellist.get(position).getSchedend());
        sw1.setTag(position);

        if(modellist.get(position).getSchedimg().equals("vibrate"))
            scimg.setImageResource(R.drawable.vibrate);
        else 
            if(modellist.get(position).getSchedimg().equals("silent"))
                scimg.setImageResource(R.drawable.silentt);


        String ststart = modellist.get(position).getSchedstart();
        String stend = modellist.get(position).getSchedend();

        String[] sttime = ststart.split(":");
        String[] endtime = stend.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sttime[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(sttime[1]));
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endtime[0]));
        calendar2.set(Calendar.MINUTE, Integer.parseInt(endtime[1]));
        calendar2.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);

        if(modellist.get(position).getD1().equals("empty") && modellist.get(position).getD2().equals("empty") && modellist.get(position).getD3().equals("empty") && modellist.get(position).getD4().equals("empty") && modellist.get(position).getD5().equals("empty") && modellist.get(position).getD6().equals("empty") && modellist.get(position).getD7().equals("empty")) {
            alldays.setVisibility(View.GONE);
            onday.setVisibility(View.VISIBLE);

                Date currentch = new Date();
                Date startch = calendar.getTime();
                Date endch = calendar2.getTime();

                    if (startch.before(currentch) && endch.after(currentch))
                        onday.setText("Today,");
                    else if (startch.before(currentch) && endch.before(currentch)) {
                        onday.setText("Tomorrow,");
                    } else if (startch.after(currentch) && endch.before(currentch))
                        onday.setText("Today,");
                    else
                        onday.setText("Today,");

        }
        else
        {
            alldays.setVisibility(View.VISIBLE);
            onday.setVisibility(View.GONE);

            if(modellist.get(position).getD1().equals("fill"))
                d1.setTextColor(Color.parseColor("#0076FF"));
            else
                d1.setTextColor(Color.parseColor("#909090"));

            if(modellist.get(position).getD2().equals("fill"))
                d2.setTextColor(Color.parseColor("#0076FF"));
            else
                d2.setTextColor(Color.parseColor("#909090"));

            if(modellist.get(position).getD3().equals("fill"))
                d3.setTextColor(Color.parseColor("#0076FF"));
            else
                d3.setTextColor(Color.parseColor("#909090"));

            if(modellist.get(position).getD4().equals("fill"))
                d4.setTextColor(Color.parseColor("#0076FF"));
            else
                d4.setTextColor(Color.parseColor("#909090"));

            if(modellist.get(position).getD5().equals("fill"))
                d5.setTextColor(Color.parseColor("#0076FF"));
            else
                d5.setTextColor(Color.parseColor("#909090"));

            if(modellist.get(position).getD6().equals("fill"))
                d6.setTextColor(Color.parseColor("#0076FF"));
            else
                d6.setTextColor(Color.parseColor("#909090"));

            if(modellist.get(position).getD7().equals("fill"))
                d7.setTextColor(Color.parseColor("#0076FF"));
            else
                d7.setTextColor(Color.parseColor("#909090"));
        }

        sw1.setChecked(modellist.get(position).getMyswitch());




        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {



                    modellist.get(position).setMyswitch(true);
                    if(modellist.get(position).getD1().equals("empty") && modellist.get(position).getD2().equals("empty") && modellist.get(position).getD3().equals("empty") && modellist.get(position).getD4().equals("empty") && modellist.get(position).getD5().equals("empty") && modellist.get(position).getD6().equals("empty") && modellist.get(position).getD7().equals("empty"))
                    {
                        checktime(calendar,calendar2,position,100+position,200+position);
                    }
                    else
                    {

                        for(int i = 0; i < days.length; i++) {
                            days[i] = 0;
                        }
                        int i=0;

                        if(modellist.get(position).getD7().equals("fill")) {
                            days[i] = 1;
                            i++;
                        }
                        if(modellist.get(position).getD1().equals("fill")) {
                            days[i] = 2;
                            i++;
                        }
                        if(modellist.get(position).getD2().equals("fill")) {
                            days[i] = 3;
                            i++;
                        }
                        if(modellist.get(position).getD3().equals("fill")) {
                            days[i] = 4;
                            i++;
                        }
                        if(modellist.get(position).getD4().equals("fill")) {
                            days[i] = 5;
                            i++;
                        }
                        if(modellist.get(position).getD5().equals("fill")) {
                            days[i] = 6;
                            i++;
                        }
                        if(modellist.get(position).getD6().equals("fill")) {
                            days[i] = 7;
                            i++;
                        }

                        for(i=0;i<7;i++)
                        {
                            if(days[i]!=0)
                            {
                                calendar.set(Calendar.DAY_OF_WEEK,days[i]);
                                calendar2.set(Calendar.DAY_OF_WEEK,days[i]);
                                Date current = new Date();
                                Date start = calendar.getTime();
                                Date end = calendar2.getTime();




                                if(start.before(current) && end.before(current))
                                {

                                }
                                else
                                {
                                    if(current.getDay()==start.getDay())
                                    {
                                        if(start.before(current) && end.after(current))
                                        {
                                            if(position==0)
                                            {
                                                Calendar c1 = Calendar.getInstance();
                                                setrepetalarm(c1, modellist.get(position).getSchedimg(), (30+position)*300+i);
                                                setrepetalarm(calendar2, "ringer", (40+position)*400+i);
                                                alarms[cn]=((30+position)*300+i);cn++;
                                                Log.d("helor","Set Alarm at "+(30+position)*300+i+" : "+String.valueOf(c1.getTime()));
                                                Log.d("helor","Set Alarm at "+(40+position)*400+i+" : "+String.valueOf(calendar2.getTime()));
                                            }
                                            else
                                            {
                                                Calendar c1 = Calendar.getInstance();
                                                setrepetalarm(c1, modellist.get(position).getSchedimg(), (position)*300+i);
                                                setrepetalarm(calendar2, "ringer", (position)*400+i);
                                                alarms[cn]=(position)*300+i;cn++;
                                                Log.d("helor","Set Alarm at "+(position)*300+i+" : "+String.valueOf(c1.getTime()));
                                                Log.d("helor","Set Alarm at "+(position)*400+i+" : "+String.valueOf(calendar2.getTime()));
                                            }
                                        }
                                        else
                                        {
                                            if(position==0)
                                            {
                                                setrepetalarm(calendar, modellist.get(position).getSchedimg(), (30+position)*300+i);
                                                setrepetalarm(calendar2, "ringer", (40+position)*400+i);
                                                alarms[cn]=(30+position)*300+i;cn++;
                                                Log.d("helor","Set Alarm at "+(30+position)*300+i+" : "+String.valueOf(calendar.getTime()));
                                                Log.d("helor","Set Alarm at "+(40+position)*400+i+" : "+String.valueOf(calendar2.getTime()));
                                            }
                                            else
                                            {
                                                setrepetalarm(calendar, modellist.get(position).getSchedimg(), (position)*300+i);
                                                setrepetalarm(calendar2, "ringer", (position)*400+i);
                                                alarms[cn]=(position)*300+i;cn++;
                                                Log.d("helor","Set Alarm at "+(position)*300+i+" : "+String.valueOf(calendar.getTime()));
                                                Log.d("helor","Set Alarm at "+(position)*400+i+" : "+String.valueOf(calendar2.getTime()));
                                            }
                                        }
                                    }
                                    else
                                    {
                                            if(position==0)
                                            {
                                                setrepetalarm(calendar, modellist.get(position).getSchedimg(), (30+position)*300+i);
                                                setrepetalarm(calendar2, "ringer", (40+position)*400+i);
                                                alarms[cn]=(30+position)*300+i;cn++;
                                                Log.d("helor","Set Alarm at "+(30+position)*300+i+" : "+String.valueOf(calendar.getTime()));
                                                Log.d("helor","Set Alarm at "+(40+position)*400+i+" : "+String.valueOf(calendar2.getTime()));
                                            }
                                            else
                                            {
                                                setrepetalarm(calendar, modellist.get(position).getSchedimg(), (position)*300+i);
                                                setrepetalarm(calendar2, "ringer", (position)*400+i);
                                                alarms[cn]=(position)*300+i;cn++;
                                                Log.d("helor","Set Alarm at "+(position)*300+i+" : "+String.valueOf(calendar.getTime()));
                                                Log.d("helor","Set Alarm at "+(position)*400+i+" : "+String.valueOf(calendar2.getTime()));
                                            }

                                    }
                                }
                            }
                        }
                     //   Toast.makeText(context, Arrays.toString(days), Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    if(modellist.get(position).getD1().equals("empty") && modellist.get(position).getD2().equals("empty") && modellist.get(position).getD3().equals("empty") && modellist.get(position).getD4().equals("empty") && modellist.get(position).getD5().equals("empty") && modellist.get(position).getD6().equals("empty") && modellist.get(position).getD7().equals("empty")) {
                        modellist.get(position).setMyswitch(false);
                        canclealarm(modellist.get(position).getSchedimg(), 100+position);
                        Log.d("helor", "delete Alarm at " + position + ".");
                        canclealarm("ringer", 200+position);
                    }
                    else
                    {
                        for(int i=0;i<7;i++)
                        {
                            if(days[i] != 0) {
                                if (position == 0)
                                {
                                    modellist.get(position).setMyswitch(false);
                                    canclerepetalarm(modellist.get(position).getSchedimg(), ((30+position)*300+i));
                                    Log.d("helor", "delete Alarm at " +((30+position)*300+i)+i+ ".");
                                    canclerepetalarm("ringer",((40+position)*300+i));
                                    Log.d("helor", "delete Alarm at " +((40+position)*300+i)+ ".");
                                }
                                else
                                {
                                    modellist.get(position).setMyswitch(false);
                                    canclerepetalarm(modellist.get(position).getSchedimg(),((position)*300+i));
                                    Log.d("helor", "delete Alarm at " +((position)*300+i)+i+ ".");
                                    canclerepetalarm("ringer",((position)*300+i));
                                    Log.d("helor", "delete Alarm at " +((position)*300+i)+ ".");
                                }
                            }
                        }
                    }
                }
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editbtn.getVisibility() == View.VISIBLE)
                {
                    editbtn.setVisibility(View.GONE);
                    deletebtn.setVisibility(View.GONE);
                }
                else {

                    editbtn.setVisibility(View.VISIBLE);
                    deletebtn.setVisibility(View.VISIBLE);
                }

            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edititem(position);
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteitem(position);
            }
        });


        return convertView;
    }

    private void checktime(Calendar calendar,Calendar calendar2,int position,int stid,int endid)
    {
        Date current = new Date();
        Date start = calendar.getTime();
        Calendar cal12 = Calendar.getInstance();
        cal12.set(Calendar.HOUR_OF_DAY,12);
        cal12.set(Calendar.MINUTE,00);
        cal12.set(Calendar.AM_PM,1);
        Date end = calendar2.getTime();



        if( (start.before(current) && end.after(current)))
        {
            Calendar c1 = Calendar.getInstance();
            setalarm(c1, modellist.get(position).getSchedimg(), stid,position);
            setalarm(calendar2, "ringer", endid,position);
            alarms[cn]=stid;cn++;

            Log.d("helor","Set Alarm at "+(stid)+" : "+String.valueOf(c1.getTime()));
            Log.d("helor","Set Alarm at "+(endid)+" : "+String.valueOf(calendar2.getTime()));
        }
        else {
            if(start.before(current) && end.before(current))
            {
                calendar.add(Calendar.DAY_OF_YEAR,1);
                calendar2.add(Calendar.DAY_OF_YEAR,1);
                setalarm(calendar, modellist.get(position).getSchedimg(), stid,position);
                alarms[cn]=stid;cn++;

                Log.d("helor","Set Alarm at "+(stid)+" : "+String.valueOf(calendar.getTime()));
                setalarm(calendar2, "ringer", endid,position);
                Log.d("helor","Set Alarm at "+(endid)+" : "+String.valueOf(calendar2.getTime()));
            }
            else {
                if(start.after(current) && end.before(current))
                {
                    calendar2.add(Calendar.DAY_OF_YEAR,1);
                    setalarm(calendar, modellist.get(position).getSchedimg(), stid,position);;
                    alarms[cn]=stid;cn++;
                    Log.d("helor","Set Alarm at "+(stid)+" : "+String.valueOf(calendar.getTime()));
                    setalarm(calendar2, "ringer", endid,position);
                    Log.d("helor","Set Alarm at "+(endid)+" : "+String.valueOf(calendar2.getTime()));
                }
                else
                {
                    setalarm(calendar, modellist.get(position).getSchedimg(), stid,position);
                    alarms[cn]=stid;cn++;
                    Log.d("helor","Set Alarm at "+(stid)+" : "+String.valueOf(calendar.getTime()));
                    setalarm(calendar2, "ringer", endid,position);
                    Log.d("helor","Set Alarm at "+(endid)+" : "+String.valueOf(calendar2.getTime()));

                }
            }
        }



    }

    public void edititem(int position)
    {
        String editstr = modellist.get(position).getSchedname() + ","+modellist.get(position).getSchedstart() + ","+modellist.get(position).getSchedend() + ","+modellist.get(position).getSchedimg() + ","+modellist.get(position).getD1() + ","+modellist.get(position).getD2() + ","+modellist.get(position).getD3() + ","+modellist.get(position).getD4() + ","+modellist.get(position).getD5() + ","+modellist.get(position).getD6() + ","+modellist.get(position).getD7() + ",";

        Intent it = new Intent(context,newedit.class);
        it.putExtra("sttime",modellist.get(position).getSchedstart());
        it.putExtra("endtime",modellist.get(position).getSchedend());
        it.putExtra("scname",modellist.get(position).getSchedname());
        it.putExtra("scmode",modellist.get(position).getSchedimg());
        it.putExtra("d1",modellist.get(position).getD1());
        it.putExtra("d2",modellist.get(position).getD2());
        it.putExtra("d3",modellist.get(position).getD3());
        it.putExtra("d4",modellist.get(position).getD4());
        it.putExtra("d5",modellist.get(position).getD5());
        it.putExtra("d6",modellist.get(position).getD6());
        it.putExtra("d7",modellist.get(position).getD7());
        it.putExtra("pos",String.valueOf(position));
        it.putExtra("editstr",editstr);


       // Toast.makeText(context, Arrays.toString(days), Toast.LENGTH_SHORT).show();
        if(modellist.get(position).getD1().equals("empty") && modellist.get(position).getD2().equals("empty") && modellist.get(position).getD3().equals("empty") && modellist.get(position).getD4().equals("empty") && modellist.get(position).getD5().equals("empty") && modellist.get(position).getD6().equals("empty") && modellist.get(position).getD7().equals("empty")) {
            modellist.get(position).setMyswitch(false);
            canclealarm(modellist.get(position).getSchedimg(),100+position);
            Log.d("helor", "delete Alarm at " + 100+position + ".");
            canclealarm("ringer", 200+position);
            Log.d("helor", "delete Alarm at " + 200+position + ".");
        }
        else
        {
            for(int i=0;i<7;i++)
            {
                if(days[i] != 0)
                {
                    if (position == 0)
                    {
                        modellist.get(position).setMyswitch(false);
                        canclerepetalarm(modellist.get(position).getSchedimg(), ((30+position)*300+i));
                        Log.d("helor", "delete Alarm at " +((30+position)*300+i)+i+ ".");
                        canclerepetalarm("ringer",((40+position)*300+i));
                        Log.d("helor", "delete Alarm at " +((40+position)*300+i)+ ".");
                    }
                    else
                    {
                        modellist.get(position).setMyswitch(false);
                        canclerepetalarm(modellist.get(position).getSchedimg(),((position)*300+i));
                        Log.d("helor", "delete Alarm at " +((position)*300+i)+i+ ".");
                        canclerepetalarm("ringer",((position)*300+i));
                        Log.d("helor", "delete Alarm at " +((position)*300+i)+ ".");
                    }
                }
            }
        }

        context.startActivity(it);

    }


    public  void deleteitem(int position)
    {

        TextView cancle,ok;
        deletedialog = new Dialog(context);
        deletedialog.setContentView(R.layout.dialogremove);
        deletedialog.show();
        deletedialog.setCancelable(false);

        cancle = deletedialog.findViewById(R.id.cancletxt);
        ok = deletedialog.findViewById(R.id.oktxt);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletedialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(context, Arrays.toString(days), Toast.LENGTH_SHORT).show();
                if(modellist.get(position).getD1().equals("empty") && modellist.get(position).getD2().equals("empty") && modellist.get(position).getD3().equals("empty") && modellist.get(position).getD4().equals("empty") && modellist.get(position).getD5().equals("empty") && modellist.get(position).getD6().equals("empty") && modellist.get(position).getD7().equals("empty")) {
                    modellist.get(position).setMyswitch(false);
                    canclealarm(modellist.get(position).getSchedimg(),100+position);
                    Log.d("helor", "delete Alarm at " + 100+position + ".");
                    canclealarm("ringer", 200+position);
                    Log.d("helor", "delete Alarm at " + 200+position + ".");
                }
                else
                {
                    for(int i=0;i<7;i++)
                    {
                        if(days[i] != 0)
                        {
                            if (position == 0)
                            {
                                modellist.get(position).setMyswitch(false);
                                canclerepetalarm(modellist.get(position).getSchedimg(), ((30+position)*300+i));
                                Log.d("helor", "delete Alarm at " +((30+position)*300+i)+i+ ".");
                                canclerepetalarm("ringer",((40+position)*300+i));
                                Log.d("helor", "delete Alarm at " +((40+position)*300+i)+ ".");
                            }
                            else
                            {
                                modellist.get(position).setMyswitch(false);
                                canclerepetalarm(modellist.get(position).getSchedimg(),((position)*300+i));
                                Log.d("helor", "delete Alarm at " +((position)*300+i)+i+ ".");
                                canclerepetalarm("ringer",((position)*300+i));
                                Log.d("helor", "delete Alarm at " +((position)*300+i)+ ".");
                            }
                        }
                    }
                }

                modellist.remove(position);
                if(modellist.size()==0)
                {
                    context.deleteFile("silentlist.txt");

                    SharedPreferences settings = context.getSharedPreferences("switch", Context.MODE_PRIVATE);
                    settings.edit().clear().commit();

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    context.startActivity(intent);
                }
                else {

                    FileOutputStream fileOutputStream = null;
                    try {
                        /*Toast.makeText(context, "size : " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "size : " + String.valueOf(modellist.size()), Toast.LENGTH_SHORT).show();
*/
                        fileOutputStream = context.openFileOutput("silentlist.txt", Context.MODE_PRIVATE); //MODE PRIVATE

                        for (int i = 0; i <= modellist.size(); i++) {

                            fileOutputStream.write((modellist.get(i).getSchedname() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getSchedstart() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getSchedend() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getSchedimg() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD1() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD2() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD3() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD4() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD5() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD6() + ",").getBytes());
                            fileOutputStream.write((modellist.get(i).getD7() + ",").getBytes());
                            fileOutputStream.write("\n".getBytes());
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            context.startActivity(intent);


                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void canclealarm(String mode,int pos)
    {


        if(mode.equals("silent")) {
            Intent it = new Intent(context, silentReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,pos, it, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
        else
        {
            if(mode.equals("vibrate")) {
                Intent it = new Intent(context, vibrateReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it, 0);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
            else
            {
                if(mode.equals("ringer")) {
                    Intent it = new Intent(context, ringerReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,pos, it, 0);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                }
            }
        }
    }



    public void canclerepetalarm(String mode,int pos) {
        if (mode.equals("silent")) {
            Intent it = new Intent(context, silentReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        } else {
            if (mode.equals("vibrate")) {
                Intent it = new Intent(context, vibrateReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it, 0);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            } else {
                if (mode.equals("ringer")) {
                    Intent it = new Intent(context, ringerReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it, 0);
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                }
            }
        }

    }



    public void setalarm(Calendar time,String mode,int pos,int loc)
    {
        if(mode.equals("silent"))
        {
            Intent it1 = new Intent(context,silentReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it1, 0);
            AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(), pendingIntent);
            /*Toast.makeText(context, String.valueOf(pos), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Alarm set in " + time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE) + " seconds", Toast.LENGTH_LONG).show();*/
        }
        else
        {
            if(mode.equals("vibrate"))
            {
                Intent it1 = new Intent(context,vibrateReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it1, 0);
                AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(), pendingIntent);
                /*Toast.makeText(context, String.valueOf(pos), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Alarm set in " + time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE) + " seconds", Toast.LENGTH_LONG).show();
*/
            }
            else
            {
                if(mode.equals("ringer"))
                {
                    Intent it1 = new Intent(context,ringerReceiver.class);
                    it1.putExtra("loc",String.valueOf(loc));
                    it1.putExtra("repeat",true);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it1, 0);
                    AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(), pendingIntent);
                    /*Toast.makeText(context, String.valueOf(pos), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Alarm set in " + time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE) + " seconds", Toast.LENGTH_LONG).show();
*/
                }
            }
        }
    }


    public void setrepetalarm(Calendar time,String mode,int pos)
    {


        if(mode.equals("silent"))
        {
            Intent it1 = new Intent(context,silentReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it1, 0);
            AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);
            /*Toast.makeText(context, String.valueOf(pos), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Alarm set in " + time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE) + " seconds", Toast.LENGTH_LONG).show();
*/
        }
        else
        {
            if(mode.equals("vibrate"))
            {
                Intent it1 = new Intent(context,vibrateReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it1, 0);
                AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);
                /*Toast.makeText(context, String.valueOf(pos), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Alarm set in " + time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE) + " seconds", Toast.LENGTH_LONG).show();
*/          }
            else
            {
                if(mode.equals("ringer"))
                {
                    Intent it1 = new Intent(context,ringerReceiver.class);
                    it1.putExtra("repeat",false);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pos, it1, 0);
                    AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(),alarmManager.INTERVAL_DAY*7, pendingIntent);
                   /* Toast.makeText(context, String.valueOf(pos), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Alarm set in " + time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE) + " seconds", Toast.LENGTH_LONG).show();
*/
                }
            }
        }
    }


}
