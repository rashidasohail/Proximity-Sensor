package com.example.lab4_task2;



import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView ProximitySensor;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);



        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        if (myProximitySensor == null) {
            ProximitySensor.setText(R.string.no_proximity);
        } else {
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] ==0) {

                    Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                    imageView.startAnimation(animZoomIn);
                }
                else {
                    Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                    imageView.startAnimation(animZoomOut);
                }
            }
        }
    };
}