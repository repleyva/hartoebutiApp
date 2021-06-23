package org.environmentronic.hartoebuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    // boton para pedido
    private ExtendedFloatingActionButton extendedFloatingCarrito;
    // boton para info
    private ExtendedFloatingActionButton addFloatingInfo;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private Boolean isAllFabsVisible;
    // linears cantidad de pedidos
    private LinearLayout linearNumPedido1;
    private LinearLayout linearNumPedido2;
    private LinearLayout linearNumPedido3;
    private LinearLayout linearNumPedido4;
    // targetas de pedidos
    private CardView targeta1;
    private CardView targeta2;
    private CardView targeta3;
    private CardView targeta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);

        // casting
        extendedFloatingCarrito = (ExtendedFloatingActionButton) findViewById(R.id.extendedFloatingCarrito);
        extendedFloatingActionButton = (ExtendedFloatingActionButton) findViewById(R.id.extendedFloatingActionButton);
        addFloatingInfo = (ExtendedFloatingActionButton) findViewById(R.id.floatingInfo);
        linearNumPedido1 = (LinearLayout) findViewById(R.id.linearNumPedido1);
        linearNumPedido2 = (LinearLayout) findViewById(R.id.linearNumPedido2);
        linearNumPedido3 = (LinearLayout) findViewById(R.id.linearNumPedido3);
        linearNumPedido4 = (LinearLayout) findViewById(R.id.linearNumPedido4);
        targeta1 = (CardView) findViewById(R.id.targeta1);
        targeta2 = (CardView) findViewById(R.id.targeta2);
        targeta3 = (CardView) findViewById(R.id.targeta3);
        targeta4 = (CardView) findViewById(R.id.targeta4);

        // ocultamos los linears de pedidos
        linearNumPedido1.setVisibility(View.GONE);
        linearNumPedido2.setVisibility(View.GONE);
        linearNumPedido3.setVisibility(View.GONE);
        linearNumPedido4.setVisibility(View.GONE);

        // ocultamos el boton de info
        addFloatingInfo.setVisibility(View.GONE);
        isAllFabsVisible = false;

        // inicializo los botones de add y less en gone
        extendedFloatingActionButton.shrink();
        extendedFloatingCarrito.shrink();

        // click al mas
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

        // click a la info
        addFloatingInfo.setOnClickListener(
                view -> Toast.makeText
                        (MainActivity
                                        .this, "Información del desarrollador",
                                Toast.LENGTH_SHORT).show());

        // click al carrito
        extendedFloatingCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extendedFloatingCarrito.extend();
            }
        });

        // ponemos a la excucha todas las tarjetas
        clickCard();
    }

    private void clickCard() {

        targeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido1.setVisibility(View.VISIBLE);
            }
        });

        targeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido2.setVisibility(View.VISIBLE);
            }
        });

        targeta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido3.setVisibility(View.VISIBLE);
            }
        });

        targeta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido4.setVisibility(View.VISIBLE);
            }
        });
    }
}