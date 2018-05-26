//ExSample3_17.java Mapに関するサンプル
package es.exsample;

import android.app.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.*;

public class ExSample3_17 extends AppCompatActivity {

    MapFragment mf;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mf = MapFragment.newInstance();  //マップフラグメントの生成
        FragmentManager fm = getFragmentManager();  //フラグメントマネージャーの取得
        FragmentTransaction ft = fm.beginTransaction();  //フラグメントトランザクションの取得
        ft.add(android.R.id.content, mf);  //フラグメントトランザクションにマップフラグメントを追加
        ft.commit();  //フラグメントトランザクションの開始
    }

}