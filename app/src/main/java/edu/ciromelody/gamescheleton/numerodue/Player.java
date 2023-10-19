package edu.ciromelody.gamescheleton.numerodue;

public class Player {
    public int pX, pY, pYInitial, pFrame, velocity;

    public Player(){
        pX = AppConstants.SCREEN_WIDTH/3 - AppConstants.getBitmapBank().getPlayerWidth();
        /*pYInitial = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getPathHeight()
                - AppConstants.getBitmapBank().getPlayerHeight();*/
        pYInitial = AppConstants.SCREEN_HEIGHT - 2
                - AppConstants.getBitmapBank().getPlayerHeight();
        /*pY = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getPathHeight()
                - AppConstants.getBitmapBank().getPlayerHeight();*/
        pY = AppConstants.SCREEN_HEIGHT - 2
                - AppConstants.getBitmapBank().getPlayerHeight();
        pFrame = 0;
        velocity = 0;
    }

    // Getter method for velocity
    public int getVelocity(){
        return velocity;
    }

    // Setter method for velocity
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    // Getter method for getting X-coordinate of the Player
    public int getX(){
        return pX;
    }

    // Getter method for getting the Y-coordinate of the Player
    public int getY(){
        return pY;
    }

    // Setter method for setting the Y-coordinate
    public void setY(int pY){
        this.pY = pY;
    }
}
