package com.example.pong;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

// Draws the game and response to touch event
public class GameView extends SurfaceView implements Callback {


	private GameRunner gameRunner;
	private Game game;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		game.onTouchEvent(event);

		Log.d(MainActivity.DEBUG, "Touch Recieved");

		return true;
	}

	// On Create Surface
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		game = new Game(getHolder(), getResources(), getWidth(), getHeight());
		Log.d(MainActivity.DEBUG, "SurfaceView Created");
		gameRunner = new GameRunner(game);
		gameRunner.start();

	}

	// On Change to Surface
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		Log.d(MainActivity.DEBUG, "SurfaceView Changed");
	}

	// On Destroy to surface
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(MainActivity.DEBUG, "SurfaceView Destroyed");

		// Shutting down the graphics thread
		if (gameRunner != null) {
			gameRunner.shutDown();
		}

		// Making sure the tread stops
		while (gameRunner != null) {
			try {
				gameRunner.join();
				gameRunner = null;
			} catch (InterruptedException e) {
				Log.d(MainActivity.DEBUG, "GameRunner Join Interrupted");
			}
		}
	}
}
