package com.epy0n0ff.android.sample.viewdraghelper;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * <br/>Created by epy0n0ff on 2013/11/11.
 */
public class DragViewGroup extends ViewGroup {
    public static final String TAG = DragViewGroup.class.getSimpleName();
    private ViewDragHelper mDragHelper;
    private View           mView;

    public DragViewGroup( Context context, AttributeSet attrs ) {
        super( context, attrs );

        mView = new View( context );
        mView.setBackgroundColor( Color.BLUE );
        addView( mView );

        Log.d( TAG, mView.toString() );

        mDragHelper = ViewDragHelper.create( this, 1.f, new DragCallBack() );
    }

    public DragViewGroup( Context context ) {
        super( context );
    }

    @Override
    protected void onFinishInflate() {

    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        Log.d( TAG, "onTouchEvent" );
        mDragHelper.processTouchEvent( event );

        if( event.getAction() == MotionEvent.ACTION_UP ) {

            //左座標を見てスクロールさせる
            int left = mView.getLeft();
            if( mDragHelper.smoothSlideViewTo( mView, left >= 0 ? - getWidth() + 100 : 0, 0 ) ) {
                //コレを入れないとアニメーションしない
                ViewCompat.postInvalidateOnAnimation( this );
            }
        }

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent( MotionEvent ev ) {
        Log.d( TAG, "onInterceptTouchEvent" );
        final int action = MotionEventCompat.getActionMasked( ev );
        if( action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP ) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent( ev );
    }

    @Override
    protected void onLayout( boolean changed, int l, int t, int r, int b ) {
        mView.layout( l, t, r, b );
    }

    @Override
    public void computeScroll() {
        if( mDragHelper.continueSettling( true ) ) {
            //コレを入れないとアニメーションしない
            ViewCompat.postInvalidateOnAnimation( this );
        }
    }

    class DragCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView( View view, int i ) {
            Log.d( TAG, "tryCaptureView" );
            return view == mView;
        }

        @Override
        public void onViewPositionChanged( View changedView, int left, int top, int dx, int dy ) {
            Log.d( TAG, String.format( "onViewPositionChanged %s %d %d %d %d", changedView.toString(), left, top, dx, dy ) );

        }

        @Override
        public int clampViewPositionHorizontal( View child, int left, int dx ) {
            //水平移動を設定 これを定義しないと横移動はしない
            final int paddingLeft = getPaddingLeft();
            final int paddingRight = getWidth() - child.getWidth();

            final int newLeft = Math.min( Math.max( left, paddingLeft ), paddingRight );

            return newLeft;
        }
    }
}
