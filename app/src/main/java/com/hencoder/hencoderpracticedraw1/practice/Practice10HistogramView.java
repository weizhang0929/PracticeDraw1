package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {
    private static final String NAME = "直方图";

    Paint basePaint;
    Paint rectPaint;

    private List<Data> dataList;
    private float maxNumber = 0;

    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDate();
        basePaint = new Paint();
        basePaint.setAntiAlias(true);
        basePaint.setColor(Color.WHITE);
        basePaint.setStrokeWidth(2);
    }

    private void initDate() {
        dataList = new ArrayList<>();
        dataList.add(new Data("Froyo", 1, Color.GREEN));
        dataList.add(new Data("GB", 3, Color.GREEN));
        dataList.add(new Data("ICS", 3, Color.GREEN));
        dataList.add(new Data("JB", 15, Color.GREEN));
        dataList.add(new Data("KitKat", 25, Color.GREEN));
        dataList.add(new Data("L", 30, Color.GREEN));
        dataList.add(new Data("M", 14, Color.GREEN));
        for (Data data : dataList) {
            maxNumber = Math.max(data.getNumber(), maxNumber);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        //绘制标题
        basePaint.setTextSize(56);
        canvas.drawText(NAME, (canvas.getWidth() - basePaint.measureText(NAME))/2, canvas.getHeight() * 0.9f, basePaint);
        //绘制坐标系
        canvas.translate(getWidth() * 0.1f, getHeight() * 0.7f);//移动到原点
        canvas.drawLine(0, 0, canvas.getWidth() * 0.8f, 0, basePaint);   // 画x轴
        canvas.drawLine(0, 0, 0, -canvas.getHeight() * 0.6f, basePaint); // 画y轴

        float unitHeight = 0.0f;
        float unitWidth = 0.0f;
        if (maxNumber > 0) {
            unitHeight = canvas.getHeight() * 0.6f / maxNumber;
        }
        if (dataList.size() > 0) {
            unitWidth = (canvas.getWidth() * 0.8f - 20) / dataList.size();
        }
        basePaint.setStyle(Paint.Style.FILL);
        basePaint.setTextSize(24);
        for (Data data : dataList) {
            basePaint.setColor(Color.GREEN);
            canvas.drawRect(20, -unitHeight * data.getNumber(), unitWidth, 0, basePaint);
            basePaint.setColor(Color.WHITE);
            canvas.drawText(data.getName(), (unitWidth -20 - basePaint.measureText(data.getName())) / 2 + 20, 20, basePaint);
            canvas.translate(unitWidth, 0);
        }
    }
}
