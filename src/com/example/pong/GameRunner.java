package com.example.pong;

import android.util.Log;

// Update graphics in this thread
public class GameRunner extends Thread {

	private volatile boolean running = true;
	private Game game;

	public GameRunner(Game game) {
		this.game = game;
	}

	@Override
	public void run() {

		long lastUpdate = System.currentTimeMillis();
		game.inti();

		
		// Graphics Loop
		while (running) {

			long now = System.currentTimeMillis();
			long elapsed = now - lastUpdate;
			lastUpdate = System.currentTimeMillis();
			Log.d(MainActivity.DEBUG, "Elapse:" + elapsed);
			
			if (elapsed < 100){
				game.update(elapsed);
				game.draw();
			}
		}
	}

	public void shutDown() {
		running = false;
		Log.d(MainActivity.DEBUG, "GameRunner has shutdown");
	}

}
