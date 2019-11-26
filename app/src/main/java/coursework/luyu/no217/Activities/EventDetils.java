package coursework.luyu.no217.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Service.ShowEventService;
import coursework.luyu.no217.Sqlite.DBManager;
import coursework.luyu.no217.Sqlite.Helper;

public class EventDetils extends AppCompatActivity  {

    @BindView(R.id.actionBarOfDetails)
    Toolbar actionBarOfDetails217;
    @BindView(R.id.selected_cardView_title)
    EditText selectedCardViewTitle217;
    @BindView(R.id.selected_cardView_content)
    EditText selectedCardViewContent217;
    @BindView(R.id.selected_cardView_time)
    TextView selectedCardViewTime;

    private String position217;
    private List<Event> list217;
    private Helper helper217;
    private SQLiteDatabase db217;
    private DBManager manager217;
    private Context mContext217;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detils);
        mContext217 = this;
        ButterKnife.bind(this);
        position217 = getIntent().getStringExtra("card_view_position");
        Log.d("调试信息", "当前position为：" + position217);

        //设置toolBar同时去除标题
        setSupportActionBar(actionBarOfDetails217);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.to_add);



        helper217 = new Helper(this, "TuoGe.db", null, 1);
        db217 = helper217.getWritableDatabase();
        manager217 = new DBManager(db217, this);

        initData();
        intView();

    }

    private void intView() {

        selectedCardViewTitle217.setText(list217.get(0).getTitle217());
        selectedCardViewContent217.setText(list217.get(0).getContent217());
        selectedCardViewTime.setText(list217.get(0).getTime217());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

            case R.id.delete_details:

                 confirm("你确定要删除当前事件么？",0);

                break;

            case R.id.yun:

                Intent intent_start217  = new Intent(this, ShowEventService.class);
                intent_start217.putExtra("title",selectedCardViewTitle217.getText().toString());
                intent_start217.putExtra("content",selectedCardViewContent217.getText().toString());

                if(Build.VERSION.SDK_INT>=26){
                    startForegroundService (intent_start217);
                }else{
                    startService (intent_start217);
                }
                startService(intent_start217);


                break;

            case R.id.cancel:

                Intent intent_stop = new Intent(this,ShowEventService.class);
                stopService(intent_stop);
                break;

            case R.id.rewrite:

                confirm("确定要重写当前信息么?",1);

                break;

            default:

                break;

        }

        return true;

    }

    private void confirm(String message,int type) {

        AlertDialog.Builder builder217  = new AlertDialog.Builder(this);
        createAlertDialog(builder217,message,type);
        AlertDialog alertDialog217 = builder217.create();
        alertDialog217.show();


    }

    private void createAlertDialog(AlertDialog.Builder builder217,String message,int type) {


        builder217.setTitle("坨哥给你小提醒").
                setMessage(message).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (type){

                            case 0:

                                manager217.delete(position217);
                                break;
                            case 1:

                                String title217 = selectedCardViewTitle217.getText().toString();
                                String content217 = selectedCardViewContent217.getText().toString();
                                rewriteData(title217, content217);
                                break;
                                default:
                                    break;

                        }

                        Toast.makeText(mContext217,"操作成功",Toast.LENGTH_LONG).show();

                        dialog.dismiss();
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });

    }

    private void rewriteData(String title217, String content217) {

        manager217.rewrite(position217, -1, title217, content217, "position");

    }

    private void initData() {

        SQLiteDatabase db217 = helper217.getWritableDatabase();

        DBManager manager217 = new DBManager(db217, this);

        list217 = manager217.read(position217);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db217.close();
        helper217.close();

    }

}
