package com.example.test.utils.pushdown;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public interface PushDown{
    void setScale(float scale );

    void setScale(@PushDownAnim.Mode int mode, float scale );

    void setDurationPush(long duration );

    void setDurationRelease(long duration );

    void setInterpolatorPush(AccelerateDecelerateInterpolator interpolatorPush );

    void setInterpolatorRelease(AccelerateDecelerateInterpolator interpolatorRelease );

    PushDown setOnClickListener( View.OnClickListener clickListener );

    PushDown setPreventDoubleClickListener( View.OnClickListener clickListener );

    void setOnLongClickListener(View.OnLongClickListener clickListener );

    void setOnTouchEvent(View.OnTouchListener eventListener );
}