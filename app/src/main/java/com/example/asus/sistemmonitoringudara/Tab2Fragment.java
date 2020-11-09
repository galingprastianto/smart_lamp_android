package com.example.asus.sistemmonitoringudara;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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


public class Tab2Fragment extends Fragment implements AntaresHTTPAPI.OnResponseListener {

    private static final String TAG = "Tab2Fragment";

    private AntaresHTTPAPI antaresHTTPAPI;

    TextView textView7;
    TextView textView9;

    TextView textView18;
    TextView textView20;

   // TextView txtTimer;
   // TextView txtTimer2;
   // Handler customHandler = new Handler();
   // LinearLayout container;
   // Handler customHandler2 = new Handler();
   // LinearLayout container2;

    ToggleButton toggleButton;
    ToggleButton toggleButton2;


    Button button;


  //  long startTime=0L,timeInMilliseconds=0L, timeswapBuff=0L, updateTime=0L;
  //  long startTimee=0L,timeInMillisecondss=0L, timeswapBufff=0L, updateTimee=0L;


    private String TAGG;



   // Runnable updateTimerThread = new Runnable(){
   //     @Override
   //     public void run () {
   //         timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
   //         updateTime = timeswapBuff + timeInMilliseconds;
   //         int secs= (int) (updateTime/1000);
   //         int mins = secs/60;
   //         secs%=60;
   //         int milliseconds = (int) (updateTime%1000);
   //         txtTimer.setText(""+mins+":"+String.format("%2d",secs)+":"
   //                 +String.format("%3d",milliseconds));
   //         customHandler.postDelayed(this,0);
   //     }
   // };

/*
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
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);


        Button buttonback2 = (Button)view.findViewById(R.id.buttonback2);


        button = (Button) view.findViewById(R.id.button);

     //   txtTimer = (TextView) view.findViewById(R.id.txtTimer);
     //   txtTimer2 = (TextView) view.findViewById(R.id.txtTimer2);
     //   container = (LinearLayout) view.findViewById(R.id.container);
     //   container2 = (LinearLayout) view.findViewById(R.id.container);


        textView7 = (TextView) view.findViewById(R.id.textView7);
        textView9 = (TextView) view.findViewById(R.id.textView9);

        textView18 = (TextView) view.findViewById(R.id.textView18);
        textView20 = (TextView) view.findViewById(R.id.textView20);

        TAGG = this.getClass().getSimpleName();

        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        toggleButton2 = (ToggleButton) view.findViewById(R.id.toggleButton2);



        antaresHTTPAPI = new AntaresHTTPAPI();
        antaresHTTPAPI.addListener(this);

        loadSavedPreferences3();
        loadSavedPreferences4();



        buttonback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.continuous_fragment, new Fragment4());
                fr.commit();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        antaresHTTPAPI.getLatestDataofDevice(1, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "SensorLDR");
                        antaresHTTPAPI.getLatestDataofDevice(30, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "LDR");
                    }
                }, 0, 60000);
            }
        });


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {



                    antaresHTTPAPI.storeDataofDevice(8, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing", "8"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled


                } else {



                    antaresHTTPAPI.storeDataofDevice(9, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing", "9");// The toggle is disabled
                }
                savePreferences3("CheckBox_Value3", toggleButton.isChecked());
            }
        });


        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

               //     startTime = SystemClock.uptimeMillis();
               //     customHandler.postDelayed(updateTimerThread,0);

                    antaresHTTPAPI.storeDataofDevice(10, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "10"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled

               //     startTimee = SystemClock.uptimeMillis();
               //     customHandler2.postDelayed(updateTimerThread2,0);

                } else {


               //     timeswapBuff+= timeInMilliseconds;
               //     customHandler.removeCallbacks(updateTimerThread);

               //     timeswapBufff+= timeInMillisecondss;
               //     customHandler2.removeCallbacks(updateTimerThread2);


                    antaresHTTPAPI.storeDataofDevice(11, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "11");// The toggle is disabled
                }
                savePreferences4("CheckBox_Value4", toggleButton2.isChecked());
            }
        });



        return view;
    }



    public TextView getTextView7() {
        return textView7;
    }

    public void setTextView7(TextView textView7) {
        this.textView7 = textView7;
    }


    public TextView getTextView9() {
        return textView9;
    }

    public void setTextView9(TextView textView9) {
        this.textView9 = textView9;
    }





    public TextView getTextView18() {
        return textView18;
    }

    public void setTextView18(TextView textView18) {
        this.textView18 = textView18;
    }


    public TextView getTextView20() {
        return textView20;
    }

    public void setTextView20(TextView textView20) {
        this.textView20 = textView20;
    }



    private void loadSavedPreferences3() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue3 = sharedPreferences.getBoolean("CheckBox_Value3", false);
        if (checkBoxValue3) {
            toggleButton.setChecked(true);
        } else {
            toggleButton.setChecked(false);
        }


    }



    private void loadSavedPreferences4() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue4 = sharedPreferences.getBoolean("CheckBox_Value4", false);
        if (checkBoxValue4) {
            toggleButton2.setChecked(true);
        } else {
            toggleButton2.setChecked(false);
        }


    }




    private void savePreferences3(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }




    private void savePreferences4(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }





    @Override
    public void onResponse (AntaresResponse antaresResponse){
        //FragmentActivity activity = getActivity();
        // private Activity activity;
        //final FragmentActivity activity=getActivity();
        String content = null;

        //Log.d(TAG,antaresResponse.toString());
        if (antaresResponse.getRequestCode() == 1) {
            try {
                content = antaresResponse.toJSON().getJSONObject("m2m:cin").getString("con");
                JSONObject jsonObject = new JSONObject(content);


                if(jsonObject.has("ArusBeban")){
                    final double ArusBeban = jsonObject.getDouble("ArusBeban");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getTextView20().setText(Double.toString(ArusBeban));   //Set data to text
                            if (ArusBeban==0){ getTextView20().setTextColor(Color.BLUE);}
                        }
                    });
                    Log.d(TAG,Double.toString(ArusBeban));
                }


                if(jsonObject.has("PIR")){
                    final double PIR = jsonObject.getDouble("PIR");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getTextView9().setText(Double.toString(PIR));   //Set data to text
                            if (PIR==0){ getTextView9().setTextColor(Color.BLUE);}
                        }
                    });
                    Log.d(TAG,Double.toString(PIR));
                }

                if (jsonObject.has("Lux")) {
                    final double Lux = jsonObject.getDouble("Lux");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getTextView7().setText(Double.toString(Lux));
                            if (Lux>=30){ getTextView7().setTextColor(Color.YELLOW);}
                        }

                    });

                    Log.d(TAGG, Double.toString(Lux));
                }


                Log.d(TAGG, content);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }


    //private void runOnUiThread(Runnable runnable) {
    // }
}

