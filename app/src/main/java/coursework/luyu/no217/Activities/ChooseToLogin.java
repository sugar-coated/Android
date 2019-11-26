package coursework.luyu.no217.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.R;

public class ChooseToLogin extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.loginPlus)
    Button loginPlus;
    @BindView(R.id.check_login)
    CheckBox checkLogin;
    @BindView(R.id.mima)
    Button mima;
    @BindView(R.id.check_pass)
    CheckBox checkPass;
    @BindView(R.id.passwordPlus)
    EditText passwordPlus;
    @BindView(R.id.login)
    RelativeLayout login;

    private  SharedPreferences sharedPreferences217;
    private  SharedPreferences.Editor editor217;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_to_login);
        ButterKnife.bind(this);

        sharedPreferences217 = this.getSharedPreferences("user1",MODE_PRIVATE);
        editor217 = sharedPreferences217.edit();

        loginPlus.setOnClickListener(this);
        mima.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.loginPlus:

                loginAndRegister();

                break;

            case R.id.mima:

                noLoginAndRegister();

                break;


            default:
                break;


        }
    }


    /*
    * 用户没有设置当前账户密码，但是游湖在自己的主页可以
    * 进行后续的添加设置
    * */
    private void noLoginAndRegister() {

        if(checkPass.isChecked()){

            if(sharedPreferences217.getInt("key",-1) != 1){

                editor217.putInt("key",0);
                editor217.putInt("keyForLayout",0);

                editor217.apply();
                toMainActivity();

            }else{

                Toast.makeText(this,"你之前创建账户，请用账户密码登录",Toast.LENGTH_LONG).show();

            }

        }else{

            Toast.makeText(this,"请确定你不想要名分之后再进行操作",Toast.LENGTH_LONG).show();

        }


    }

    /*
    * 通过对账户、账户密码在SharePerferences中进行读取
    * 判断当前输入是否合法
    * */
    private void loginAndRegister() {


        String username217 = name.getText().toString();
        String password217 = passwordPlus.getText().toString();

        if (checkLogin.isChecked()) {

            if(username217.equals("称号")|password217.equals("通行符")|username217.equals(null)|password217.equals(null)|username217.equals("")|password217.equals("")){

                Toast.makeText(this,"用户名、账户密码不能为空",Toast.LENGTH_LONG).show();

            }else{

                if(sharedPreferences217.getInt("key",-1) == 1){


                    Toast.makeText(this,"你之前创建账户，请用账户密码登录",Toast.LENGTH_LONG).show();



                }else{


                    editor217.putString("username",username217);
                    editor217.putString("password",password217);
                    editor217.putInt("key",1);
                    editor217.putInt("record_times",0);
                    editor217.putInt("finish_times",0);
                    editor217.putInt("keyForLayout",1);
                    editor217.apply();
                    /*
                     * 三秒后实现跳转
                     * */

                    Toast.makeText(this,"注册成功，三秒后自动跳转",Toast.LENGTH_LONG).show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Thread.sleep(3000);

                                toMainActivity();
                                //结束加载活动
                                finish();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();


                }


            }

        } else {

            String  true_username217 =  sharedPreferences217.getString("username",null);
            String true_password217 = sharedPreferences217.getString("password",null);
            Log.d("存储的用户名",username217);
            Log.d("存储的账户密码",password217);


            if(username217.equals( true_username217)&&password217.equals(true_password217)){

              toMainActivity();
              //结束加载进程


          }else{

              Toast.makeText(this,"输入错误，请重新输入",Toast.LENGTH_LONG).show();

          }

        }

    }

    private void toMainActivity() {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
         finish();
    }


}
