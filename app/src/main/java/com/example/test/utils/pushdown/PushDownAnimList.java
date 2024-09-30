package com.example.test.utils.pushdown;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;


public class PushDownAnimList implements PushDown{

    private final List<PushDownAnim> pushDownList = new ArrayList<>();


    PushDownAnimList( View... views ){
        for( View view : views ){
            PushDownAnim pushDown = PushDownAnim.setPushDownAnimTo( view );
            pushDown.setOnTouchEvent( null );
            this.pushDownList.add( pushDown );
        }
    }

    @Override
    public void setScale(float scale ){
        for( PushDownAnim pushDown : pushDownList ){
            pushDown.setScale( scale );
        }
    }

    @Override
    public void setScale(int mode, float scale ){
        for( PushDownAnim pushDown : pushDownList ){
            pushDown.setScale( mode, scale );
        }
    }

    @Override
    public void setDurationPush(long duration ){
        for( PushDownAnim pushDown : pushDownList ){
            pushDown.setDurationPush( duration );
        }
    }

    @Override
    public void setDurationRelease(long duration ){
        for( PushDownAnim pushDown : pushDownList ){
            pushDown.setDurationRelease( duration );
        }
    }

    @Override
    public void setInterpolatorPush(AccelerateDecelerateInterpolator interpolatorPush ){
        for( PushDownAnim pushDown : pushDownList ){
            pushDown.setInterpolatorPush( interpolatorPush );
        }
    }

    @Override
    public void setInterpolatorRelease(AccelerateDecelerateInterpolator interpolatorRelease ){
        for( PushDownAnim pushDown : pushDownList ){
            pushDown.setInterpolatorRelease( interpolatorRelease );
        }
    }


    @Override
    public PushDownAnimList setOnClickListener( View.OnClickListener clickListener ){
        for( PushDownAnim pushDown : pushDownList ){
            if( clickListener != null ){
                pushDown.setOnClickListener( clickListener );
            }
        }
        return this;
    }

    @Override
    public PushDown setPreventDoubleClickListener(View.OnClickListener clickListener) {
        for( PushDownAnim pushDown : pushDownList ){
            if( clickListener != null ){
                pushDown.setPreventDoubleClickListener( clickListener );
            }
        }
        return this;
    }

    @Override
    public void setOnLongClickListener(View.OnLongClickListener clickListener ){
        for( PushDownAnim pushDown : pushDownList ){
            if( clickListener != null ){
                pushDown.setOnLongClickListener( clickListener );
            }
        }
    }

    public void setOnTouchEvent(final View.OnTouchListener eventListener ){
        for( PushDownAnim pushDown : pushDownList ){
            if( eventListener != null ){
                pushDown.setOnTouchEvent( eventListener );
            }
        }
    }

}