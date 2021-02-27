package com.example.viewstatesamples;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SampleView extends View {
    private static final String TAG = SampleView.class.getSimpleName();
    private static final int SIZE = 200;

    private final Paint paint = new Paint();
    private boolean changed = false;

    public SampleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                performClick();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean performClick() {
        changed = !changed;
        invalidate();
        return super.performClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;

        if (changed) {
            canvas.drawCircle(halfWidth, halfHeight, SIZE / 2f, paint);
        } else {
            int left = halfWidth - SIZE / 2;
            int top = halfHeight - SIZE / 2;
            int right = left + SIZE;
            int bottom = top + SIZE;

            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Log.d(TAG, "onSaveInstanceState");
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        Log.d(TAG, "onSaveInstanceState changed: " + changed);
        savedState.changed = changed;
        Log.d(TAG, "onSaveInstanceState SavedState changed: " + savedState.changed);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Log.d(TAG, "onRestoreInstanceState");
        Log.d(TAG, "onRestoreInstanceState changed: " + changed);
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        Log.d(TAG, "onRestoreInstanceState restore start");
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        changed = savedState.changed;
        Log.d(TAG, "onRestoreInstanceState SavedState changed: " + savedState.changed);
        invalidate();
    }

    static class SavedState extends BaseSavedState {
        boolean changed;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            changed = source.readInt() == 1;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(changed ? 1 : 0);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel source) {
                        return new SavedState(source);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
