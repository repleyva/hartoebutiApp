package org.environmentronic.hartoebuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PasosPedidoActivity extends AppCompatActivity {

    private StepView stepView;
    private TextView stepTextView, stepDescriptionTextView;
    private int stepIndex = 0;
    private String[] stepTexts = {"Paso1", "Paso 2", "Paso 3"};
    private String[] stepDescriptionTexts =
            {"¡Ordena pedidos adicionales para agrandar tu compra!",
                    "¡Ingresa tus datos!", "¡Verifica tu compra y manda tu pedido por WhatsApp!\n¡Queda HARTO E´BUTI!"};

    private TextView tvTotal;
    private String total;
    private Integer totalAdd;

    // botones para incrementar pedido
    private ImageView btnAdd1add;
    private ImageView btnAdd2add;
    private ImageView btnAdd3add;
    private ImageView btnAdd4add;
    // botones para decrementar pedido
    private ImageView btnLess1add;
    private ImageView btnLess2add;
    private ImageView btnLess3add;
    private ImageView btnLess4add;
    // textview de la cantidad de pedidos
    private TextView numAdd1;
    private TextView numAdd2;
    private TextView numAdd3;
    private TextView numAdd4;
    private TextView tvEspecificacionPedido;

    private Integer add1 = 0;
    private Integer add2 = 0;
    private Integer add3 = 0;
    private Integer add4 = 0;

    private String pedido1 = "";
    private String pedido2 = "";
    private String pedido3 = "";
    private String pedido4 = "";

    private Map<String, Integer> pedidos = new HashMap<>();
    private int contadorPaginas = 0;

    private CardView addPlatanoAsado, addSalsa, addPatacon, addChorizo;

    private CardView datos;
    private LinearLayout lineaAdicional;

    private TextInputEditText nombreUsuario;
    private TextInputEditText direccionUsuario;
    private TextInputEditText cambioBilleteTx;
    private TextInputLayout nombreUsuarioLy;
    private TextInputLayout direccionUsuarioLy;
    private TextInputLayout billeteCambio;
    private RadioButton efectivo, bancolombia, efectivoSi, efectivoNo;
    private ExtendedFloatingActionButton btnContinuarMetodoPago, btnContinuarMetodoEfectivo;

    private LinearLayout metodoBancolombia;
    private LinearLayout metodoEfectivo;
    private ExtendedFloatingActionButton floatingSiguiente;
    private RadioGroup metodoPagoGroup;
    private Boolean necesitaCambio = false;
    private Boolean pasar = true;

    private TextView pedidoFinalEspecificadoTv;
    private TextView datosNombres;
    private CardView pedidoFinalCard;

    private boolean bc = false;
    private boolean ef = false;
    private String cadenaPedido = "";
    private Integer cambio;
    private Integer totalFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_pasos_pedido);

        total = getIntent().getStringExtra("total");
        pedido1 = getIntent().getStringExtra("pedido1");
        pedido2 = getIntent().getStringExtra("pedido2");
        pedido3 = getIntent().getStringExtra("pedido3");
        pedido4 = getIntent().getStringExtra("pedido4");

        // casting
        stepView = findViewById(R.id.stepView);
        stepTextView = findViewById(R.id.stepTextView);
        stepDescriptionTextView = findViewById(R.id.stepDescriptionTextView);
        tvTotal = findViewById(R.id.tvTotal);

        tvTotal.setText(total);

        btnAdd1add = (ImageView) findViewById(R.id.btnAdd1add);
        btnAdd2add = (ImageView) findViewById(R.id.btnAdd2add);
        btnAdd3add = (ImageView) findViewById(R.id.btnAdd3add);
        btnAdd4add = (ImageView) findViewById(R.id.btnAdd4add);
        btnLess1add = (ImageView) findViewById(R.id.btnLess1add);
        btnLess2add = (ImageView) findViewById(R.id.btnLess2add);
        btnLess3add = (ImageView) findViewById(R.id.btnLess3add);
        btnLess4add = (ImageView) findViewById(R.id.btnLess4add);
        numAdd1 = (TextView) findViewById(R.id.numAdd1);
        numAdd2 = (TextView) findViewById(R.id.numAdd2);
        numAdd3 = (TextView) findViewById(R.id.numAdd3);
        numAdd4 = (TextView) findViewById(R.id.numAdd4);
        tvEspecificacionPedido = (TextView) findViewById(R.id.tvEspecificacionPedido);

        addPlatanoAsado = findViewById(R.id.addPlatanoAsado);
        addSalsa = findViewById(R.id.addSalsa);
        addPatacon = findViewById(R.id.addPatacon);
        addChorizo = findViewById(R.id.addChorizo);
        datos = findViewById(R.id.datos);
        lineaAdicional = findViewById(R.id.lineaAdicional);

        direccionUsuario = findViewById(R.id.direccionUsuario);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        billeteCambio = findViewById(R.id.billeteCambio);
        efectivo = findViewById(R.id.efectivo);
        bancolombia = findViewById(R.id.bancolombia);
        efectivoSi = findViewById(R.id.efectivoSi);
        efectivoNo = findViewById(R.id.efectivoNo);
        btnContinuarMetodoPago = findViewById(R.id.btnContinuarMetodoPago);
        btnContinuarMetodoEfectivo = findViewById(R.id.btnContinuarMetodoEfectivo);
        metodoBancolombia = findViewById(R.id.metodoBancolombia);
        metodoEfectivo = findViewById(R.id.metodoEfectivo);
        floatingSiguiente = findViewById(R.id.floatingSiguiente);
        nombreUsuarioLy = findViewById(R.id.nombreUsuarioLy);
        direccionUsuarioLy = findViewById(R.id.direccionUsuarioLy);
        metodoPagoGroup = findViewById(R.id.metodoPagoGroup);
        cambioBilleteTx = findViewById(R.id.cambioBilleteTx);
        pedidoFinalEspecificadoTv = findViewById(R.id.pedidoFinalEspecificadoTv);
        datosNombres = findViewById(R.id.datosNombres);
        pedidoFinalCard = findViewById(R.id.pedidoFinalCard);

        //tvEspecificacionPedido.setText(pedido1 + " " + pedido2 + " " + pedido3 + " " + pedido4);

        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();

        //nexyStep();
    }

    public void nexyStep(View view) {
        if (pasar) {
            stepIndex++;
            contadorPaginas = stepIndex;
        }

        if (stepIndex < stepTexts.length) {
            if (contadorPaginas == 1) {
                addChorizo.setVisibility(View.GONE);
                addPlatanoAsado.setVisibility(View.GONE);
                addPatacon.setVisibility(View.GONE);
                addSalsa.setVisibility(View.GONE);
                datos.setVisibility(View.VISIBLE);
                lineaAdicional.setVisibility(View.GONE);
                stepTextView.setText(stepTexts[stepIndex]);
                stepDescriptionTextView.setText(stepDescriptionTexts[stepIndex]);
                stepView.go(stepIndex, true);
            }
            if (contadorPaginas == 2) {
                if ((!nombreUsuario.getText().toString().trim().isEmpty()) & (!direccionUsuario.getText().toString().trim().isEmpty())) {
                    if (necesitaCambio & cambioBilleteTx.getText().toString().trim().isEmpty()) {
                        pasar = false;
                        billeteCambio.setError("Debe ingresar el valor del Billete a cambiar");
                    } else {
                        if (!(bancolombia.isChecked() || efectivo.isChecked())) {
                            pasar = false;
                            Toast.makeText(this, "Debe seleccionar un metodo de pago", Toast.LENGTH_SHORT).show();
                        } else {
                            pasar = true;
                            datos.setVisibility(View.GONE);
                            pedidoFinalCard.setVisibility(View.VISIBLE);
                            stepTextView.setText(stepTexts[stepIndex]);
                            stepDescriptionTextView.setText(stepDescriptionTexts[stepIndex]);
                            stepView.go(stepIndex, true);
                            floatingSiguiente.setText("¡Hacer pedido por WhatsApp!");
                            ordenFinal();
                        }
                    }

                } else {
                    pasar = false;
                    if (nombreUsuario.getText().toString().trim().isEmpty()) {
                        nombreUsuarioLy.setError("Debe ingresar su nombre");
                    }
                    if (direccionUsuario.getText().toString().trim().isEmpty()) {
                        direccionUsuarioLy.setError("Debe ingresar la dirección");
                    }
                    if (!(bancolombia.isChecked() || efectivo.isChecked())) {
                        Toast.makeText(this, "Debe seleccionar un metodo de pago", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else if (contadorPaginas == 3) {
            String mensaje = "";
            if (bc) {
                mensaje = "\uD83D\uDD90️ *¡Hola! te hablo desde HartoE'ButiApp.*\n\n*\uD83E\uDDCD Nombre:* " + nombreUsuario.getText().toString() + "\n\uD83D\uDCCD *Dirección:* " + direccionUsuario.getText().toString() + "\n\uD83D\uDCB3 *Método de pago:* Transferencia por Bancolombia Ahorro a la mano" + "\n\n\uD83D\uDCDD Pedido: \n" + cadenaPedido + "\n\uD83D\uDCB8 *Total a pagar: " + tvTotal.getText().toString() + "*";
            }

            if (ef) {
                if (necesitaCambio) {
                    mensaje = "\uD83D\uDD90️ *¡Hola! te hablo desde HartoE'ButiApp.*\n\n*\uD83E\uDDCD Nombre:* " + nombreUsuario.getText().toString() + "\n\uD83D\uDCCD *Dirección:* " + direccionUsuario.getText().toString() + "\n\uD83D\uDCB3 *Método de pago:* En efectivo con cambio de " + cambio + "\n\n\uD83D\uDCDD Pedido: \n" + cadenaPedido + "\n\uD83D\uDCB8 *Total a pagar: " + tvTotal.getText().toString() + "*";
                } else {
                    mensaje = "\uD83D\uDD90️ *¡Hola! te hablo desde HartoE'ButiApp.*\n\n*\uD83E\uDDCD Nombre:* " + nombreUsuario.getText().toString() + "\n\uD83D\uDCCD *Dirección:* " + direccionUsuario.getText().toString() + "\n\uD83D\uDCB3 *Método de pago:* En efectivo, no necesito cambio" + "\n\n\uD83D\uDCDD Pedido: \n" + cadenaPedido + "\n\uD83D\uDCB8 *Total a pagar: " + tvTotal.getText().toString() + "*";
                }
            }

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_VIEW);
            String uri = "whatsapp://send?phone=+573158160633&text=" + mensaje;
            sendIntent.setData(Uri.parse(uri));
            startActivity(sendIntent);
            finish();
        }
    }

    private void ordenFinal() {
        Integer bancolombia = pedidos.get("bancolombia");
        Integer efectivo = pedidos.get("efectivo");
        Integer pedido_1 = Integer.parseInt(pedido1);
        Integer pedido_2 = Integer.parseInt(pedido2);
        Integer pedido_3 = Integer.parseInt(pedido3);
        Integer pedido_4 = Integer.parseInt(pedido4);

        if (bancolombia == 1) {
            bc = true;
            datosNombres.setText("Nombre: " + nombreUsuario.getText().toString() + "\nDirección: " + direccionUsuario.getText().toString() + "\nMétodo de Pago: Consignación por Bancolombia");
        }

        if (efectivo == 1) {
            ef = true;
            if (necesitaCambio) {
                Integer billete = Integer.parseInt(cambioBilleteTx.getText().toString());
                Integer cuenta = Integer.parseInt(tvTotal.getText().toString());
                cambio = billete - cuenta;
                datosNombres.setText("Nombre: " + nombreUsuario.getText().toString() + "\nDirección: " + direccionUsuario.getText().toString() + "\nMétodo de Pago: En efectivo con vueltos de " + cambio.toString());
            } else {
                datosNombres.setText("Nombre: " + nombreUsuario.getText().toString() + "\nDirección: " + direccionUsuario.getText().toString() + "\nMétodo de Pago: En efectivo sin vueltos");
            }
        }

        if (pedido_1 > 0) {
            cadenaPedido = "* " + pedido_1 + " x Cógela Suave\n";
        }

        if (pedido_2 > 0) {
            cadenaPedido = cadenaPedido + "* " + pedido_2 + " x Visajoso\n";
        }

        if (pedido_3 > 0) {
            cadenaPedido = cadenaPedido + "* " + pedido_3 + " x Espeluque\n";
        }

        if (pedido_4 > 0) {
            cadenaPedido = cadenaPedido + "* " + pedido_4 + " x Barrejobo\n";
        }

        if (add1 > 0) {
            cadenaPedido = cadenaPedido + "* " + add1 + " x Platano Amarillo Asado\n";
        }

        if (add2 > 0) {
            cadenaPedido = cadenaPedido + "* " + add2 + " x Salsa Picante\n";
        }

        if (add3 > 0) {
            cadenaPedido = cadenaPedido + "* " + add3 + " x Patacones\n";
        }

        if (add4 > 0) {
            cadenaPedido = cadenaPedido + "* " + add4 + " x Chorizo\n";
        }

        pedidoFinalEspecificadoTv.setText(cadenaPedido);

    }

    @Override
    public void onBackPressed() {

    }

    public void goToMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    public void incrementar(View view) {

        if (view.getId() == btnAdd1add.getId()) {
            add1 = add1 + 1;
            numAdd1.setText(add1.toString());
        }

        if (view.getId() == btnAdd2add.getId()) {
            add2 = add2 + 1;
            numAdd2.setText(add2.toString());
        }

        if (view.getId() == btnAdd3add.getId()) {
            add3 = add3 + 1;
            numAdd3.setText(add3.toString());
        }

        if (view.getId() == btnAdd4add.getId()) {
            add4 = add4 + 1;
            numAdd4.setText(add4.toString());
        }

        setTotal();
    }

    public void decrementar(View view) {

        if (view.getId() == btnLess1add.getId()) {
            if (Integer.parseInt(numAdd1.getText().toString()) > 0) {
                add1 = add1 - 1;
                numAdd1.setText(add1.toString());
            }
        }

        if (view.getId() == btnLess2add.getId()) {
            if (Integer.parseInt(numAdd2.getText().toString()) > 0) {
                add2 = add2 - 1;
                numAdd2.setText(add2.toString());
            }
        }

        if (view.getId() == btnLess3add.getId()) {
            if (Integer.parseInt(numAdd3.getText().toString()) > 0) {
                add3 = add3 - 1;
                numAdd3.setText(add3.toString());
            }
        }

        if (view.getId() == btnLess4add.getId()) {
            if (Integer.parseInt(numAdd4.getText().toString()) > 0) {
                add4 = add4 - 1;
                numAdd4.setText(add4.toString());
            }
        }

        setTotal();
    }

    private void setTotal() {
        pedidos.clear();
        totalFinal = 0;
        totalAdd = (add1 * (1500)) + (add2 * (500)) + (add3 * (1500)) + (add4 * (1000));
        totalFinal = totalAdd + Integer.parseInt(total);
        tvTotal.setText(totalFinal.toString());

        // mandaré los nombres por una lista
        pedidos.put("pedido1", Integer.parseInt(pedido1)); // pedido 1
        pedidos.put("pedido2", Integer.parseInt(pedido2)); // pedido 2
        pedidos.put("pedido3", Integer.parseInt(pedido3)); // pedido 3
        pedidos.put("pedido4", Integer.parseInt(pedido4)); // pedido 4
        pedidos.put("adicional1", add1); // adicional 1
        pedidos.put("adicional2", add2); // adicional 2
        pedidos.put("adicional3", add3); // adicional 3
        pedidos.put("adicional4", add4); // adicional 4

    }

    public void setBtnContinuarMetodoPago(View view) {
        if (efectivo.isChecked() || bancolombia.isChecked()) {

            if (bancolombia.isChecked()) {
                pedidos.put("bancolombia", 1); // bancolombia true
                pedidos.put("efectivo", 0); // efectivo false
                bancolombia.setClickable(false);
                efectivo.setClickable(false);
                metodoBancolombia.setVisibility(View.VISIBLE);
                btnContinuarMetodoPago.setVisibility(View.GONE);
            }

            if (efectivo.isChecked()) {
                pedidos.put("bancolombia", 0); // bancolombia false
                pedidos.put("efectivo", 1); // efectivo true
                bancolombia.setClickable(false);
                efectivo.setClickable(false);
                metodoEfectivo.setVisibility(View.VISIBLE);
                btnContinuarMetodoPago.setVisibility(View.GONE);
            }
        }
    }

    public void setBtnContinuarEfectivo(View view) {
        if (efectivoSi.isChecked() || efectivoNo.isChecked()) {
            if (efectivoSi.isChecked()) {
                efectivoSi.setClickable(false);
                efectivoNo.setClickable(false);
                necesitaCambio = true;
                btnContinuarMetodoEfectivo.setVisibility(View.GONE);
                billeteCambio.setVisibility(View.VISIBLE);
            }

            if (efectivoNo.isChecked()) {
                efectivoSi.setClickable(false);
                efectivoNo.setClickable(false);
                btnContinuarMetodoEfectivo.setText("Click en siguiente");
            }
        }
    }
}