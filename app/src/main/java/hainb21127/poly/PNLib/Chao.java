package hainb21127.poly.PNLib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Chao extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao);
        img = findViewById(R.id.imgLogo);

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Chao.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Chao.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}