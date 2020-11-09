package com.example.asus.sistemmonitoringudara;

import android.os.Bundle;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.TwoStatePreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.util.Log;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.View.OnClickListener;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by User on 2/28/2017.
 */

public class Tab1Fragment extends Fragment implements AntaresHTTPAPI.OnResponseListener {
    private static final String TAG = "Tab1Fragment";

    private AntaresHTTPAPI antaresHTTPAPI;


    ToggleButton toggleButton4;
    ToggleButton toggleButton6;


    Button button2;

    TextView textView14;
    TextView textView16;

   /*
    //Button btnStart;
    TextView txtTimer;
    TextView txtTimer2;
    Handler customHandler = new Handler();
    LinearLayout container;
    Handler customHandler2 = new Handler();
    LinearLayout container2;


    long startTime=0L,timeInMilliseconds=0L, timeswapBuff=0L, updateTime=0L;
    long startTimee=0L,timeInMillisecondss=0L, timeswapBufff=0L, updateTimee=0L;
*/


    private String TAGG;

/*
    Runnable updateTimerThread = new Runnable(){
        @Override
        public void run () {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeswapBuff + timeInMilliseconds;
            int secs= (int) (updateTime/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int) (updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%2d",secs)+":"
                                        +String.format("%3d",milliseconds));
            customHandler.postDelayed(this,0);
        }
    };


    Runnable updateTimerThread2 = new Runnable(){
        @Override
        public void run () {
            timeInMillisecondss = SystemClock.uptimeMillis()-startTimee;
            updateTimee = timeswapBufff + timeInMillisecondss;
            int secss= (int) (updateTimee/1000);
            int minss = secss/60;
            secss%=60;
            int millisecondss = (int) (updateTimee%1000);
            txtTimer2.setText(""+minss+":"+String.format("%2d",secss)+":"
                    +String.format("%3d",millisecondss));
            customHandler2.postDelayed(this,0);
        }
    };
*/


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);

        Button buttonback1 = (Button)view.findViewById(R.id.buttonback1);

        button2 = (Button) view.findViewById(R.id.button2);
        //btnStart = (Button) view.findViewById(R.id.btnStart);
      //  txtTimer = (TextView) view.findViewById(R.id.txtTimer);
      //  txtTimer2 = (TextView) view.findViewById(R.id.txtTimer2);
      //  container = (LinearLayout) view.findViewById(R.id.container);
      //  container2 = (LinearLayout) view.findViewById(R.id.container);

        toggleButton4 = (ToggleButton) view.findViewById(R.id.toggleButton4);
        toggleButton6 = (ToggleButton) view.findViewById(R.id.toggleButton6);

        textView14 = (TextView) view.findViewById(R.id.textView14);
        textView16 = (TextView) view.findViewById(R.id.textView16);


        TAGG = this.getClass().getSimpleName();


        antaresHTTPAPI = new AntaresHTTPAPI();
        antaresHTTPAPI.addListener(this);

        loadSavedPreferences();
        loadSavedPreferences2();
        //toggleButton4.setOnClickListener((OnClickListener) this);

        //toggleButton4.setChecked(getSharedPreferences(mSharedPreferences, MODE_PRIVATE).getBoolean("dontShowAgain", false));







        buttonback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.continuous_fragment, new Fragment4());
                fr.commit();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        antaresHTTPAPI.getLatestDataofDevice(30, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "SensorLDR");
                    }
                }, 0, 60000);
            }
        });


        toggleButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //SharedPreferences preferences = getSharedPreferences;
                if (isChecked) {
                  //  startTime = SystemClock.uptimeMillis();
                  //  customHandler.postDelayed(updateTimerThread,0);

                    antaresHTTPAPI.storeDataofDevice(2, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing", "2"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled
                  //  startTimee = SystemClock.uptimeMillis();
                  //  customHandler2.postDelayed(updateTimerThread2,0);

                } else {

                  //  timeswapBuff+= timeInMilliseconds;
                  //  customHandler.removeCallbacks(updateTimerThread);

                  //  timeswapBufff+= timeInMillisecondss;
                  //  customHandler2.removeCallbacks(updateTimerThread2);

                    antaresHTTPAPI.storeDataofDevice(3, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing", "3");// The toggle is disabled


                }
                savePreferences("CheckBox_Value", toggleButton4.isChecked());
            }
        });



        toggleButton6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    antaresHTTPAPI.storeDataofDevice(4, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "4"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled
                } else {
                    antaresHTTPAPI.storeDataofDevice(5, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "5");// The toggle is disabled
                }
                savePreferences2("CheckBox_Value2", toggleButton6.isChecked());
            }
        });

        return view;
    }




    public TextView getTextView14() {
        return textView14;
    }

    public void setTextView14(TextView textView14) {
        this.textView14 = textView14;
    }


    public TextView getTextView16() {
        return textView16;
    }

    public void setTextView16(TextView textView16) {
        this.textView16 = textView16;
    }





    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value", false);
        if (checkBoxValue) {
            toggleButton4.setChecked(true);
        } else {
            toggleButton4.setChecked(false);
        }


    }



    private void loadSavedPreferences2() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue2 = sharedPreferences.getBoolean("CheckBox_Value2", false);
        if (checkBoxValue2) {
            toggleButton6.setChecked(true);
        } else {
            toggleButton6.setChecked(false);
        }


    }




    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }




    private void savePreferences2(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    @Override
    public void onResponse(AntaresResponse antaresResponse) {
        String content = null;

        if (antaresResponse.getRequestCode() == 30) {
            try {
                content = antaresResponse.toJSON().getJSONObject("m2m:cin").getString("con");
                JSONObject jsonObject = new JSONObject(content);


                if(jsonObject.has("ArusBeban")){
                    final double ArusBeban = jsonObject.getDouble("ArusBeban");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getTextView16().setText(Double.toString(ArusBeban));   //Set data to text
                            if (ArusBeban==0){ getTextView16().setTextColor(Color.BLUE);}
                        }
                    });
                    Log.d(TAG,Double.toString(ArusBeban));
                }



                Log.d(TAGG, content);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }



    }
}
