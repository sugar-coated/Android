package coursework.luyu.no217.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.R;

public class RegisterForVisitor extends AppCompatActivity implements View.OnClickListener {



    @BindView(R.id.backPlus)
    Toolbar backPlus217;
    @BindView(R.id.new_name)
    EditText newName217;
    @BindView(R.id.new_password)
    EditText newPassword217;
    @BindView(R.id.repeat_new_password)
    EditText repeatNewPassword217;
    @BindView(R.id.change)
    Button change217;
    @BindView(R.id.reset)
    Button reset217;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_visitor);
        ButterKnife.bind(this);

        //设置toolBar同时去除标题
        setSupportActionBar(backPlus217);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.to_add);

        listener();

    }

    //监听器
    private void listener() {

      change217.setOnClickListener(this);
      reset217.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.change:

             String name217 = newName217.getText().toString();
             String password217 = newPassword217.getText().toString();
             String repeatPassword217 = repeatNewPassword217.getText().toString();

             register(name217,password217,repeatPassword217);


                break;
            case R.id.reset:
                newName217.setText("");
                newPassword217.setText("");
                repeatNewPassword217.setText("");
                break;
                default:
                    break;

        }

    }

    private void register(String name217, String password217, String repeatPassword217) {

        if(repeatPassword217.equals(password217)){

            SharedPreferences sharedPreferences217 = this.getSharedPreferences("user1",MODE_PRIVATE);
            SharedPreferences.Editor editor217 = sharedPreferences217.edit();
            editor217.putString("username",name217);
            editor217.putString("password",password217);
            editor217.putInt("key",1);
            editor217.putInt("record_times",0);
            editor217.putInt("finish_times",0);
            editor217.putInt("keyForLayout",1);
            editor217.apply();

            toLogin();

        }else{

            Toast.makeText(this,"两次账户密码输入不一致,请重新输入",Toast.LENGTH_LONG).show();
        }

    }

    private void toLogin(){


        Toast.makeText(this,"成功添加用户，三秒后自动跳转登陆界面",Toast.LENGTH_LONG).show();

                new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(3000);
                    Intent intent217 = new Intent(RegisterForVisitor.this, ChooseToLogin.class);
                    startActivity(intent217);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
