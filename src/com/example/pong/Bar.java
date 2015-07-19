package com.example.pong;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Bar extends Sprite {

	public enum Position {
		LEFT, RIGHT
	};

	private int barWidth = 45;
	private int barHeight = 226;
	private int halfBarHeight = barHeight / 2;

	float accuracy = 0.08f;

	private Position position;

	private int score = 0;

	private int yPos = getScreenHeight() / 2;

	public Bar(int screenWidth, int screenHeight, Position position) {
		super(screenWidth, screenHeight);
		this.position = position;
		barWidth = (int) ((int) screenHeight * (0.02));
		barHeight = (int) ((int) screenHeight * (0.2));
		halfBarHeight = barHeight / 2;
	}

	public Rect getScreenRect() {
		Rect screenRect = null;

		switch (position) {

		case LEFT:
			screenRect = new Rect(getMargin(), yPos - halfBarHeight,
					getMargin() + barWidth, yPos + halfBarHeight);
			break;
		case RIGHT:
			screenRect = new Rect(getScreenWidth() - barWidth - getMargin(),
					yPos - halfBarHeight, getScreenWidth() - getMargin(), yPos
							+ halfBarHeight);
			break;
		}
		return screenRect;
	}

	public int getyPos() {
		return yPos;
	}

	// This is for human player control
	public void setyPos(int yPos) {

		if (yPos < halfBarHeight) {
			yPos = halfBarHeight;
		} else if (yPos > getScreenHeight() - halfBarHeight) {
			yPos = getScreenHeight() - halfBarHeight;
		}

		this.yPos = yPos;
	}

	// same as setPos but use the y Position of the ball as input and move a
	// percentafe of difference
	public void update(float yPosBall) {

		if (yPosBall < halfBarHeight) {
			yPosBall = halfBarHeight;
		} else if (yPosBall > getScreenHeight() - halfBarHeight) {
			yPosBall = getScreenHeight() - halfBarHeight;
		}

		int posDiff = (int) yPosBall - yPos;
		yPos += (int) posDiff * accuracy;

	}

	public void draw(Canvas canvas) {
		canvas.drawRect(getScreenRect(), paint);
	}

	public Position getPosition() {
		return position;
	}

	public void won() {
		score++;
	}

	public int getScore() {
		return score;
	}
	
	

}
