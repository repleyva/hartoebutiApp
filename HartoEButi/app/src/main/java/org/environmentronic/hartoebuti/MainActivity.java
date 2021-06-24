package org.environmentronic.hartoebuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

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
    private CircleImageView targeta1;
    private CircleImageView targeta2;
    private CircleImageView targeta3;
    private CircleImageView targeta4;
    // botones para incrementar pedido
    private ImageView btnAdd1;
    private ImageView btnAdd2;
    private ImageView btnAdd3;
    private ImageView btnAdd4;
    // botones para decrementar pedido
    private ImageView btnLess1;
    private ImageView btnLess2;
    private ImageView btnLess3;
    private ImageView btnLess4;
    private ImageView btnLess1Delete;
    private ImageView btnLess2Delete;
    private ImageView btnLess3Delete;
    private ImageView btnLess4Delete;
    // textview de la cantidad de pedidos
    private TextView numPedido1;
    private TextView numPedido2;
    private TextView numPedido3;
    private TextView numPedido4;
    // numeros de pedidos
    private Integer pedido1 = 0;
    private Integer pedido2 = 0;
    private Integer pedido3 = 0;
    private Integer pedido4 = 0;
    // total de compra en el carrito
    private Integer tot = 0;

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
        targeta1 = (CircleImageView) findViewById(R.id.targeta1);
        targeta2 = (CircleImageView) findViewById(R.id.targeta2);
        targeta3 = (CircleImageView) findViewById(R.id.targeta3);
        targeta4 = (CircleImageView) findViewById(R.id.targeta4);
        btnAdd1 = (ImageView) findViewById(R.id.btnAdd1);
        btnAdd2 = (ImageView) findViewById(R.id.btnAdd2);
        btnAdd3 = (ImageView) findViewById(R.id.btnAdd3);
        btnAdd4 = (ImageView) findViewById(R.id.btnAdd4);
        btnLess1 = (ImageView) findViewById(R.id.btnLess1);
        btnLess2 = (ImageView) findViewById(R.id.btnLess2);
        btnLess3 = (ImageView) findViewById(R.id.btnLess3);
        btnLess4 = (ImageView) findViewById(R.id.btnLess4);
        btnLess1Delete = (ImageView) findViewById(R.id.btnLess1delete);
        btnLess2Delete = (ImageView) findViewById(R.id.btnLess2delete);
        btnLess3Delete = (ImageView) findViewById(R.id.btnLess3delete);
        btnLess4Delete = (ImageView) findViewById(R.id.btnLess4delete);
        numPedido1 = (TextView) findViewById(R.id.numPedido1);
        numPedido2 = (TextView) findViewById(R.id.numPedido2);
        numPedido3 = (TextView) findViewById(R.id.numPedido3);
        numPedido4 = (TextView) findViewById(R.id.numPedido4);

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

                if (!extendedFloatingCarrito.isExtended()) {
                    extendedFloatingCarrito.extend();
                } else {
                    if (tot == 0) {
                        extendedFloatingCarrito.shrink();
                    } else {
                        Toast.makeText(MainActivity.this, "Tu pedido es de: " + tot.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // ponemos a la excucha todas las tarjetas
        clickCard();

    }

    public void clickCard() {

        targeta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido1.setVisibility(View.VISIBLE);
                pedido1 = 1;
                if (pedido1 == 1) {
                    btnLess1.setVisibility(View.GONE);
                    btnLess1Delete.setVisibility(View.VISIBLE);
                }
                numPedido1.setText(pedido1.toString());
                extendedFloatingCarrito.extend();
                setTotal();
            }
        });

        targeta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido2.setVisibility(View.VISIBLE);
                pedido2 = 1;
                if (pedido2 == 1) {
                    btnLess2.setVisibility(View.GONE);
                    btnLess2Delete.setVisibility(View.VISIBLE);
                }
                numPedido2.setText(pedido2.toString());
                extendedFloatingCarrito.extend();
                setTotal();
            }
        });

        targeta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido3.setVisibility(View.VISIBLE);
                pedido3 = 1;
                if (pedido3 == 1) {
                    btnLess3.setVisibility(View.GONE);
                    btnLess3Delete.setVisibility(View.VISIBLE);
                }
                numPedido3.setText(pedido3.toString());
                extendedFloatingCarrito.extend();
                setTotal();
            }
        });

        targeta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearNumPedido4.setVisibility(View.VISIBLE);
                pedido4 = 1;
                if (pedido4 == 1) {
                    btnLess4.setVisibility(View.GONE);
                    btnLess4Delete.setVisibility(View.VISIBLE);
                }
                numPedido4.setText(pedido4.toString());
                extendedFloatingCarrito.extend();
                setTotal();
            }
        });
    }

    public void incrementar(View view) {

        if (view.getId() == btnAdd1.getId()) {
            pedido1 = pedido1 + 1;
            if (pedido1 > 1) {
                btnLess1Delete.setVisibility(View.GONE);
                btnLess1.setVisibility(View.VISIBLE);
            }
            numPedido1.setText(pedido1.toString());
        }

        if (view.getId() == btnAdd2.getId()) {
            pedido2 = pedido2 + 1;
            if (pedido2 > 1) {
                btnLess2Delete.setVisibility(View.GONE);
                btnLess2.setVisibility(View.VISIBLE);
            }
            numPedido2.setText(pedido2.toString());
        }

        if (view.getId() == btnAdd3.getId()) {
            pedido3 = pedido3 + 1;
            if (pedido3 > 1) {
                btnLess3Delete.setVisibility(View.GONE);
                btnLess3.setVisibility(View.VISIBLE);
            }
            numPedido3.setText(pedido3.toString());
        }

        if (view.getId() == btnAdd4.getId()) {
            pedido4 = pedido4 + 1;
            if (pedido4 > 1) {
                btnLess4Delete.setVisibility(View.GONE);
                btnLess4.setVisibility(View.VISIBLE);
            }
            numPedido4.setText(pedido4.toString());
        }

        setTotal();
    }

    public void decrementar(View view) {

        if (view.getId() == btnLess1.getId()) {
            if (Integer.parseInt(numPedido1.getText().toString()) > 1) {
                pedido1 = pedido1 - 1;
                numPedido1.setText(pedido1.toString());
            }
            if (pedido1 == 1) {
                btnLess1.setVisibility(View.GONE);
                btnLess1Delete.setVisibility(View.VISIBLE);
            }
        }

        if (view.getId() == btnLess2.getId()) {
            if (Integer.parseInt(numPedido2.getText().toString()) > 1) {
                pedido2 = pedido2 - 1;
                numPedido2.setText(pedido2.toString());
            }
            if (pedido2 == 1) {
                btnLess2.setVisibility(View.GONE);
                btnLess2Delete.setVisibility(View.VISIBLE);
            }
        }

        if (view.getId() == btnLess3.getId()) {
            if (Integer.parseInt(numPedido3.getText().toString()) > 1) {
                pedido3 = pedido3 - 1;
                numPedido3.setText(pedido3.toString());
            }
            if (pedido3 == 1) {
                btnLess3.setVisibility(View.GONE);
                btnLess3Delete.setVisibility(View.VISIBLE);
            }
        }

        if (view.getId() == btnLess4.getId()) {
            if (Integer.parseInt(numPedido4.getText().toString()) > 1) {
                pedido4 = pedido4 - 1;
                numPedido4.setText(pedido4.toString());
            }
            if (pedido4 == 1) {
                btnLess4.setVisibility(View.GONE);
                btnLess4Delete.setVisibility(View.VISIBLE);
            }
        }

        setTotal();
    }

    public void eliminarPedido(View view) {

        if (view.getId() == btnLess1Delete.getId()) {
            linearNumPedido1.setVisibility(View.GONE);
            pedido1 = 0;
        }

        if (view.getId() == btnLess2Delete.getId()) {
            linearNumPedido2.setVisibility(View.GONE);
            pedido2 = 0;
        }

        if (view.getId() == btnLess3Delete.getId()) {
            linearNumPedido3.setVisibility(View.GONE);
            pedido3 = 0;
        }

        if (view.getId() == btnLess4Delete.getId()) {
            linearNumPedido4.setVisibility(View.GONE);
            pedido4 = 0;
        }

        setTotal();
    }

    private void setTotal() {
        String total = "";
        tot = (pedido1 * (6000)) + (pedido2 * (8000)) + (pedido3 * (13000)) + (pedido4 * (22000));
        if (tot == 0) {
            extendedFloatingCarrito.shrink();
        }
        total = "(Hacer pedido) Total: " + tot;
        extendedFloatingCarrito.setText(total);
    }

}