package coursework.luyu.no217.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.DBManager;
import coursework.luyu.no217.Sqlite.Helper;

public class CreateContent extends Activity implements View.OnClickListener {

    @BindView(R.id.type)
    TextView type217;
    private String currentTime217;
    private String event_type217;
    private String event_title217;
    private String event_time217;
    private String event_content217;
    private Helper helper217;
    private String showEventType217;
    private DBManager dbManager217;


    @BindView(R.id.back)
    TextView back217;
    @BindView(R.id.new_title)
    EditText newTitle217;

    @BindView(R.id.submit)
    TextView submit217;
    @BindView(R.id.time)
    TextView time217;
    @BindView(R.id.content)
    EditText content217;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_content);
        //初始胡连接数据对象和数据库操作对象
        helper217 = new Helper(this, "TuoGe.db", null, 1);
        dbManager217 = new DBManager(helper217.getWritableDatabase(), this);
        //绑定butterknife
        ButterKnife.bind(this);

        //获取类型
        Intent intent217 = getIntent();
        showEventType217 = intent217.getStringExtra("EventType");
        type217.setText(showEventType217);

        //显示当前时间
        setCurrentTime();

        back217.setOnClickListener(this);
        submit217.setOnClickListener(this);
        back217.setOnClickListener(this);


    }

    private void setCurrentTime() {

        Calendar calendar = Calendar.getInstance();

        int month217 = calendar.get(Calendar.MONTH) + 1;
        int day217 = calendar.get(Calendar.DAY_OF_MONTH);
        int hour217 = calendar.get(Calendar.HOUR_OF_DAY);
        int minute217 = calendar.get(Calendar.MINUTE);

        currentTime217 = month217 + "." + day217 + " " + hour217 + ":" + minute217;
        time217.setText(currentTime217);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back:
                    finish();
                break;

            case R.id.submit:

                event_content217 = content217.getText().toString();
                event_time217 = currentTime217;
                event_title217 = newTitle217.getText().toString();
                event_type217 = type217.getText().toString();
                //调用操作数据库对象进行对数据插入
                dbManager217.insert(event_title217, event_type217, event_time217, event_content217);

                break;



            default:
                break;

        }


    }


}
