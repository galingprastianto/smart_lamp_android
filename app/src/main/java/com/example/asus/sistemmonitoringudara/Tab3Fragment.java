package com.example.asus.sistemmonitoringudara;

import android.os.Bundle;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
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
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;
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

/**
 * Created by User on 2/28/2017.
 */

public class Tab3Fragment extends Fragment implements AntaresHTTPAPI.OnResponseListener {
    private static final String TAG = "Tab3Fragment";

    private AntaresHTTPAPI antaresHTTPAPI;

    TextView textView23;
    TextView textView26;

/*
    TextView txtTimer;
    TextView txtTimer2;
    Handler customHandler = new Handler();
    LinearLayout container;
    Handler customHandler2 = new Handler();
    LinearLayout container2;
*/



    ToggleButton toggleButton7;
    ToggleButton toggleButton8;
    ToggleButton toggleButton9;

    EditText chooseTime;
    EditText chooseTime2;
    EditText chooseTime3;
    EditText chooseTime4;

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String waktu;
    //int hourOfDay;
    int waktu2;
    String amPm;



    Button button3;


  //  long startTime=0L,timeInMilliseconds=0L, timeswapBuff=0L, updateTime=0L;
  //  long startTimee=0L,timeInMillisecondss=0L, timeswapBufff=0L, updateTimee=0L;

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
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);

        Button buttonback3 = (Button)view.findViewById(R.id.buttonback3);

        button3 = (Button) view.findViewById(R.id.button3);



      //  txtTimer = (TextView) view.findViewById(R.id.txtTimer);
      //  txtTimer2 = (TextView) view.findViewById(R.id.txtTimer2);
      //  container = (LinearLayout) view.findViewById(R.id.container);
      //  container2 = (LinearLayout) view.findViewById(R.id.container);


        toggleButton7 = (ToggleButton) view.findViewById(R.id.toggleButton7);
        toggleButton8 = (ToggleButton) view.findViewById(R.id.toggleButton8);
        toggleButton9 = (ToggleButton) view.findViewById(R.id.toggleButton9);

        textView23 = (TextView) view.findViewById(R.id.textView23);
        textView26 = (TextView) view.findViewById(R.id.textView26);

        chooseTime = view.findViewById(R.id.chooseTime);
        chooseTime2 = view.findViewById(R.id.chooseTime2);
        chooseTime3 = view.findViewById(R.id.chooseTime3);
        chooseTime4 = view.findViewById(R.id.chooseTime4);

        TAGG = this.getClass().getSimpleName();

        antaresHTTPAPI = new AntaresHTTPAPI();
        antaresHTTPAPI.addListener(this);

        loadSavedPreferences5();
        loadSavedPreferences6();

        loadSavedPreferences7();
        loadSavedPreferences8();

        loadSavedPreferences9();
        loadSavedPreferences10();



        buttonback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.continuous_fragment, new Fragment4());
                fr.commit();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       startTime = SystemClock.uptimeMillis();
         //       customHandler.postDelayed(updateTimerThread,0);
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        antaresHTTPAPI.getLatestDataofDevice(31, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "SensorLDR");
                    }
                }, 0, 60000);
         //       startTimee = SystemClock.uptimeMillis();
         //       customHandler2.postDelayed(updateTimerThread2,0);


            }
        });




        toggleButton7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {



                    antaresHTTPAPI.storeDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing", "22"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled


                } else {

           //         timeswapBuff+= timeInMilliseconds;
           //         customHandler.removeCallbacks(updateTimerThread);

           //         timeswapBufff+= timeInMillisecondss;
           //         customHandler2.removeCallbacks(updateTimerThread2);

                    antaresHTTPAPI.storeDataofDevice(23, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing", "23");// The toggle is disabled
                }
                savePreferences5("CheckBox_Value5", toggleButton7.isChecked());
            }
        });


        toggleButton8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    antaresHTTPAPI.storeDataofDevice(16, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "16"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled
                } else {
                    antaresHTTPAPI.storeDataofDevice(17, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "17");// The toggle is disabled
                }
                savePreferences6("CheckBox_Value6", toggleButton8.isChecked());
            }
        });


        toggleButton9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            calendar = Calendar.getInstance();
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                            currentMinute = calendar.get(Calendar.MINUTE);
                            antaresHTTPAPI.storeDataofDevice(35, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "HourPhone", String.valueOf(currentHour));
                            antaresHTTPAPI.storeDataofDevice(36, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "MinutePhone", String.valueOf(currentMinute));
                        }
                    }, 0, 60000);

                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled



                } else {


                    //    antaresHTTPAPI.storeDataofDevice(36, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", "17");// The toggle is disabled
                }
                //savePreferences6("CheckBox_Value6", toggleButton8.isChecked());
            }
        });

/*
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antaresHTTPAPI.storeDataofDevice(24, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", String.valueOf(hourOfDay));
            }
        });
*/



