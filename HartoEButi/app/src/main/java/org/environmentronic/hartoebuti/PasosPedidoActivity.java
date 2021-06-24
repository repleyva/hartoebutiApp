package org.environmentronic.hartoebuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class PasosPedidoActivity extends AppCompatActivity {

    private StepView stepView;
    private TextView stepTextView, stepDescriptionTextView;
    private int stepIndex = 0;
    private String[] stepTexts = {"Paso1", "Paso 2", "Paso 3"};
    private String[] stepDescriptionTexts =
            {"¡Ordena pedidos adicionales para agrandar tu compra!",
                    "¡Ingresa tus datos!", "¡Manda tu pedido por WhatsApp y queda HARTO E´BUTI!"};

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

    private ArrayList<Integer> pedidos = new ArrayList();
    private int contadorPaginas = 1;

    private CardView addPlatanoAsado, addSalsa, addPatacon, addChorizo;

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

        //tvEspecificacionPedido.setText(pedido1 + " " + pedido2 + " " + pedido3 + " " + pedido4);

        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();

        //nexyStep();
    }

    public void nexyStep(View view) {
        stepIndex++;
        if (stepIndex < stepTexts.length) {

            if (contadorPaginas == 1) {
                //tvEspecificacionPedido.setVisibility(View.GONE);
                addChorizo.setVisibility(View.GONE);
                addPlatanoAsado.setVisibility(View.GONE);
                addPatacon.setVisibility(View.GONE);
                addSalsa.setVisibility(View.GONE);

                stepTextView.setText(stepTexts[stepIndex]);
                stepDescriptionTextView.setText(stepDescriptionTexts[stepIndex]);
                stepView.go(stepIndex, true);
                contadorPaginas++;
            }

            if (contadorPaginas == 2){

                stepTextView.setText(stepTexts[stepIndex]);
                stepDescriptionTextView.setText(stepDescriptionTexts[stepIndex]);
                stepView.go(stepIndex, true);
                contadorPaginas = 1;
            }

        }
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
        int totalFinal = 0;
        totalAdd = (add1 * (1500)) + (add2 * (500)) + (add3 * (1500)) + (add4 * (1000));
        totalFinal = totalAdd + Integer.parseInt(total);
        tvTotal.setText("$ " + totalFinal);

        // mandaré los nombres por una lista
        pedidos.add(Integer.parseInt(pedido1));
        pedidos.add(Integer.parseInt(pedido2));
        pedidos.add(Integer.parseInt(pedido3));
        pedidos.add(Integer.parseInt(pedido4));
        pedidos.add(add1);
        pedidos.add(add2);
        pedidos.add(add3);
        pedidos.add(add4);
        //tvEspecificacionPedido.setText(pedidos.toString());
    }
}