package cz.ruzicka.led;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Color> barvy = new ArrayList<Color>();

    Color lastColor = Color.valueOf(0,0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barvy.add(Color.valueOf(1, 0, 0));
        barvy.add(Color.valueOf(0, 1, 0));
        barvy.add(Color.valueOf(0, 0, 1));
        barvy.add(Color.valueOf(1, 1, 0));
        barvy.add(Color.valueOf(1, 0, 1));
        barvy.add(Color.valueOf(0, 1, 1));
        barvy.add(Color.valueOf(1, 1, 1));

        barvy.add(Color.valueOf(1, 0.5F, 0)); // oranz
        barvy.add(Color.valueOf(0.5F, 0, 1)); // fialova
        barvy.add(Color.valueOf(0, 0, 0)); // cerna
        //barvy.add(Color.valueOf(139/255F, 69/255F,19/255F)); // hneda

        View.OnClickListener clickListener = new View.OnClickListener(){
            public void onClick(View view){
                //Toast.makeText(MainActivity.this, "pizda", Toast.LENGTH_LONG).show();
                Color color = Color.valueOf(view.getBackgroundTintList().getDefaultColor());
                lastColor = color;
                int r = (int) (255 * lastColor.red());
                int g = (int) (255 * lastColor.green());
                int b = (int) (255 * lastColor.blue());
                String url = "http://10.0.0.135/ledBarva?r="+ r +"&g="+ g +"&b="+ b;
                new HttpReqTask().execute(url);
            }
        };

        GridLayout gl = findViewById(R.id.gl);
        gl.setColumnCount(3);
        gl.setRowCount(3);

        for (Color color: barvy) {
            Button but = new Button(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0F);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1.0F);
            //params.columnSpec = GridLayout.InvokeSpec(GridLayout.Undefined, 1f);

            but.setLayoutParams(params);
            but.setBackgroundTintList(ColorStateList.valueOf(color.toArgb()));
            but.setOnClickListener(clickListener);
            gl.addView(but);
        }
    }

    public void ClickModeRotaceL(View view){
        new HttpReqTask().execute("http://10.0.0.135/ledRotaceL");
    }
    public void ClickModeRotaceR(View view){
        new HttpReqTask().execute("http://10.0.0.135/ledRotaceR");
    }
    public void ClickModeAll(View view){
        new HttpReqTask().execute("http://10.0.0.135/ledAll");
    }

    public void ClickModePomalu(View view) {
        new HttpReqTask().execute("http://10.0.0.135/pomalu");
    }

    public void ClickModeRychle(View view) {
        new HttpReqTask().execute("http://10.0.0.135/rychle");
    }

    private void SendJas(int aValueProcent){
        String url = "http://10.0.0.135/jas?value="+ aValueProcent;
        new HttpReqTask().execute(url);
    }

    public void ClickJasMalo(View view) {
        SendJas(30);
    }
    public void ClickJasHodne(View view) {
        SendJas(90);
    }

    public void ClickModeOne(View view) {
        new HttpReqTask().execute("http://10.0.0.135/led");
    }
}