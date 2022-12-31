package com.example.autosilent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class newedit extends AppCompatActivity {

    RelativeLayout namecard,startcard,endcard,modecard;
    Dialog namecarddialg,savecarddialg,modecarddialog;
    TextView dialigok,dialogcancle,dialogsave,dialogdiscard,dialogscancle,dialogmcancle,dialogmok;
    TextView namesched,startsched,endsched,modesched;
    TextView startadd,endadd,nameadd,modeadd;
    EditText dialognameed;
    TextView day1,day2,day3,day4,day5,day6,day7;
    RadioButton vibraterb,silentrb;
    int starthr,startmin,endhr,endmin;
    TextView tsave,tcancle;

    String schedsttime,schedendtime,schedname,schedmode="silent",pos,schedd1,schedd2,schedd3,schedd4,schedd5,schedd6,schedd7,editstr="";

    final Context context=this;
    String editornew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newedit);



        tsave = findViewById(R.id.savetoolbar);
        tcancle = findViewById(R.id.cancletoolbar);

        namecard = findViewById(R.id.namecard);
        startcard = findViewById(R.id.startcard);
        endcard = findViewById(R.id.endcard);
        modecard = findViewById(R.id.modecard);

        namesched = findViewById(R.id.namesched);
        startsched = findViewById(R.id.startsched);
        endsched = findViewById(R.id.endsched);
        modesched = findViewById(R.id.modesched);

        startadd = findViewById(R.id.startadd);
        endadd = findViewById(R.id.endadd);
        nameadd = findViewById(R.id.nameadd);
        modeadd = findViewById(R.id.modeadd);

        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);
        day6 = findViewById(R.id.day6);
        day7 = findViewById(R.id.day7);

        editset();
        adddefalut();
        daydefalt();


        tsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();
            }
        });

        tcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        startcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickstartcard();
            }
        });

        endcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickendcard();
            }
        });

        namecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicknamecard();
            }
        });

        clickrepeatcard();

        modecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickmodecard();
            }
        });

    }

    private void editset()
    {
        Intent it = getIntent();
        if(it.getStringExtra("scname")!=null) {
            namesched.setText(it.getStringExtra("scname"));
            startsched.setText(it.getStringExtra("sttime"));
            endsched.setText(it.getStringExtra("endtime"));
            modesched.setText(it.getStringExtra("scmode"));
            day1.setTag(it.getStringExtra("d1"));
            day2.setTag(it.getStringExtra("d2"));
            day3.setTag(it.getStringExtra("d3"));
            day4.setTag(it.getStringExtra("d4"));
            day5.setTag(it.getStringExtra("d5"));
            day6.setTag(it.getStringExtra("d6"));
            day7.setTag(it.getStringExtra("d7"));
            editstr = it.getStringExtra("editstr");
        }
    }

    private void adddefalut() {
        if(namesched.getText().toString().equals("None"))
            nameadd.setText("+ Add");
        else
            nameadd.setText("+ Edit");

        if(startsched.getText().toString().equals("None"))
            startadd.setText("+ Add");
        else
            startadd.setText("+ Edit");

        if(endsched.getText().toString().equals("None"))
            endadd.setText("+ Add");
        else
            endadd.setText("+ Edit");


    }


    private void daydefalt() {

        if(day1.getTag().equals("empty")) {
            day1.setBackgroundResource(R.drawable.dayround);
            day1.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day1.getTag().equals("fill")) {
                day1.setBackgroundResource(R.drawable.dayroundfill);
                day1.setTextColor(Color.WHITE);
            }
        }

        if(day2.getTag().equals("empty")) {
            day2.setBackgroundResource(R.drawable.dayround);
            day2.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day2.getTag().equals("fill")) {
                day2.setBackgroundResource(R.drawable.dayroundfill);
                day2.setTextColor(Color.WHITE);
            }
        }


        if(day3.getTag().equals("empty")) {
            day3.setBackgroundResource(R.drawable.dayround);
            day3.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day3.getTag().equals("fill")) {
                day3.setBackgroundResource(R.drawable.dayroundfill);
                day3.setTextColor(Color.WHITE);
            }
        }


        if(day4.getTag().equals("empty")) {
            day4.setBackgroundResource(R.drawable.dayround);
            day4.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day4.getTag().equals("fill")) {
                day4.setBackgroundResource(R.drawable.dayroundfill);
                day4.setTextColor(Color.WHITE);
            }
        }


        if(day5.getTag().equals("empty")) {
            day5.setBackgroundResource(R.drawable.dayround);
            day5.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day5.getTag().equals("fill")) {
                day5.setBackgroundResource(R.drawable.dayroundfill);
                day5.setTextColor(Color.WHITE);
            }
        }


        if(day6.getTag().equals("empty")) {
            day6.setBackgroundResource(R.drawable.dayround);
            day6.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day6.getTag().equals("fill")) {
                day6.setBackgroundResource(R.drawable.dayroundfill);
                day6.setTextColor(Color.WHITE);
            }
        }


        if(day7.getTag().equals("empty")) {
            day7.setBackgroundResource(R.drawable.dayround);
            day7.setTextColor(Color.parseColor("#0076FF"));
        }
        else {
            if (day7.getTag().equals("fill")) {
                day7.setBackgroundResource(R.drawable.dayroundfill);
                day7.setTextColor(Color.WHITE);
            }
        }
    }


    private void clickstartcard() {

        Calendar starttime = Calendar.getInstance();
        int currnthr,currntmin;

        if(startsched.getText().toString().equals("None")) {
            currnthr = starttime.get(Calendar.HOUR_OF_DAY);
            currntmin = starttime.get(Calendar.MINUTE);
        }
        else {
            String[] sttime = startsched.getText().toString().split(":");
            currnthr = Integer.parseInt(sttime[0]);
            currntmin = Integer.parseInt(sttime[1]);
        }

        TimePickerDialog starttimedialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hrs,mins;
                if(hourOfDay<10)
                    hrs = "0"+hourOfDay;
                else
                    hrs = String.valueOf(hourOfDay);

                if(minute<10)
                    mins = "0"+minute;
                else
                    mins = String.valueOf(minute);

                startsched.setText(hrs+":"+mins);
                startadd.setText("+ Edit");

                starthr = hourOfDay;
                startmin = minute;

                schedsttime = hrs+":"+mins;

            }
        },currnthr,currntmin,true);
        starttimedialog.show();

    }


    private void clickendcard() {

        Calendar endttime = Calendar.getInstance();

        int currnthr,currntmin;

        if(endsched.getText().toString().equals("None")) {
            currnthr = endttime.get(Calendar.HOUR_OF_DAY);
            currntmin = endttime.get(Calendar.MINUTE);
        }
        else {
            String[] endtime = endsched.getText().toString().split(":");
            currnthr = Integer.parseInt(endtime[0]);
            currntmin = Integer.parseInt(endtime[1]);
        }

        TimePickerDialog endtimedialog;
        endtimedialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hrs,mins;
                if(hourOfDay<10)
                    hrs = "0"+hourOfDay;
                else
                    hrs = String.valueOf(hourOfDay);

                if(minute<10)
                    mins = "0"+minute;
                else
                    mins = String.valueOf(minute);

                endsched.setText(hrs+":"+mins);
                endadd.setText("+ Edit");

                endhr = hourOfDay;
                endmin = minute;
                schedendtime = hrs+":"+mins;
            }
        },currnthr,currntmin,true);
        endtimedialog.show();

    }

    private void clicknamecard() {
        namecarddialg = new Dialog(context);
        namecarddialg.setContentView(R.layout.dialogname);
        namecarddialg.show();
        namecarddialg.setCancelable(false);

        Window window = namecarddialg.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        dialigok = namecarddialg.findViewById(R.id.oktxt);
        dialogcancle = namecarddialg.findViewById(R.id.cancletxt);
        dialognameed = namecarddialg.findViewById(R.id.nameedit);
        if(namesched.getText().toString().equals("None"))
        {
        }
        else
        {
            dialognameed.setText(namesched.getText().toString());
            dialognameed.setSelection(namesched.getText().toString().length());
        }
        dialigok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedname = dialognameed.getText().toString();
                if(schedname.isEmpty())
                {
                    namesched.setText("None");
                    nameadd.setText("+ Add");
                }
                else
                {
                    namesched.setText(schedname);
                    nameadd.setText("+ Edit");
                }
                namecarddialg.dismiss();
            }
        });

        dialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namecarddialg.dismiss();
            }
        });


    }

    private void clickmodecard() {

        modecarddialog = new Dialog(context);
        modecarddialog.setContentView(R.layout.dialogmode);
        modecarddialog.show();
        modecarddialog.setCancelable(false);

        Window window = modecarddialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        dialigok = modecarddialog.findViewById(R.id.oktxt);
        dialogcancle = modecarddialog.findViewById(R.id.cancletxt);
        vibraterb = modecarddialog.findViewById(R.id.vibraterb);
        silentrb = modecarddialog.findViewById(R.id.silentrb);

        if(modesched.getText().toString().equals("silent"))
        {
            vibraterb.setChecked(false);
            silentrb.setChecked(true);
        }
        if(modesched.getText().toString().equals("vibrate"))
        {
            silentrb.setChecked(false);
            vibraterb.setChecked(true);
        }

        dialigok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vibraterb.isChecked())
                {
                    schedmode = "vibrate";
                    modesched.setText(schedmode);
                    modeadd.setText("+ Edit");
                }
                else
                {
                    if(silentrb.isChecked())
                    {
                        schedmode = "silent";
                        modesched.setText(schedmode);
                        modeadd.setText("+ Edit");
                    }
                    else
                    {
                        schedmode="None";
                        modesched.setText("None");
                        modeadd.setText("+ Add");
                    }
                }
                modecarddialog.dismiss();
            }
        });

        dialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modecarddialog.dismiss();
            }
        });


    }

    private void clickrepeatcard() {

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day1.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day1.getTag().equals("empty")) {
                    day1.setBackgroundResource(R.drawable.dayroundfill);
                    day1.setTextColor(Color.WHITE);
                    day1.setTag("fill");
                }
                else {
                    if (day1.getTag().equals("fill")) {
                        day1.setBackgroundResource(R.drawable.dayround);
                        day1.setTextColor(Color.parseColor("#0076FF"));
                        day1.setTag("empty");
                    }
                }
            }
        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day2.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day2.getTag().equals("empty")) {
                    day2.setBackgroundResource(R.drawable.dayroundfill);
                    day2.setTextColor(Color.WHITE);
                    day2.setTag("fill");
                }
                else {
                    if (day2.getTag().equals("fill")) {
                        day2.setBackgroundResource(R.drawable.dayround);
                        day2.setTextColor(Color.parseColor("#0076FF"));
                        day2.setTag("empty");
                    }
                }
            }
        });


        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day3.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day3.getTag().equals("empty")) {
                    day3.setBackgroundResource(R.drawable.dayroundfill);
                    day3.setTextColor(Color.WHITE);
                    day3.setTag("fill");
                }
                else {
                    if (day3.getTag().equals("fill")) {
                        day3.setBackgroundResource(R.drawable.dayround);
                        day3.setTextColor(Color.parseColor("#0076FF"));
                        day3.setTag("empty");
                    }
                }
            }
        });


        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day4.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day4.getTag().equals("empty")) {
                    day4.setBackgroundResource(R.drawable.dayroundfill);
                    day4.setTextColor(Color.WHITE);
                    day4.setTag("fill");
                }
                else {
                    if (day4.getTag().equals("fill")) {
                        day4.setBackgroundResource(R.drawable.dayround);
                        day4.setTextColor(Color.parseColor("#0076FF"));
                        day4.setTag("empty");
                    }
                }
            }
        });


        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day5.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day5.getTag().equals("empty")) {
                    day5.setBackgroundResource(R.drawable.dayroundfill);
                    day5.setTextColor(Color.WHITE);
                    day5.setTag("fill");
                }
                else {
                    if (day5.getTag().equals("fill")) {
                        day5.setBackgroundResource(R.drawable.dayround);
                        day5.setTextColor(Color.parseColor("#0076FF"));
                        day5.setTag("empty");
                    }
                }
            }
        });


        day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day6.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day6.getTag().equals("empty")) {
                    day6.setBackgroundResource(R.drawable.dayroundfill);
                    day6.setTextColor(Color.WHITE);
                    day6.setTag("fill");
                }
                else {
                    if (day6.getTag().equals("fill")) {
                        day6.setBackgroundResource(R.drawable.dayround);
                        day6.setTextColor(Color.parseColor("#0076FF"));
                        day6.setTag("empty");
                    }
                }
            }
        });


        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, day7.getTag().toString(), Toast.LENGTH_SHORT).show();
                if(day7.getTag().equals("empty")) {
                    day7.setBackgroundResource(R.drawable.dayroundfill);
                    day7.setTextColor(Color.WHITE);
                    day7.setTag("fill");
                }
                else {
                    if (day7.getTag().equals("fill")) {
                        day7.setBackgroundResource(R.drawable.dayround);
                        day7.setTextColor(Color.parseColor("#0076FF"));
                        day7.setTag("empty");
                    }
                }
            }
        });

    }


    @Override
    public void onBackPressed() {

        if(startsched.getText().toString().equals("None") && endsched.getText().toString().equals("None") && namesched.getText().toString().equals("None") && day1.getTag().equals("empty") && day2.getTag().equals("empty") && day3.getTag().equals("empty") && day4.getTag().equals("empty") && day5.getTag().equals("empty") && day6.getTag().equals("empty") && day7.getTag().equals("empty"))
        {
            finish();
        }
        else {

            savecarddialg = new Dialog(context);
            savecarddialg.setContentView(R.layout.dialogsave);
            savecarddialg.show();
            savecarddialg.setCancelable(false);

            Window window = savecarddialg.getWindow();
            window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            dialogsave = savecarddialg.findViewById(R.id.savetxt);
            dialogdiscard = savecarddialg.findViewById(R.id.discardtxt);
            dialogscancle = savecarddialg.findViewById(R.id.cancletxt);

            dialogsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savecarddialg.dismiss();
                    senddata();
                }
            });

            dialogdiscard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            dialogscancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savecarddialg.dismiss();
                }
            });
        }
    }

    public void senddata()
    {
        //Log.d("silentlog","Send data : \n"+startsched.getText().toString()+endsched.getText().toString()+namesched.getText().toString()+schedmode+day1.getTag()+day2.getTag()+day3.getTag()+day4.getTag()+day5.getTag()+day6.getTag()+day7.getTag());
        if(startsched.getText().toString().equals("None"))
        {
            Toast.makeText(context, "Select Start Time", Toast.LENGTH_SHORT).show();
            startadd.requestFocus();
        }
        else {
            if (endsched.getText().toString().equals("None")) {
                Toast.makeText(context, "Select End Time", Toast.LENGTH_SHORT).show();
                endadd.requestFocus();
            } else {

                if (endsched.getText().toString().equals(startsched.getText().toString())) {
                    Toast.makeText(context, "Both time is same!!", Toast.LENGTH_SHORT).show();
                }
               else {
                    if (namesched.getText().toString().equals("None")) {
                        Toast.makeText(context, "Enter Some Name", Toast.LENGTH_SHORT).show();
                        nameadd.requestFocus();
                    } else {
                        schedname = namesched.getText().toString();
                        schedsttime = startsched.getText().toString();
                        schedendtime = endsched.getText().toString();
                        schedmode = modesched.getText().toString();
                        schedd1 = day1.getTag().toString();
                        schedd2 = day2.getTag().toString();
                        schedd3 = day3.getTag().toString();
                        schedd4 = day4.getTag().toString();
                        schedd5 = day5.getTag().toString();
                        schedd6 = day6.getTag().toString();
                        schedd7 = day7.getTag().toString();

                       // Toast.makeText(context, "Edit" + editstr, Toast.LENGTH_SHORT).show();
                        if (editstr == "") {
                            //Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
                            FileOutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = openFileOutput("silentlist.txt", Context.MODE_APPEND); //MODE PRIVATE

                                fileOutputStream.write((schedname + ",").getBytes());
                                fileOutputStream.write((schedsttime + ",").getBytes());
                                fileOutputStream.write((schedendtime + ",").getBytes());
                                fileOutputStream.write((schedmode + ",").getBytes());
                                fileOutputStream.write((schedd1 + ",").getBytes());
                                fileOutputStream.write((schedd2 + ",").getBytes());
                                fileOutputStream.write((schedd3 + ",").getBytes());
                                fileOutputStream.write((schedd4 + ",").getBytes());
                                fileOutputStream.write((schedd5 + ",").getBytes());
                                fileOutputStream.write((schedd6 + ",").getBytes());
                                fileOutputStream.write((schedd7 + ",").getBytes());
                                fileOutputStream.write("\n".getBytes());

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                           // Toast.makeText(context, "not null", Toast.LENGTH_SHORT).show();
                            try {

                                FileInputStream fis = openFileInput("silentlist.txt");
                                InputStreamReader streamReader = new InputStreamReader(fis);
                                BufferedReader bufferedReader = new BufferedReader(streamReader);

                                String oldcontent = "", newcontent = "";

                                String msg;
                                while ((msg = bufferedReader.readLine()) != null) {
                                    Log.d("editreader", "1. " + msg + "\n");
                                    oldcontent += msg + "\n";
                                }

                                newcontent = oldcontent.replaceAll(editstr, schedname + "," + schedsttime + "," + schedendtime + "," + schedmode + "," + schedd1 + "," + schedd2 + "," + schedd3 + "," + schedd4 + "," + schedd5 + "," + schedd6 + "," + schedd7 + ",");
                              //  Toast.makeText(context, oldcontent, Toast.LENGTH_SHORT).show();
                              //  Toast.makeText(context, newcontent, Toast.LENGTH_SHORT).show();


                                FileOutputStream fileOutputStream = null;
                                try {
                                    fileOutputStream = openFileOutput("silentlist.txt", Context.MODE_PRIVATE); //MODE PRIVATE
                                    fileOutputStream.write(newcontent.getBytes());
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                } finally {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }


                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                    }
                }
            }

        }


    }

}