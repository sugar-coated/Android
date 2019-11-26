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
    TextView type;
    private String currentTime217;
    private String event_type217;
    private String event_title217;
    private String event_time217;
    private String event_content217;
    private Helper helper217;
    private String showEventType;
    private DBManager dbManager217;


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.new_title)
    EditText newTitle;

    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    EditText content;



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
        Intent intent = getIntent();
        showEventType = intent.getStringExtra("EventType");
        type.setText(showEventType);

        //显示当前时间
        setCurrentTime();

        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    private void setCurrentTime() {

        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        currentTime217 = month + "." + day + " " + hour + ":" + minute;
        time.setText(currentTime217);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back:
                    finish();
                break;

            case R.id.submit:

                event_content217 = content.getText().toString();
                event_time217 = currentTime217;
                event_title217 = newTitle.getText().toString();
                event_type217 = type.getText().toString();
                //调用操作数据库对象进行对数据插入
                dbManager217.insert(event_title217, event_type217, event_time217, event_content217);

                break;



            default:
                break;

        }


    }


}
