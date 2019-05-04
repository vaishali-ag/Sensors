package vaisahli.agrawal.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
TextView sensorInfo;
private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorInfo=findViewById(R.id.Sensor_info);
        SensorManager sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        String  sensorName=getString(R.string.sensor_name);
       sensorName= sensorName.concat(" " + sensor.getName());
       sensorInfo.setText(sensorName);

       sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);




    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
      try{
          float x = event.values[0];

        float y = event.values[1];  //type_ACCELOMETER
        float z = event.values[2];
          sensorInfo.setText(event.timestamp + ":(" + x + "," + y + "," + z + ")");
         //sensorInfo.setText(event.timestamp + ":(" + x +  ")");

    }catch (Exception e){
          sensorInfo.setText(e.getMessage());
      }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
