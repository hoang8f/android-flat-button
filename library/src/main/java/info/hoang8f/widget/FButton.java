package info.hoang8f.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import info.hoang8f.lvbutton.R;

/**
 * Created by hoang8f on 5/5/14.
 */

public class FButton extends Button implements View.OnTouchListener {

    private boolean isShadowEnabled = true;
    private int mBackgroundColor;

    public FButton(Context context) {
        super(context);
        this.setOnTouchListener(this);
    }

    public FButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(context, attrs);
        this.setOnTouchListener(this);
    }

    public FButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttrs(context, attrs);
        this.setOnTouchListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //Update background color
        refresh();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isShadowEnabled) {
                    this.setPadding(getResources().getDimensionPixelSize(R.dimen.default_padding_left),
                            getResources().getDimensionPixelSize(R.dimen.default_padding_top),
                            getResources().getDimensionPixelSize(R.dimen.default_padding_right),
                            0);
                } else {
                    setColorFilter(view, 0x6D6D6D);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Rect r = new Rect();
                view.getLocalVisibleRect(r);
                if (!r.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    if (isShadowEnabled) {
                        this.setPadding(getResources().getDimensionPixelSize(R.dimen.default_padding_left),
                                getResources().getDimensionPixelSize(R.dimen.default_shadow_height),
                                getResources().getDimensionPixelSize(R.dimen.default_padding_right),
                                getResources().getDimensionPixelSize(R.dimen.default_shadow_height));
                    } else {
                        setColorFilter(view, null);
                    }
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (isShadowEnabled) {
                    this.setPadding(getResources().getDimensionPixelSize(R.dimen.default_padding_left),
                            getResources().getDimensionPixelSize(R.dimen.default_shadow_height),
                            getResources().getDimensionPixelSize(R.dimen.default_padding_right),
                            getResources().getDimensionPixelSize(R.dimen.default_shadow_height));
                } else {
                    setColorFilter(view, null);
                }
                break;
        }
        return false;
    }

    private void setColorFilter(View v, Integer filter) {
        if (filter == null) {
            v.getBackground().clearColorFilter();
        } else {
            LightingColorFilter darken = new LightingColorFilter(filter, 0x000000);
            v.getBackground().setColorFilter(darken);
        }
        // required on Android 2.3.7 for filter change to take effect (but not on 4.0.4)
        v.getBackground().invalidateSelf();
    }

    public void setShadowEnabled(boolean isShadowEnabled) {
        this.isShadowEnabled = isShadowEnabled;
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FButton);
        if (typedArray == null) return;
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.FButton_shadowEnabled) {
                //Default is true
                isShadowEnabled = typedArray.getBoolean(attr, true);
            }
        }
        typedArray.recycle();
    }

    public void refresh() {
        //Refresh button interface
        //Create drawable from color
        StateListDrawable stateListDrawable = new StateListDrawable();

        int backgroundColor = getResources().getColor(R.color.button_twitter_color);
        int shadowColor = getResources().getColor(R.color.button_twitter_shadow_color);
        int transparent = getResources().getColor(R.color.button_transparent_color);

        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, createDrawable(10, backgroundColor, shadowColor));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, createDrawable(10, transparent, backgroundColor));
        //Set button background
        if (Build.VERSION.SDK_INT >= 16) {
            this.setBackground(stateListDrawable);
        } else {
            this.setBackgroundDrawable(stateListDrawable);
        }
    }

    private LayerDrawable createDrawable(int radius, int topColor, int bottomColor) {

        float[] outerRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        //Top
        RoundRectShape topRoundRect = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable topShapeDrawable = new ShapeDrawable(topRoundRect);
        topShapeDrawable.getPaint().setColor(topColor);
        //Bottom

        RoundRectShape roundRectShape = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
        bottomShapeDrawable.getPaint().setColor(bottomColor);

        Drawable[] drawArray = {bottomShapeDrawable, topShapeDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(drawArray);

        layerDrawable.setLayerInset(0, 0, 10, 0, 0);  /*index, left, top, right, bottom*/
        layerDrawable.setLayerInset(1, 0, 0, 0, 10);  /*index, left, top, right, bottom*/

        return layerDrawable;
    }
}
