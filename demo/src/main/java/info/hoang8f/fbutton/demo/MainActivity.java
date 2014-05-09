package info.hoang8f.fbutton.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;

import info.hoang8f.widget.FButton;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private FButton twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twitter = (FButton) findViewById(R.id.f_twitter_button);
        twitter.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_twitter_button:

                //Create color picker view
                View view = this.getLayoutInflater().inflate(R.layout.color_picker_dialog, null);
                if (view == null) return;

                //Config picker
                final ColorPicker picker = (ColorPicker) view.findViewById(R.id.picker);
                SVBar svBar = (SVBar) view.findViewById(R.id.svbar);
                OpacityBar opacityBar = (OpacityBar) view.findViewById(R.id.opacitybar);
                final TextView hexCode = (TextView) view.findViewById(R.id.hex_code);

                picker.addSVBar(svBar);
                picker.addOpacityBar(opacityBar);
                picker.setOldCenterColor(twitter.getButtonColor());
                picker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int intColor) {
                        String hexColor = Integer.toHexString(intColor).toUpperCase();
                        hexCode.setText("#" + hexColor);
                    }
                });

                //Config dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(view);
                builder.setTitle("Choose your color");
                builder.setCancelable(true);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Update color
                        twitter.setButtonColor(picker.getColor());
                    }
                });
                builder.create().show();
                break;
        }
    }
}
