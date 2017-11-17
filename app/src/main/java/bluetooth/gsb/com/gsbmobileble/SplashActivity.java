package bluetooth.gsb.com.gsbmobileble;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;
    private long delay_time  = 3000;
    private long time = 3000L;
    private String TAG = "Splash screen";
    private ImageView img;
    private String url = "http://www.imoneythailand.com/sites/default/files/xGSB_Logo.png.pagespeed.ic.UKdZwaMjMq.png";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);
        getSupportActionBar().hide();
        init();
    }

    private void init(){
        try{
            img = findViewById(R.id.imgSplesh);
            Picasso.with(getApplicationContext()).load(url).into(img);
            getSupportActionBar().hide();
            handler = new Handler();

            runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainApps.class);
                    startActivity(intent);
                    finish();
                }
            };

        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            delay_time = time;
            handler.postDelayed(runnable,delay_time);
            time = System.currentTimeMillis();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            handler.removeCallbacks(runnable);
            time = delay_time - (System.currentTimeMillis() - time);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            handler.removeCallbacks(runnable);
            time = delay_time - (System.currentTimeMillis() - time);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

}
