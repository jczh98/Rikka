package top.rechinx.rikka.theme.widgets;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.ColorRes;
import top.rechinx.rikka.R;
import top.rechinx.rikka.theme.utils.TintInfo;
import top.rechinx.rikka.theme.utils.TintManager;

public class AppCompatTabLayoutHelper extends AppCompatBaseHelper<TabLayout> {

    private int mTabIndicatorColorId;

    private TintInfo mTabIndicatorColorTintInfo;

    AppCompatTabLayoutHelper(TabLayout view, TintManager tintManager) {
        super(view, tintManager);
    }

    @Override
    void loadFromAttribute(AttributeSet attrs, int defStyleAttr) {
        TypedArray array = mView.getContext().obtainStyledAttributes(attrs, R.styleable.TintTabLayoutHelper , defStyleAttr, 0);

        int colorId = array.getResourceId(R.styleable.TintTabLayoutHelper_tabIndicatorColor, 0);
        setTabIndicatorColor(colorId);

        array.recycle();
    }

    public void setTabIndicatorColor() {
        if (skipNextApply()) return;

        resetTabIndicatorTintResource(0);
        setSkipNextApply(false);
    }

    private void setTabIndicatorColor(@ColorRes int resId) {
        if (mTabIndicatorColorId != resId) {
            resetTabIndicatorTintResource(resId);

            if (resId != 0) {
                setSupportTabIndicatorTint(resId);
            }
        }
    }

    private void setSupportTabIndicatorTint(@ColorRes int resId) {
        if (resId != 0) {
            if (mTabIndicatorColorTintInfo == null) {
                mTabIndicatorColorTintInfo = new TintInfo();
            }
            mTabIndicatorColorTintInfo.mHasTintList = true;
            mTabIndicatorColorTintInfo.mTintList =  mTintManager.getColorStateList(resId);
        }
        applySupportTabIndicatorColorTint();
    }

    private void applySupportTabIndicatorColorTint() {
        if (mTabIndicatorColorTintInfo != null && mTabIndicatorColorTintInfo.mHasTintList) {
            setTabIndicatorColor(mTabIndicatorColorTintInfo.mTintList);
        }
    }

    private void setTabIndicatorColor(ColorStateList tint) {
        if (skipNextApply()) return;

        mView.setSelectedTabIndicatorColor(tint.getDefaultColor());
    }

    private void resetTabIndicatorTintResource(@ColorRes int resId) {
        mTabIndicatorColorId = resId;
        if (mTabIndicatorColorTintInfo != null) {
            mTabIndicatorColorTintInfo.mHasTintList = false;
            mTabIndicatorColorTintInfo.mTintList = null;
        }
    }

    @Override
    public void tint() {
        if (mTabIndicatorColorId != 0) {
            setSupportTabIndicatorTint(mTabIndicatorColorId);
        }
    }
}
