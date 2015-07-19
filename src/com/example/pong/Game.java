package com.example.pong;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.pong.Bar.Position;

public class Game {

	SurfaceHolder holder;
	Resources resources;

	private int screenWidth;
	private int screenHeigth;

	private Ball ball;

	private Bar player;
	private Bar computer;

	private Paint scorePaint;

	public Game(SurfaceHolder holder, Resources resources, int screenWidth,
			int screenHeigth) {

		this.holder = holder;
		this.resources = resources;
		this.screenWidth = screenWidth;
		this.screenHeigth = screenHeigth;
	}

	public void inti() {
		// Will be called once per game, initialisation

		ball = new Ball(screenWidth, screenHeigth);
		ball.init();
		player = new Bar(screenWidth, screenHeigth, Position.LEFT);
		computer = new Bar(screenWidth, screenHeigth, Position.RIGHT);

		scorePaint = new Paint();
		scorePaint.setColor(Color.WHITE);
		scorePaint.setTextAlign(Align.CENTER);
		scorePaint.setAntiAlias(false);
		scorePaint.setTextSize(26);
		scorePaint.setTypeface(Typeface.DEFAULT_BOLD);

	}

	public void update(long elapsed) {
		// elapsed is the time since this method was last called

		if (player.getScreenRect().contains(ball.getScreenRect().left,
				ball.getScreenRect().centerY())) {
			ball.contact(player.getPosition());
		}
		if (computer.getScreenRect().contains(ball.getScreenRect().right,
				ball.getScreenRect().centerY())) {
			ball.contact(computer.getPosition());
		}
		if (ball.getPosition() == player.getPosition()) {
			computer.won();
			ball.init();
		}
		if (ball.getPosition() == computer.getPosition()) {
			player.won();
			ball.init();
		}
		ball.update(elapsed);
		computer.update(ball.getyPos());
	}

	public void drawScore(Canvas canvas) {

		StringBuilder builder = new StringBuilder();
		builder.append(player.getScore());
		builder.append(" : ");
		builder.append(computer.getScore());

		String score = builder.toString();

		canvas.drawText(score, screenWidth / 2, 50, scorePaint);

	}

	public void draw() {
		// Draw on Canvas

		Canvas canvas = holder.lockCanvas();

		if (canvas != null) {
			// Colour the whole thing black
			canvas.drawColor(Color.BLACK);
			// Draw the ball at postiton x 50 y 50
			ball.draw(canvas);
			player.draw(canvas);
			computer.draw(canvas);
			drawScore(canvas);

			// Unlock the surface and post the updates
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		player.setyPos((int) event.getY());
	}
}
