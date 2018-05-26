//ExSample3_12.java 加速度センサとジャイロセンサに関するサンプル
package es.exsample;

import android.app.Activity;
import android.content.Context;
import android.os.*;
import android.hardware.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

public class ExSample3_12 extends AppCompatActivity implements SensorEventListener {

	TextView      tv;
	SensorManager sm;
	Sensor        ac;
	Sensor        gy;
	float[]       values=new float[6];

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		LinearLayout ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		tv=new TextView(this);
		ll.addView(tv);
	}

	protected void onResume(){
		super.onResume();
			sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
			ac = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  //加速度センサの取得
			gy = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);  //ジャイロセンサの取得
			sm.registerListener(this, ac, SensorManager.SENSOR_DELAY_NORMAL);  //加速度センサのリスナー登録
			sm.registerListener(this, gy, SensorManager.SENSOR_DELAY_NORMAL);  //ジャイロセンサのリスナー登録
	}

	protected void onStop(){
		super.onStop();
		sm.unregisterListener(this);
	}

	public void onSensorChanged(SensorEvent event){
		if (event.sensor==ac){  //加速度センサの値取得
			values[0]=event.values[0];
			values[1]=event.values[1];
			values[2]=event.values[2];
		}
		if (event.sensor==gy){  //ジャイロセンサの値取得
			values[3]=event.values[0];
			values[4]=event.values[1];
			values[5]=event.values[2];
		}
		String text=
				"X軸加速度:"+values[0]+
						"\nY軸加速度:"+values[1]+
						"\nZ軸加速度:"+values[2]+
						"\nX軸角速度:"+values[3]+
						"\nY軸角速度:"+values[4]+
						"\nZ軸角速度:"+values[5];
		tv.setText(text);
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy){}
}
