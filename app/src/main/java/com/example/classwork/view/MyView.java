package com.example.classwork.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.classwork.R;

public class MyView extends View {

    private Paint paint;
    private Path path;
    private float x, y;
    public int color;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        x = 0;
        y = 0;

        path = new Path();
        paint = new Paint();

        color = getResources().getColor(R.color.red);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16);

        canvas.drawColor(getResources().getColor(R.color.black));
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (x == 0) {
            x = event.getX();
            y = event.getY();
            path.moveTo(x, y);
        } else {
            x = event.getX();
            y = event.getY();
        }

        path.lineTo(x, y);

        invalidate();

        return true;
    }
}
