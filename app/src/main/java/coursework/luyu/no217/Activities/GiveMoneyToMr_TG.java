package coursework.luyu.no217.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.R;

public class GiveMoneyToMr_TG extends AppCompatActivity {

    @BindView(R.id.backTo2)
    Toolbar backTo2217;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_money_to_mr__tg);
        ButterKnife.bind(this);
        //设置toolBar同时去除标题
        setSupportActionBar(backTo2217);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.to_add);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:

                finish();

                break;
        }
        return true;
    }
}
