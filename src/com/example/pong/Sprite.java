package com.example.pong;

import android.graphics.Color;
import android.graphics.Paint;

public class Sprite {

	private int screenWidth;
	private int screenHeight;

	private int margin = 200;

	Paint paint;

	public Sprite(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		margin = (int) ((int) screenWidth * 0.1);

		paint = new Paint();
		paint.setColor(Color.WHITE);

	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getMargin() {
		return margin;
	}

	public Paint getPaint() {
		return paint;
	}
	
	

}
