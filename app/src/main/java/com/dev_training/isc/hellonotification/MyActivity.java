package com.dev_training.isc.hellonotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);

            rootView.findViewById(R.id.notifyButton).setOnClickListener(this);
            return rootView;
        }

        @Override
        public void onClick(View view) {
            // Notify
            Toast.makeText(getActivity(), "Notify!", Toast.LENGTH_LONG).show();
            //
            Intent intent = new Intent(getActivity(), MyActivity.class);
            PendingIntent contentIntent
                    = PendingIntent.getActivity(
                        getActivity(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

            // Notificationの指定
            Notification notification = new Notification.Builder(getActivity())
                    .setContentTitle("Hello Notify")
                    .setContentText("通知テストです")
                    .setContentInfo("通知情報")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(
                            getActivity().getResources(), R.drawable.ic_launcher))
                    .setTicker("ここがTickerです")
                    .setContentIntent(contentIntent) //タップすると起動するアクティビティの指定
                    .getNotification();

            // Notification Managerの取得
            NotificationManager manager =
                    (NotificationManager)getActivity().
                            getSystemService(Activity.NOTIFICATION_SERVICE);
            // 100msec後に通知する
            manager.notify(100, notification);
        }
    }
}
