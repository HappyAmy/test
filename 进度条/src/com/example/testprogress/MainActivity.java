package com.example.testprogress;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ProgressBar pb;
    private TextView tv;
    private SeekBar seekbar;
    int pos=0;
    
    Handler handler = new Handler(){
    	public void handleMessage(Message msg) {
    		//处理消息
    		if(msg.what==200){
    			pos=msg.arg1;
    			//pb.setProgress(pos);
    			tv.setText(pos+"");
    			seekbar.setProgress(pos);
    		}
    	};
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//pb=(ProgressBar)this.findViewById(R.id.pb);
		tv=(TextView)this.findViewById(R.id.tv);
		seekbar=(SeekBar)this.findViewById(R.id.seekBar);
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				pos=progress;
				seekbar.setProgress(pos);
			}
		});
		
		
		
		Thread thread=new Thread(){
			public void run() {
				while(pos<100){
				try {
					sleep(1000);
					pos=pos+20;
					//发送消息给主线程,消息中包含需要更改界面的数据
					Message msg= new Message();
					msg.what=200;//what --什么类型的消息，200代表成功
					msg.arg1=pos;
					handler.sendMessage(msg);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			}
		};
		thread.start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Thread.interrupted();
		super.onDestroy();
	}
}
