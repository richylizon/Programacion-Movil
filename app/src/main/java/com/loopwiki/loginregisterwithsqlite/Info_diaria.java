package com.loopwiki.loginregisterwithsqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Info_diaria extends AppCompatActivity {

    TextView text1, text11, text2, text22, text3, text33, text4, text44, text5, text55;
    ImageView contenedor;
    Button btncambiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_diaria);
        text1 = (TextView) findViewById(R.id.textView1);
        text11 = (TextView) findViewById(R.id.textView2);
        text2 = (TextView) findViewById(R.id.textView10);
        // text22=(TextView)findViewById(R.id.textView2);
                       /* text3=(TextView)findViewById(R.id.textView1);
                        text33=(TextView)findViewById(R.id.textView2);
                        text4=(TextView)findViewById(R.id.textView1);
                        text44=(TextView)findViewById(R.id.textView2);
                        text5=(TextView)findViewById(R.id.textView1);
                        text55=(TextView)findViewById(R.id.textView2);*/
        contenedor = (ImageView) findViewById(R.id.contenedor);
        btncambiar = (Button) findViewById(R.id.btncambiar);
        text2.setText("Al estar caminando");
        contenedor.setImageResource(R.drawable.caminar_w1140);
        text1.setText("Sí. Erguida, con la espalda recta, los glúteos duros, la tripa hacia dentro, la cabeza alta y los brazos balanceándose rítmicamente a los lados. ");
        text11.setText("No. Huye de la típica postura de embarazada con la tripa hacia delante y la columna arqueada. En esta posición, la espalda se curva excesivamente y pueden aparecer molestias en esta zona.\n" + "Nuestro consejo: Utiliza zapatos de tacón medio, de unos 3 o 4 cm. Y, si estás en casa, camina descalza o con unos calcetines. Es un buen ejercicio para trabajar los músculos de la planta del pie y mejorar la circulación.  ");
        btncambiar.setOnClickListener(new View.OnClickListener() {
            int c = 0;

            @Override
            public void onClick(View view) {
                for (int i = 0; i <= 4; i++) {
                    if (c % 4 == 0) {
                        text2.setText("Al Agacharse");
                        contenedor.setImageResource(R.drawable.agacharse_w1140);
                        text1.setText("Sí. Flexiona las rodillas y separa ligeramente las piernas, manteniendo la espalda recta. Hazlo lentamente.");
                        text11.setText("No. Evita arquear la espalda hacia delante y mantener las piernas rectas.\n" +
                                "Nuestro consejo: Si no tienes mucho equilibrio, no te “arriesgues”. Apóyate en la pared o en algún soporte hasta llegar al suelo… y hazlo también para levantarte.");
                    } else if (c % 4 == 1) {
                        text2.setText("Al Levantarse");
                        contenedor.setImageResource(R.drawable.levantarse_w1140);
                        text1.setText("No. Si estás tumbada boca arriba, no te levantes frontalmente. Sobrecargarías excesivamente la zona lumbar y aumentarías el riesgo de ciática y dolores de espalda");
                        text11.setText("Sí.Apoya el codo izquierdo y haz fuerza con la mano derecha sobre el colchón hasta que logres incorporarte. Al mismo tiempo, ve liberando las piernas del colchón. Debes quedarte sentada en la cama con las piernas fuera, tocando el suelo, y desde ahí, levantarte..\n" +
                                "Nuestro consejo: No te levantes rápidamente. ");
                    } else if (c % 4 == 2) {
                        text2.setText("Al sentarse");
                        contenedor.setImageResource(R.drawable.sentarse_w1140);
                        text1.setText("Sí. Lleva los glúteos hasta el final del asiento a fin de mantener recta la espalda. Si notas que la parte lumbar no está apoyada, utiliza un almohadón. Coloca las piernas elevadas sobre un escabel o una silla y un cojín.");
                        text11.setText("No. Evita los asientos demasiado blandos en los que te hundas. No favorece la posición de la espalda y, además, te costará más esfuerzo levantarte. Tampoco utilices taburetes sin respaldo e intenta no cruzas las piernas.");
                    } else if (c % 4 == 3) {
                        text2.setText("Al estra en ordenador");
                        contenedor.setImageResource(R.drawable.ordenador_w1140);
                        text1.setText("Sí. Si el tamaño de tu tripa te impide acercarte a la mesa, es preferible que bajes un poco la silla o subas la altura de la mesa, para que puedas “meter” la tripa dentro.");
                        text11.setText("No. Intenta no acercarte a la mesa, curvando la espalda, ni dejes los brazos sin apoyo, en tensión.");
                    } else if (c % 4 == 4) {
                        text2.setText("Al estar en el choche");
                        contenedor.setImageResource(R.drawable.coche_w1140);
                        text1.setText("No. No viajes sin el cinturón de seguridad puesto aún en distancias cortas.");
                        text11.setText("Sí. Para entrar en el coche, siéntate primero con los pies fuera de automóvil y luego, gírate e introduce una pierna después de la otra, haciendo fuerza con tus manos sobre el asiento. Si Notas contracciones, para el coche hasta que se te pasen. Si vas a realizar un viaje largo, descansa cada 100-120 km.");
                    }
                }
                c++;
            }
        });

        //agregar el boton de atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Log.i("ActionBar", "Atrás!");
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
