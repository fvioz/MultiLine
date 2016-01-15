package fvioz.multiline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiLineView extends View {

    class Point {
        float X, Y;
        public Point(float X0, float Y0) {
            X = X0;
            Y = Y0;
        }
    }

    class Line {
        Point start, end;
        public Line(Point start0, Point end0) {
            start = start0;
            end = end0;
        }
    }

    Paint paint = new Paint();

    ArrayList<Line> lines = new ArrayList<>();

    float prevX, prevY, newX, newY = 0;

    public MultiLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Line line: lines){
            canvas.drawLine(line.start.X, line.start.Y, line.end.X, line.end.Y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                newX = event.getX();
                newY = event.getY();

                Point point1 = new Point(prevX, prevY);
                Point point2 = new Point(newX, newY);

                Line line = new Line(point1, point2);

                lines.add(line);

                break;
        }

        this.invalidate();

        return true;
    }

}