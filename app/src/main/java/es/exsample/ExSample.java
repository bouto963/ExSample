//ExSample4_02.java インテント先から返ってきたデータを利用するサンプル
package es.exsample;

import java.util.*;
import android.app.*;
import android.content.*;
import android.speech.*;
import android.os.*;
import android.speech.tts.*;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import android.os.Bundle;

public class ExSample extends AppCompatActivity  {

    public static int SAMPLE_APP = 1;  //呼び出し元のインテントのID
    Button bt;
    Button bt1;
    TextView tv;
    TextToSpeech tts;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        tv = new TextView(this);
        bt = new Button(this);
        bt.setText("入力");
        bt1 = new Button(this);
        bt1.setText("開始");


        ll.addView(tv);
        ll.addView(bt);
        ll.addView(bt1);

        tts = new TextToSpeech(getApplicationContext(),  new SampleInitListener());  //テキストトゥスピーチオブジェクトの生成と、イベントリスナーの登録
        tts.setLanguage(Locale.JAPANESE);  //読み上げる言語の設定
        bt.setOnClickListener(new SampleClickListener());
        bt1.setOnClickListener(new SampleClickListener2());
    }

    public void onActivityResult(int reqcode, int result, Intent it){
        if(reqcode == SAMPLE_APP && result == RESULT_OK){ //インテント先から結果が返ってきたときの処理
            ArrayList<String> list = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  //インテント先からのデータ取得
            tv.setText(list.get(0));
        }
    }

    class SampleClickListener implements OnClickListener{
        public void onClick(View v){
            try{
                Intent it = new Intent();
                it.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);  //音声認識にアクションを設定
                it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  //音声認識の設定
                it.putExtra(RecognizerIntent.EXTRA_PROMPT, "入力してください。");  //音声認識のプロンプト文字の設定
                startActivityForResult(it, SAMPLE_APP); //結果を取得するインテントのスタート
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(),"音声認識は利用できません。",Toast.LENGTH_LONG).show();
            }

        }
    }
    class SampleClickListener2 implements OnClickListener{
        public void onClick(View v){
            String str  = tv.getText().toString();
            String utteranceId = this.hashCode() + "";  //utteranceIdの取得
            if(str != null)
                tts.speak(str, TextToSpeech.QUEUE_FLUSH, null, utteranceId);  //テキストの読み上げ
        }
    }

    class SampleInitListener implements TextToSpeech.OnInitListener {
        public void onInit(int status){}
    }
}

