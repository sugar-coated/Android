package coursework.luyu.no217.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;

import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import coursework.luyu.no217.R;

import static android.os.Build.ID;

public class ShowEventService extends Service {

    private String title217;
    private String content217;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        title217 = intent.getStringExtra("title");
        content217 = intent.getStringExtra("content");

        show_focus(title217,content217);

        return super.onStartCommand(intent, flags, startId);
    }

    private void show_focus(String title217, String content217){
        //判断当前sdk版本是否为26以上
        if(Build.VERSION.SDK_INT>=26){

            NotificationManager manager217 = (NotificationManager)getSystemService (NOTIFICATION_SERVICE);
            NotificationChannel channel217 = new NotificationChannel (ID,"tuoge",NotificationManager.IMPORTANCE_HIGH);
            manager217.createNotificationChannel (channel217);
            Notification notification217 = new Notification.Builder (this,ID)
                    .setContentTitle (this.title217)
                    .setSmallIcon (R.mipmap.focus)
                    .setContentText(this.content217)
                    //.setStyle(new NotificationCompat.BigTextStyle().bigText(content217))
                    .setLargeIcon (BitmapFactory.decodeResource (getResources (),R.mipmap.ic_launcher))
                    .build ();
            startForeground (1,notification217);
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
