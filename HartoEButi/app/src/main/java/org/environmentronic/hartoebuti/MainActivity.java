package org.environmentronic.hartoebuti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton addFloatingInfo;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);

        // casting
        extendedFloatingActionButton = (ExtendedFloatingActionButton) findViewById(R.id.extendedFloatingActionButton);
        addFloatingInfo = (ExtendedFloatingActionButton) findViewById(R.id.floatingInfo);
        // ocultamos el boton de info
        addFloatingInfo.setVisibility(View.GONE);
        isAllFabsVisible = false;

        extendedFloatingActionButton.shrink();

        extendedFloatingActionButton.setOnClickListener(v -> {
            if (!isAllFabsVisible) {
                addFloatingInfo.show();
                extendedFloatingActionButton.extend();
                isAllFabsVisible = true;
            } else {
                addFloatingInfo.hide();
                extendedFloatingActionButton.shrink();
                isAllFabsVisible = false;
            }
        });

        addFloatingInfo.setOnClickListener(
                view -> Toast.makeText
                        (MainActivity
                                        .this, "Información del desarrollador",
                                Toast.LENGTH_SHORT).show());

    }
}