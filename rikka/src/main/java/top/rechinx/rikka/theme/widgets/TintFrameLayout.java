/*
 * Copyright (C) 2016 Bilibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.rechinx.rikka.theme.widgets;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import top.rechinx.rikka.theme.utils.TintManager;


/**
 * @author xyczero617@gmail.com
 * @time 16/2/14
 */
public class TintFrameLayout extends FrameLayout implements Tintable, AppCompatBackgroundHelper.BackgroundExtensible,
        AppCompatForegroundHelper.ForegroundExtensible {

    private AppCompatBackgroundHelper mBackgroundHelper;
    private AppCompatForegroundHelper mForegroundHelper;

    public TintFrameLayout(Context context) {
        this(context, null);
    }

    public TintFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TintFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
        TintManager tintManager = TintManager.get(context);

        mBackgroundHelper = new AppCompatBackgroundHelper(this, tintManager);
        mBackgroundHelper.loadFromAttribute(attrs, defStyleAttr);

        mForegroundHelper = new AppCompatForegroundHelper(this, tintManager);
        mForegroundHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (getBackground() != null) {
            invalidateDrawable(getBackground());
        }
    }

    @Override
    public void setForeground(Drawable foreground) {
        super.setForeground(foreground);
        if (mForegroundHelper != null) {
            mForegroundHelper.setForegroundDrawableExternal(foreground);
        }
    }

    public void setForegroundResource(int resId) {
        if (mForegroundHelper != null) {
            mForegroundHelper.setForegroundResId(resId);
        }
    }

    @Override
    public void setForegroundTintList(int resId) {
        if (mForegroundHelper != null) {
            mForegroundHelper.setForegroundTintList(resId, null);
        }
    }

    @Override
    public void setForegroundTintList(int resId, PorterDuff.Mode mode) {
        if (mForegroundHelper != null) {
            mForegroundHelper.setForegroundTintList(resId, mode);
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
        if (mForegroundHelper != null) {
            mForegroundHelper.tint();
        }
    }
}
