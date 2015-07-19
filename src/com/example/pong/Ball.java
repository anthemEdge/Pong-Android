package com.example.pong;

import java.util.Random;

import com.example.pong.Bar.Position;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Ball extends Sprite {

	private float xPos = getScreenWidth() / 2;
	private float yPos = 50;

	private float velocity_x = 0.7f;
	private float velocity_y = 0.7f;

	private int xDirection = 1;
	private int yDirection = 1;

	private Position position;

	private int ballSize = 45;

	public Ball(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		ballSize = (int) ((int) screenHeight * (0.02));

		velocity_x = (float) (screenWidth * 0.000364583);
		velocity_y = (float) (screenWidth * 0.000364583);

	}

	public void init() {
		Random random = new Random();

		yPos = random.nextInt(getScreenHeight());
		xPos = getScreenWidth() / 2;
		xDirection = randomDirection();
		yDirection = randomDirection();

		position = null;
	}

	public Rect getScreenRect() {
		Rect screenRect = new Rect((int) xPos, (int) yPos, (int) xPos
				+ ballSize, (int) yPos + ballSize);
		return screenRect;
	}

	public void draw(Canvas canvas) {
		canvas.drawRect(getScreenRect(), paint);
	}

	public void update(long elapsed) {

		// Make sure the ball is bounded by the screen
		if (getScreenRect().left - getMargin() <= 0) {
			// xDirection = 1;
			position = Position.LEFT;
		} else if (getScreenRect().right + getMargin() >= getScreenWidth()) {
			// xDirection = -1;
			position = Position.RIGHT;
		}

		// Bounce in yDirection
		if (getScreenRect().top <= 0) {
			yDirection = 1;
		} else if (getScreenRect().bottom >= getScreenHeight()) {
			yDirection = -1;
		}

		// Calculating new position
		xPos += elapsed * velocity_x * xDirection;
		yPos += elapsed * velocity_y * yDirection;

	}

	private int randomDirection() {

		Random random = new Random();

		int number = random.nextInt(2);
		switch (number) {
		case 1:
			number = 1;
			break;
		default:
			number = -1;
			break;
		}
		return number;
	}

	public float getyPos() {
		return yPos;
	}

	public void contact(Position pos) {

		switch (pos) {
		case LEFT:
			xDirection = 1;
			break;
		case RIGHT:
			xDirection = -1;
			break;
		}
	}

	public Position getPosition() {
		return position;
	}

}