/*
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        antaresHTTPAPI.storeDataofDevice(27, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCJadwalWaktu", String.valueOf(currentHour));
                    }
                }, 0, 60000);
            }
        });
*/



        /*
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                antaresHTTPAPI.storeDataofDevice(25, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "RCSensing",  String.valueOf(currentHour));
            }
        }, 0, 60000);
*/


        //     final SharedPreferences sharedPreferences = PreferenceManager
        //             .getDefaultSharedPreferences(getActivity());
        //      chooseTime.setText(sharedPreferences.getString("autosave",null)(String.format("%02d:%02d", hourOfDay, minutes) ));

        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        // if (hourOfDay >= 12) {
                        //     amPm = "PM";
                        // } else {
                        //     amPm = "AM";
                        // }
                        // chooseTime.setText (String.format("%02d:%02d", hourOfDay, minutes) );
                        //     final SharedPreferences sharedPreferences = PreferenceManager
                        //             .getDefaultSharedPreferences(getActivity());

                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                        savePreferences7("storedName", chooseTime.getText().toString());

                        antaresHTTPAPI.storeDataofDevice(24, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu", String.valueOf(hourOfDay));
                        antaresHTTPAPI.storeDataofDevice(25, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu2", String.valueOf(minutes));

                    }

                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                // savePreferences("storedName", chooseTime.getText().toString());
                //  waktu = Integer.toString(chooseTime);
                //waktu2 = new Integer(currentMinute).toString();
            }
        });


        chooseTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        // if (hourOfDay >= 12) {
                        //     amPm = "PM";
                        // } else {
                        //     amPm = "AM";
                        // }
                        chooseTime2.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                        savePreferences8("storedName2", chooseTime2.getText().toString());

                        antaresHTTPAPI.storeDataofDevice(26, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu3", String.valueOf(hourOfDay));
                        antaresHTTPAPI.storeDataofDevice(27, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu4", String.valueOf(minutes));

                    }

                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                // savePreferences("storedName", chooseTime.getText().toString());
                //  waktu = Integer.toString(chooseTime);
                //waktu2 = new Integer(currentMinute).toString();
            }
        });



        chooseTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        // if (hourOfDay >= 12) {
                        //     amPm = "PM";
                        // } else {
                        //     amPm = "AM";
                        // }
                        chooseTime3.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                        savePreferences9("storedName3", chooseTime3.getText().toString());

                        antaresHTTPAPI.storeDataofDevice(28, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu5", String.valueOf(hourOfDay));
                        antaresHTTPAPI.storeDataofDevice(29, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu6", String.valueOf(minutes));

                    }

                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                // savePreferences("storedName", chooseTime.getText().toString());
                //  waktu = Integer.toString(chooseTime);
                //waktu2 = new Integer(currentMinute).toString();
            }
        });



        chooseTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        // if (hourOfDay >= 12) {
                        //     amPm = "PM";
                        // } else {
                        //     amPm = "AM";
                        // }
                        chooseTime4.setText(String.format("%02d:%02d", hourOfDay, minutes) );
                        savePreferences10("storedName4", chooseTime4.getText().toString());

                        antaresHTTPAPI.storeDataofDevice(32, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu7", String.valueOf(hourOfDay));
                        antaresHTTPAPI.storeDataofDevice(33, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu8", String.valueOf(minutes));

                    }

                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                // savePreferences("storedName", chooseTime.getText().toString());
                //  waktu = Integer.toString(chooseTime);
                //waktu2 = new Integer(currentMinute).toString();
            }
        });



        return view;
    }


    public TextView getTextView23() {
        return textView23;
    }

    public void setTextView23(TextView textView23) {
        this.textView23 = textView23;
    }


    public TextView getTextView26() {
        return textView26;
    }

    public void setTextView16(TextView textView26) {
        this.textView26 = textView26;
    }



/*
    private void loadSavedPreferences7() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        String name = sharedPreferences.getString("storedName", "Choose the time");

        chooseTime.setText(name);
    }


    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

*/



    private void loadSavedPreferences7() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        String name = sharedPreferences.getString("storedName", "Time ?");

        chooseTime.setText(name);
    }


    private void savePreferences7(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }



    private void loadSavedPreferences8() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        String name = sharedPreferences.getString("storedName2", "Time ?");

        chooseTime2.setText(name);
    }


    private void savePreferences8(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }




    private void loadSavedPreferences9() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        String name = sharedPreferences.getString("storedName3", "Time ?");

        chooseTime3.setText(name);
    }


    private void savePreferences9(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }




    private void loadSavedPreferences10() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        String name = sharedPreferences.getString("storedName4", "Time ?");

        chooseTime4.setText(name);
    }


    private void savePreferences10(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }





    private void loadSavedPreferences5() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue5 = sharedPreferences.getBoolean("CheckBox_Value5", false);
        if (checkBoxValue5) {
            toggleButton7.setChecked(true);
        } else {
            toggleButton7.setChecked(false);
        }


    }



    private void loadSavedPreferences6() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue6 = sharedPreferences.getBoolean("CheckBox_Value6", false);
        if (checkBoxValue6) {
            toggleButton8.setChecked(true);
        } else {
            toggleButton8.setChecked(false);
        }


    }




    private void savePreferences5(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }




    private void savePreferences6(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }




    @Override
    public void onResponse(AntaresResponse antaresResponse) {


        String content = null;

        //Log.d(TAG,antaresResponse.toString());
        if (antaresResponse.getRequestCode() == 31) {
            try {
                content = antaresResponse.toJSON().getJSONObject("m2m:cin").getString("con");
                JSONObject jsonObject = new JSONObject(content);

/*
                if (jsonObject.has("ArusLampu")) {
                    final double ArusLampu = jsonObject.getDouble("ArusLampu");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getTextView23().setText(Double.toString(ArusLampu));
                            if (ArusLampu>=30){ getTextView23().setTextColor(Color.YELLOW);}
                        }

                    });

                    Log.d(TAGG, Double.toString(ArusLampu));
                }

    */



                if(jsonObject.has("ArusBeban")){
                    final double ArusBeban = jsonObject.getDouble("ArusBeban");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getTextView26().setText(Double.toString(ArusBeban));   //Set data to text
                            if (ArusBeban==0){ getTextView26().setTextColor(Color.BLUE);}
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


