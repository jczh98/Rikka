package top.rechinx.rikka.theme.widgets;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.google.android.material.tabs.TabLayout;

import top.rechinx.rikka.theme.utils.ThemeUtils;
import top.rechinx.rikka.theme.utils.TintManager;

public class TintTabLayout extends TabLayout implements Tintable, AppCompatBackgroundHelper.BackgroundExtensible {

    private AppCompatBackgroundHelper mBackgroundHelper;
    private AppCompatTabLayoutHelper mTabHelper;

    public TintTabLayout(Context context) {
        super(context);
    }

    public TintTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TintTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
        TintManager tintManager = TintManager.get(getContext());

        mBackgroundHelper = new AppCompatBackgroundHelper(this, tintManager);
        mBackgroundHelper.loadFromAttribute(attrs, defStyleAttr);

        mTabHelper = new AppCompatTabLayoutHelper(this, tintManager);
        mTabHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    public void setSelectedTabIndicatorColor(int color) {
        super.setSelectedTabIndicatorColor(color);
        if(mTabHelper != null) {
            mTabHelper.setTabIndicatorColor();
        }
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

        if (mTabHelper != null) {
            mTabHelper.tint();
        }
    }
}
