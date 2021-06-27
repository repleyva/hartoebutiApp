package org.environmentronic.hartoebuti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class InfoDesarrolladorActivity extends AppCompatActivity {

    private String facebookR = "https://www.facebook.com/roosevelt.leyva";
    private ImageView btnFacebookRusvel;
    private String twitterR = "https://twitter.com/repleyva";
    private ImageView btnTwitterRusvel;
    private String instagramR = "https://www.instagram.com/repleyva/?hl=es-la";
    private ImageView btnInstaRusvel;
    private String githubR = "https://github.com/RusvelLeyva";
    private ImageView btnGitRusvel;
    private String linkedinR = "https://www.linkedin.com/in/rusvel-enrique-pasos-leyva-969b9918b/";
    private ImageView btnLinkedinRusvel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_info_desarrollador);

        btnFacebookRusvel = (ImageView) findViewById(R.id.btnFacebookRusvel);
        btnTwitterRusvel = (ImageView) findViewById(R.id.btnTwitterRusvel);
        btnInstaRusvel = (ImageView) findViewById(R.id.btnInstaRusvel);
        btnLinkedinRusvel = (ImageView) findViewById(R.id.btnLinkedinRusvel);
        btnGitRusvel = (ImageView) findViewById(R.id.btnGitRusvel);

        btnFacebookRusvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(facebookR);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });

        btnTwitterRusvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(twitterR);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });

        btnInstaRusvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(instagramR);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });

        btnLinkedinRusvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(linkedinR);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });

        btnGitRusvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse(githubR);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
            }
        });
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    @Override
    public void onBackPressed() {

    }
}