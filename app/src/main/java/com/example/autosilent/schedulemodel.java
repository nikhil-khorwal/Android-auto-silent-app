package com.example.autosilent;

public class schedulemodel {

   String schedname,schedstart,schedend,schedimg;
   String d1,d2,d3,d4,d5,d6,d7;

    public Boolean getMyswitch() {
        return myswitch;
    }

    public void setMyswitch(Boolean myswitch) {
        this.myswitch = myswitch;
    }

    Boolean myswitch;

    public schedulemodel(String schedname, String schedstart, String schedend, String schedimg, String d1, String d2, String d3, String d4, String d5, String d6, String d7) {
        this.schedname = schedname;
        this.schedstart = schedstart;
        this.schedend = schedend;
        this.schedimg = schedimg;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d5 = d5;
        this.d6 = d6;
        this.d7 = d7;
    }


    public String getSchedname() {
        return schedname;
    }

    public String getSchedstart() {
        return schedstart;
    }

    public String getSchedend() {
        return schedend;
    }

    public String getSchedimg() {
        return schedimg;
    }

    public String getD1() {
        return d1;
    }

    public String getD2() {
        return d2;
    }

    public String getD3() {
        return d3;
    }

    public String getD4() {
        return d4;
    }

    public String getD5() {
        return d5;
    }

    public String getD6() {
        return d6;
    }

    public String getD7() {
        return d7;
    }
}
