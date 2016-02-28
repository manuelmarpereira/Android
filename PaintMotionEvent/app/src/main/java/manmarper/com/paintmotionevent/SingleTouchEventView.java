package manmarper.com.paintmotionevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by manel_000 on 27/02/16.
 */
public class SingleTouchEventView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    private float eventX, eventY;
    private boolean fingerDown = false;

    public SingleTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);// Helper for setFlags(), setting or clearing the ANTI_ALIAS_FLAG bit AntiAliasing smooths out the edges of what is being drawn, but is has no impact on the interior of the shape.
        paint.setStrokeWidth(6f);// Set the width for stroking.
        paint.setColor(Color.BLACK); //Set the paint's color.
        paint.setStyle(Paint.Style.STROKE); //Set the paint's Style.
        paint.setStrokeJoin(Paint.Join.ROUND); //Set the paint's Join.
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);

        if (fingerDown) {
            paint.setColor(Color.BLUE);
            canvas.drawCircle(eventX, eventY, 20, paint); //Draw the specified circle using the specified paint.
            paint.setColor(Color.BLACK);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        eventX = event.getX();
        eventY = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                fingerDown = true;
                path.moveTo(eventX, eventY);
                return true;

            case MotionEvent.ACTION_UP:
                fingerDown = false;
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;

            default:
                return false;

        }

        invalidate(); //it draws the custom view that doesn't have anything

        return true;
    }
}

