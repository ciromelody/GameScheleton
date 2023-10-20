package edu.ciromelody.gamescheleton.numerodue;

public class Path {

    private int pathX, pathY, pathVelocity;

    public Path(){
        pathX = 0;
        //pathY = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getPathHeight();
        pathY=AppConstants.SCREEN_HEIGHT ;//tolgo il path
        pathVelocity = 15;
    }

    // Getter method for getting the X-coordinate
    public int getPathX(){
        return pathX;
    }

    // Getter method for getting the Y-coordinate
    public int getPathY(){
        return pathY;
    }

    // Setter method for setting the X-coordinate
    public void setPathX(int pathX){
        this.pathX = pathX;
    }

    // Getter method for getting the velocity
    public int getPathVelocity(){
        return pathVelocity;
    }
}