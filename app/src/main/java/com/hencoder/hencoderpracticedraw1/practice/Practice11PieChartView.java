package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {
    private List<Data> dataList;
    private float maxNumber = 0f;
    private float sumNumber = 0f;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDate();
    }

    private void initDate() {
        dataList = new ArrayList<>();
        dataList.add(new Data("Froyo", 1f, Color.GRAY));
        dataList.add(new Data("Gingerbread", 3f, Color.DKGRAY));
        dataList.add(new Data("Ice Cream Sandwich", 3f, Color.GRAY));
        dataList.add(new Data("Jelly Bean", 15f, Color.GREEN));
        dataList.add(new Data("KitKat", 25f, Color.BLUE));
        dataList.add(new Data("Lollipop", 30f, Color.RED));
        dataList.add(new Data("Marshmallow", 14f, Color.YELLOW));
        for (Data data : dataList) {
            maxNumber = Math.max(data.getNumber(), maxNumber);
            sumNumber += data.getNumber();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        RectF rect = new RectF(-250, -250, 250, 250);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float startAngel = 0.0f;
        float sweepAngle = 0.0f;
        float lineAngle;       // 当前扇形一半的角度
        float lineStartX = 0f; // 直线开始的X坐标
        float lineStartY = 0f; // 直线开始的Y坐标
        float lineEndX;        // 直线结束的X坐标
        float lineEndY;        // 直线结束的Y坐标
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        boolean isMoved = false;
        for (Data data : dataList) {
            if (isMoved) {
                canvas.translate(-lineStartX * 0.1f, -lineStartY * 0.1f);
            }
            paint.setColor(data.getColor());
            paint.setStyle(Paint.Style.FILL);

            sweepAngle = 360 * data.getNumber() / sumNumber;
            lineAngle = startAngel + sweepAngle / 2;
            lineStartX = 250 * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineStartY = 250 * (float) Math.sin(lineAngle / 180 * Math.PI);
            lineEndX = 250 * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineEndY = 250 * (float) Math.sin(lineAngle / 180 * Math.PI);

            if (data.getNumber() == maxNumber) {
                canvas.translate(lineStartX * 0.1f, lineStartY * 0.1f);
                canvas.drawArc(rect, startAngel, sweepAngle, true, paint);
                isMoved = true;
            } else {
                canvas.drawArc(rect, startAngel, sweepAngle-2, true, paint);
            }

            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(24);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);
            if (lineAngle > 90 && lineAngle <= 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, paint);
                canvas.drawText(data.getName(), lineEndX - 50 - 10 - paint.measureText(data.getName()), lineEndY + 10, paint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, paint);
                canvas.drawText(data.getName(), lineEndX + 50 + 10, lineEndY + 10, paint);
            }

            startAngel += sweepAngle;
        }
    }
}
