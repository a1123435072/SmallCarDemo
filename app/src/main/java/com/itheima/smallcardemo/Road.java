package com.itheima.smallcardemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sszz on 2017/1/19.
 */

public class Road extends View {

	private Bitmap road;
	private Rect src;
	private Rect dst;
	private int roadWidth;
	private Rect lastSrc;
	private Rect lastDst;

	public Road(Context context) {
		this(context, null);
	}

	public Road(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Road(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private Paint paint;

	private void init() {
		src = new Rect();
		dst = new Rect();
		road = BitmapFactory.decodeResource(getResources(), R.drawable.road);
		roadWidth = road.getWidth();
		paint = new Paint();

		lastSrc = new Rect();
		lastDst = new Rect();

		path=new Path();

	}

	private int offset;
	private Path path;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		path.addCircle(this.getWidth()/2,this.getHeight()/2,this.getWidth()/2,Path.Direction.CW);
		//画布的剪切:可以按照path去剪切出对应的图形
		canvas.clipPath(path);

		//参数2:src 表示从源图片中选择的矩形区域
		//参数3:dst 表示将多少矩形区域的数据绘制到画布上
		if(offset+this.getWidth()<=roadWidth) {
			src.set(offset, 0, this.getWidth() + offset, this.getHeight());
			dst.set(0, 0, this.getWidth(), this.getHeight());
			canvas.drawBitmap(road, src, dst, paint);
		}else{
			src.set(offset,0,roadWidth,this.getHeight());
			dst.set(0,0,src.width(),this.getHeight());
			canvas.drawBitmap(road,src,dst,paint);

			lastSrc.set(0,0,this.getWidth()-src.width(),this.getHeight());
			lastDst.set(dst.width(),0,getWidth(),getHeight());
			canvas.drawBitmap(road,lastSrc,lastDst,paint);

		}
		offset+=3;
		offset%=roadWidth;
		invalidate();
	}
}
