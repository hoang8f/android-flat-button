package info.hoang8f.fbutton.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;

import info.hoang8f.widget.FButton;

public class MainActivity extends ActionBarActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private FButton twitterBtn;
    private FButton disabledBtn;
    private TextView shadowHeight;
    private SeekBar shadowHeightBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twitterBtn = (FButton) findViewById(R.id.f_twitter_button);
        disabledBtn = (FButton) findViewById(R.id.disabled_button);
        Button changeColorBtn = (Button) findViewById(R.id.change_color_button);
        ToggleButton shadowSwitch = (ToggleButton) findViewById(R.id.enable_shadow_switch);
        shadowHeightBar = (SeekBar) findViewById(R.id.shadow_height_seekbar);
        shadowHeight = (TextView) findViewById(R.id.shadow_height_value);

        changeColorBtn.setOnClickListener(this);
        shadowSwitch.setOnCheckedChangeListener(this);
        shadowHeightBar.setOnSeekBarChangeListener(this);

        //Config disabled button
        disabledBtn.setButtonColor(getResources().getColor(R.color.fbutton_color_concrete));
        disabledBtn.setShadowEnabled(true);
        disabledBtn.setShadowHeight(5);
        disabledBtn.setCornerRadius(5);
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
        switch (item.getItemId()) {
            case R.id.action_github:
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(Config.GITHUB_URL));
                startActivity(browse);
                return true;
            case R.id.action_social:
                ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(MainActivity.this);
                intentBuilder.setChooserTitle("Choose Share App")
                        .setType("text/plain")
                        .setSubject("Flat button for android")
                        .setText("A flat button library for android #AndroidFlat goo.gl/C6aLDi")
                        .startChooser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_color_button:

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
                picker.setOldCenterColor(twitterBtn.getButtonColor());
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
                        twitterBtn.setButtonColor(picker.getColor());
                    }
                });
                builder.create().show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        twitterBtn.setShadowEnabled(isChecked);
        updateShadowHeight(shadowHeightBar.getProgress());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        shadowHeight.setText(progress + "dp");
        updateShadowHeight(progress);
    }

    private void updateShadowHeight(int height) {
        //Convert from dp to pixel
        int shadowHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        twitterBtn.setShadowHeight(shadowHeight);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
