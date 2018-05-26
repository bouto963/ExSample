//ExSample2_13.java ＸＭＬを用いたレイアウトとイベント処理に関するサンプル
package es.exsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class ExSample2_12 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elexsample);  //レイアウトのXMLファイル読み込み

        Button button = (Button)findViewById(R.id.bt_search);  //ビューのidを用いてオブジェクトを関連付け
        button.setOnClickListener(new ButtonClickListener());  //ボタンクリック時のリスナー登録

        Spinner sp = (Spinner)findViewById(R.id.spinner);  //ビューのidを用いてオブジェクトを関連付け
        String[] str ={"兼六園","21世紀美術館","近江町市場",
                "東茶屋街","武家屋敷","忍者寺"};

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        sp.setAdapter(ad);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new ExSampleItemSelectedListener()); //スピナーのアイテム選択時のリスナー登録
    }

    class ButtonClickListener implements OnClickListener {  //ボタンクリック時のイベント処理
        public void onClick(View v) {
            EditText input = (EditText)findViewById(R.id.et_sight);  //ビューのidを用いてオブジェクトを関連付け
            TextView tv = (TextView)findViewById(R.id.tv_1);  //ビューのidを用いてオブジェクトを関連付け
            tv.setText("検索結果：" + input.getText());
        }
    }

    class ExSampleItemSelectedListener implements AdapterView.OnItemSelectedListener {  //スピナーのアイテム選択時のイベント処理
        public void onItemSelected(AdapterView<?>v, View iv, int pos, long id){
            TextView tmp = (TextView) iv;
            TextView tv = (TextView)findViewById(R.id.tv_1);  //ビューのidを用いてオブジェクトを関連付け
            tv.setText("検索結果：" + tmp.getText());
        }
        public void onNothingSelected(AdapterView<?> arg0){}
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ex_sample, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}