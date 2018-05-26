//ExSample3_09.java インテントを用いたカメラ利用のサンプル
package es.exsample;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ExSample3_09 extends AppCompatActivity {

	int SAMPLE_APP = 1;
	ImageView iv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		setContentView(ll);

		iv = new ImageView(this);
		Button bt = new Button(this);
		bt.setText("カメラ起動");
		ll.addView(iv);
		ll.addView(bt);

		bt.setOnClickListener(new ExSampleClickListener());
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SAMPLE_APP) {
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");  //インテント先からのデータの受け取り
			iv.setImageBitmap(bitmap);
		}
	}
	class ExSampleClickListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //画像取得のためのインテントの設定
			startActivityForResult(intent, SAMPLE_APP);  //インテントの開始
		}
	}
}