package coursework.luyu.no217.Activities;

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

public class ChangeInformation extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.change)
    Button change217;
    @BindView(R.id.reset)
    Button reset217;
    @BindView(R.id.backTo)
    Toolbar backTo217;
    @BindView(R.id.input_password1_217)
    EditText inputPassword1217;
    @BindView(R.id.input_password2_217)
    EditText inputPassword2217;

    private SharedPreferences sharedPreferences217;
    private SharedPreferences.Editor editor217;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
        ButterKnife.bind(this);

        change217.setOnClickListener(this);
        reset217.setOnClickListener(this);

        //设置toolBar同时去除标题
        setSupportActionBar(backTo217);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.to_add);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.change:

                change_option();

                break;
            case R.id.reset:
                reset_option();
                break;

            default:
                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:

                finish();
                break;
            default:
                break;

        }
        return true;
    }

    private void reset_option() {

    inputPassword1217.setText("");
    inputPassword2217.setText("");
    }

    private void change_option() {

        String input_password1_217 = inputPassword1217.getText().toString();
        String input_password2_217 = inputPassword2217.getText().toString();

        if (input_password1_217.equals("") | input_password2_217.equals("") | input_password1_217.equals(null) | input_password2_217.equals(null)) {

            Toast.makeText(this, "当前输入不规范", Toast.LENGTH_LONG).show();

        } else {

            if (!input_password1_217.equals(input_password2_217)) {

                Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_LONG).show();

            } else {
                sharedPreferences217 = this.getSharedPreferences("user1", MODE_PRIVATE);

                editor217 = sharedPreferences217.edit();
                editor217.putString("password", input_password1_217);
                editor217.apply();

                Toast.makeText(this, "更新成功", Toast.LENGTH_LONG).show();


            }

        }

    }
}
