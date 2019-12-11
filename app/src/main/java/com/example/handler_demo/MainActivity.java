package com.example.handler_demo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartBtn;
    private TextView mTimeText;

    private static final int TIME_START_ACTION = 1;
    private static int TIME = 60;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TIME_START_ACTION:
                    mTimeText.setText(String.valueOf(msg.obj));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
    }

    private void initView() {
        mStartBtn = findViewById(R.id.start_btn);
        mTimeText = findViewById(R.id.time_text);

        mStartBtn.setOnClickListener(this);
        mTimeText.setText(String.valueOf(TIME));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                new Thread(() -> {
//                    for (int i = TIME;i>-1;i--){
////                            mHandler.sendMessage(mHandler.obtainMessage(TIME_START_ACTION,i));
//                            Message message = new Message();
//                            message.obj = i;
//                            message.what = TIME_START_ACTION;
//                            mHandler.sendMessage(message);
//                        mHandler.sendEmptyMessage(TIME_START_ACTION);
////                            mHandler.obtainMessage(TIME_START_ACTION,i).sendToTarget();
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                        for (int i = 60;i>0;i--){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mHandler.post(() -> mTimeText.setText(String.valueOf(--TIME)));
                        }
                }).start();

                break;
        }
    }
}
