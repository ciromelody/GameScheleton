package edu.ciromelody.gamescheleton.numerodue;

import android.graphics.Bitmap;
import android.graphics.RectF;

import edu.ciromelody.gamescheleton.utility.RectHitbox;

public class Player {
    public int pX, pY, pYInitial, pFrame, velocity;
    RectHitbox rectHitboxFeet;
    RectHitbox rectHitboxHead;
    RectHitbox rectHitboxLeft;
    RectHitbox rectHitboxRight;
    RectF rettangoloSopraLaTesta;
    RectF rettangoloSottoIPiedi;
    RectF rettangoloADestra;
    RectF rettangoloASinistra;
    int altezzaBitmap;
    int larghezzaBitmap;

    public Player(){
        pX = AppConstants.SCREEN_WIDTH/3 - AppConstants.getBitmapBank().getPlayerWidth(0);
        /*pYInitial = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getPathHeight()
                - AppConstants.getBitmapBank().getPlayerHeight();*/
        pYInitial = AppConstants.SCREEN_HEIGHT - 2
                - AppConstants.getBitmapBank().getPlayerHeight(0);
        /*pY = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getPathHeight()
                - AppConstants.getBitmapBank().getPlayerHeight();*/
        pY = AppConstants.SCREEN_HEIGHT - 2
                - AppConstants.getBitmapBank().getPlayerHeight(0);
        pFrame = 0;
        velocity = 0;
        altezzaBitmap=AppConstants.getBitmapBank().getPlayerHeight(0);
        larghezzaBitmap=AppConstants.getBitmapBank().getPlayerWidth(0);
        rectHitboxFeet = new RectHitbox();
        rectHitboxHead = new RectHitbox();
        rectHitboxLeft = new RectHitbox();
        rectHitboxRight = new RectHitbox();
        rettangoloSopraLaTesta=new RectF();
        rettangoloSottoIPiedi=new RectF();
        rettangoloADestra=new RectF();
        rettangoloASinistra=new RectF();

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
   public void aggiornaRectIntornoAlPlayer(){
        //120 è la dimensione del player
        rectHitboxFeet.top = pY + (altezzaBitmap * .90f);
        rectHitboxFeet.left = pX ;
        rectHitboxFeet.bottom = pY + (altezzaBitmap * .98f);
        rectHitboxFeet.right = pX + (larghezzaBitmap * .6f);

        rettangoloSottoIPiedi.top=rectHitboxFeet.top;
        rettangoloSottoIPiedi.left= rectHitboxFeet.left;
        rettangoloSottoIPiedi.bottom=rectHitboxFeet.bottom;
        rettangoloSottoIPiedi.right=rectHitboxFeet.right;

       // Update player head hitbox
        rectHitboxHead.top = pY;
        rectHitboxHead.left = pX ;
        rectHitboxHead.bottom = pY + altezzaBitmap * .2f;
        rectHitboxHead.right = pX + (larghezzaBitmap * .6f);
        rettangoloSopraLaTesta.top=rectHitboxHead.top;
       rettangoloSopraLaTesta.left=rectHitboxHead.left;
       rettangoloSopraLaTesta.bottom=rectHitboxHead.bottom;
       rettangoloSopraLaTesta.right=rectHitboxHead.right;

        // Update player left hitbox
        rectHitboxLeft.top = pY + (altezzaBitmap * .2f);
        rectHitboxLeft.left = pX -( larghezzaBitmap * .15f);
        rectHitboxLeft.bottom = pY + (altezzaBitmap * .8f);
        rectHitboxLeft.right = pX + (larghezzaBitmap * .12f);

        rettangoloASinistra.top=rectHitboxLeft.top;
       rettangoloASinistra.left=rectHitboxLeft.left;
       rettangoloASinistra.bottom=rectHitboxLeft.bottom;
       rettangoloASinistra.right=rectHitboxLeft.right;

        // Update player right hitbox
        rectHitboxRight.top = pY+ (altezzaBitmap * .2f);
        rectHitboxRight.left = pX + (larghezzaBitmap * .5f);
        rectHitboxRight.bottom = pY +( altezzaBitmap * .8f);
        rectHitboxRight.right = pX + (larghezzaBitmap * .7f);

        rettangoloADestra.top=rectHitboxRight.top;
       rettangoloADestra.left=rectHitboxRight.left;
       rettangoloADestra.bottom=rectHitboxRight.bottom;
       rettangoloADestra.right=rectHitboxRight.right;
    }
}
