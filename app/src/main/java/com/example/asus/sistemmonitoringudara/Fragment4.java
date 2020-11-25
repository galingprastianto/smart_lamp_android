package com.example.asus.sistemmonitoringudara;

import android.content.Context;
import android.graphics.Color;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;


public class Fragment4 extends Fragment implements AntaresHTTPAPI.OnResponseListener {
   //  private static final String TAG = "Fragment4";

    private AntaresHTTPAPI antaresHTTPAPI;

    private Handler antaresHandler = new Handler();

    ToggleButton toggleButton10;
    ToggleButton toggleButton11;

    TextView antaresInfoText;

    private String dataDevice = "Loading";

    private String dataToTextView;

    private String testString = "Test";
//    ToggleButton toggleButton12;


    // private String TAGG;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);


        Button btnFragment4 = (Button) view.findViewById(R.id.btnFragment4);
        Button btnFragment5 = (Button) view.findViewById(R.id.btnFragment5);
//        Button btnFragment6 = (Button) view.findViewById(R.id.btnFragment6);


        toggleButton10 = (ToggleButton) view.findViewById(R.id.toggleButton10);
        toggleButton11 = (ToggleButton) view.findViewById(R.id.toggleButton11);
//        toggleButton12 = (ToggleButton) view.findViewById(R.id.toggleButton12);
        //TAGG = this.getClass().getSimpleName();

        antaresInfoText = (TextView) view.findViewById(R.id.status_antares);


        antaresHTTPAPI = new AntaresHTTPAPI();
        antaresHTTPAPI.addListener(this);

        loadSavedPreferences11();
        loadSavedPreferences12();
//        loadSavedPreferences13();

        antaresDataCheck.run();


        btnFragment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.continuous_fragment, new Tab1Fragment());
                fr.commit();
            }
        });

        btnFragment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.continuous_fragment, new Tab3Fragment());
                fr.commit();
            }
        });

//        btnFragment6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.continuous_fragment, new Tab3Fragment());
//                fr.commit();
//            }
//        });


        toggleButton10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //SharedPreferences preferences = getSharedPreferences;
                if (isChecked) {

                     antaresHTTPAPI.storeDataofDevice(37, "38fa9dc94027a9e3:ad7eed0d8b228eb8", "HomeAutomationHome", "StatusRC", "37"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled


                } else {
                    antaresHTTPAPI.storeDataofDevice(38, "38fa9dc94027a9e3:ad7eed0d8b228eb8", "HomeAutomationHome", "StatusRC", "38");// The toggle is disabled
                }
                savePreferences11("CheckBox_Value11", toggleButton10.isChecked());
            }
        });


        toggleButton11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //SharedPreferences preferences = getSharedPreferences;
                if (isChecked) {

                      antaresHTTPAPI.storeDataofDevice(39, "38fa9dc94027a9e3:ad7eed0d8b228eb8", "HomeAutomationHome", "StatusSensing", "39"); // The toggle is enabled
                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled


                } else {
                     antaresHTTPAPI.storeDataofDevice(40, "38fa9dc94027a9e3:ad7eed0d8b228eb8", "HomeAutomationHome", "StatusSensing", "40");// The toggle is disabled
                }
                savePreferences12("CheckBox_Value12", toggleButton11.isChecked());
            }
        });


//        toggleButton12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                //SharedPreferences preferences = getSharedPreferences;
//                if (isChecked) {
//
//                     antaresHTTPAPI.storeDataofDevice(41, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "StatusJadwal", "41"); // The toggle is enabled
//                    //antaresHTTPAPI.getLatestDataofDevice(22, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "JadwalWaktu"); // The toggle is enabled
//
//
//                } else {
//                     antaresHTTPAPI.storeDataofDevice(42, "f7c006295a705ee2:44a74791cc28c092", "HomeAutomationHome", "StatusJadwal", "42");// The toggle is disabled
//                }
//                savePreferences13("CheckBox_Value13", toggleButton12.isChecked());
//            }
//        });


        return view;
    }



    private void loadSavedPreferences11() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue11 = sharedPreferences.getBoolean("CheckBox_Value11", false);
        if (checkBoxValue11) {
            toggleButton10.setChecked(true);
        } else {
            toggleButton10.setChecked(false);
        }


    }



    private void loadSavedPreferences12() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        boolean checkBoxValue12 = sharedPreferences.getBoolean("CheckBox_Value12", false);
        if (checkBoxValue12) {
            toggleButton11.setChecked(true);
        } else {
            toggleButton11.setChecked(false);
        }


    }


//    private void loadSavedPreferences13() {
//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(getActivity());
//        boolean checkBoxValue13 = sharedPreferences.getBoolean("CheckBox_Value13", false);
//        if (checkBoxValue13) {
//            toggleButton12.setChecked(true);
//        } else {
//            toggleButton12.setChecked(false);
//        }
//
//
//    }



    private void savePreferences11(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    private void savePreferences12(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void savePreferences13(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public TextView getAntaresInfoText() {
        return antaresInfoText;
    }

    public void setAntaresInfoText(TextView antaresInfoText) {
        this.antaresInfoText = antaresInfoText;
    }

    private final Runnable antaresDataCheck = new Runnable() {
        @Override
        public void run() {
            antaresHTTPAPI.getLatestDataofDevice("38fa9dc94027a9e3:ad7eed0d8b228eb8","SmartLampSkuy","LampuDalam");

            antaresHandler.postDelayed(this,5000);
        }
    };

    @Override
    public void onResponse(AntaresResponse antaresResponse) {
        // --- Cetak hasil yang didapat dari ANTARES ke System Log --- //
        Log.d("Test",antaresResponse.toString());

        final String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String condition = "";
        String mode = "";

        try {
            JSONObject body = new JSONObject(antaresResponse.getBody());
            dataDevice = body.getJSONObject("m2m:cin").getString("con");
            JSONObject jsonObject = new JSONObject(dataDevice);

            if(jsonObject.has("Condition")){
                condition = jsonObject.getString("Condition");
            }

            if (jsonObject.has("Fitur")) {
                mode = jsonObject.getString("Fitur");
            }

            final String finalMode = mode;
            final String finalCondition = condition;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    antaresInfoText.setText("Mode :" + finalMode + "\n" + "Time :" + date +"\nCondition :" + finalCondition);
                }
            });

//            getAntaresInfoText().setText("Mode :" + mode + "\n" + "Time :" + date +"\nCondition :" + condition);

            Log.d("Test", jsonObject.getString("Time"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
