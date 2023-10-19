package edu.ciromelody.gamescheleton.numerodue;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import edu.ciromelody.gamescheleton.R;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;
    MediaPlayer mediaPlayer;
    public GameView(Context context) {
        super(context);
        mediaPlayer = MediaPlayer.create(context, R.raw.hit);
        initView();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (!gameThread.isRunning()) {
            gameThread = new GameThread(holder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        if (gameThread.isRunning()) {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }
    }
        void initView() {
            SurfaceHolder holder = getHolder();
            holder.addCallback(this);
            setFocusable(true);
            gameThread = new GameThread(holder);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            // Tap is detected
            if (action == MotionEvent.ACTION_DOWN) {
               if(AppConstants.getGameEngine().gameState == 0){
                    AppConstants.getGameEngine().gameState = 1;
                }
                if (AppConstants.playerGrounded == true) {
                   // AppConstants.getGameEngine().player.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
                    AppConstants.playerGrounded = false;
                }
                mediaPlayer.start();
            }
            return true;
        }


    public void pause() {

    }

    // If the Activity is resumed make sure to resume our thread

    public void resume() {

    }

}
