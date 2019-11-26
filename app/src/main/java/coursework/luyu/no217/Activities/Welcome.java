package coursework.luyu.no217.Activities;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import coursework.luyu.no217.R;

public class Welcome extends Activity {

    private Button close217;
    private int time217 = 4000;
    private boolean flag217 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        close217 = (Button) findViewById(R.id.close);

        close217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag217 = false;
                Log.d("调试信息", "已经进去点击事件");

                isRegister();
                finish();


            }
        });


        handler.sendEmptyMessageDelayed(0,1000);

    }

    //判断当前用户是否已经注册
    private void isRegister(){

        SharedPreferences sharedPreferences217 = this.getSharedPreferences("user1",MODE_PRIVATE);
        int statusOfKey217 = sharedPreferences217.getInt("key",-1);
        Intent intent217;


            if(statusOfKey217 == 1|statusOfKey217 == -1){

                intent217 = new Intent(Welcome.this, ChooseToLogin.class);
                startActivity(intent217);

            }else if(statusOfKey217 == 0){

                intent217 = new Intent(Welcome.this,MainActivity.class);
                startActivity(intent217);

           }

    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            time217 = time217 -1000;

            String number217 = String.valueOf(time217/1000);
            close217.setText("关闭 "+ number217);

            removeMessages(0);

            handler.sendEmptyMessageDelayed(0,1000);

            if(time217<=0&flag217==true){
                handler.removeCallbacksAndMessages(null);
                //判断当前是否为游客登录
                isRegister();


            }


        }
    };

}
