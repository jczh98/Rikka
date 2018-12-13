package top.rechinx.rikka.theme.widgets;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.google.android.material.button.MaterialButton;

import top.rechinx.rikka.theme.utils.TintManager;

public class TintMaterialButton extends MaterialButton implements Tintable, AppCompatBackgroundHelper.BackgroundExtensible {

    private AppCompatBackgroundHelper mBackgroundHelper;

    public TintMaterialButton(Context context) {
        this(context, null);
    }

    public TintMaterialButton(Context context, AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.materialButtonStyle);
    }

    public TintMaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
        TintManager tintManager = TintManager.get(context);

        mBackgroundHelper = new AppCompatBackgroundHelper(this, tintManager);
        mBackgroundHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundDrawableExternal(background);
        }
    }

    @Override
    public void setBackgroundResource(int resId) {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundResId(resId);
        } else {
            super.setBackgroundResource(resId);
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundColor(color);
        }
    }

    @Override
    public void setBackgroundTintList(int resId) {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundTintList(resId, null);
        }
    }

    @Override
    public void setBackgroundTintList(int resId, PorterDuff.Mode mode) {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundTintList(resId, mode);
        }
    }

    @Override
    public void tint() {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.tint();
        }
    }
}
