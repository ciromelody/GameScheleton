package edu.ciromelody.gamescheleton.utility;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public abstract class Sprite {
    //le coordinate dello sprite
    protected  int x,y;
    protected Bitmap bitmap;
    protected Boolean visibile;

    public String getNomesprite() {
        return nomesprite;
    }

    public void setNomesprite(String nomesprite) {
        this.nomesprite = nomesprite;
    }

    String nomesprite;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Boolean getVisibile() {
        return visibile;
    }

    public void setVisibile(Boolean visibile) {
        this.visibile = visibile;
    }

    // la dimensione dello sprite
    protected int larghezza_sprite;
    protected int altezza_sprite;

    public int getLarghezza_sprite() {
        return larghezza_sprite;
    }

    public void setLarghezza_sprite(int larghezza_sprite) {
        this.larghezza_sprite = larghezza_sprite;
    }

    public int getAltezza_sprite() {
        return altezza_sprite;
    }

    public void setAltezza_sprite(int altezza_sprite) {
        this.altezza_sprite = altezza_sprite;
    }

    public Sprite(Bitmap bitmap , int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.larghezza_sprite=bitmap.getWidth();
        this.altezza_sprite=bitmap.getHeight();
    }
    public boolean contiene(int coordinata_x,int coordinata_y){
        // se il punto coordinata cade all interno dello sprite
        if((Math.abs((coordinata_x-x))<larghezza_sprite)&&((coordinata_y-y)<altezza_sprite)){
            return true;
        }
        return  false;
    }
    // disegna l immagine se è visibile
    public void disegnaBitmap(Canvas canvas){
        if(visibile){
            canvas.drawBitmap(bitmap,x,y,null);
        }
    }

    public void muoviImmagine(int distanza_x,int distanza_y){
        x=x+distanza_x;
        y=y+distanza_y;
        boolean b = y > Resources.getSystem().getDisplayMetrics().heightPixels;
        Random rnd=new Random();
        int coordinataX= rnd.nextInt( Resources.getSystem().getDisplayMetrics().widthPixels);
        if( b){y=getAltezza_sprite();x=coordinataX;}

    }
    public boolean collisione_tra_sprite(Sprite sprite){
        boolean collisione=false;
        if(this.getVisibile()&&sprite.getVisibile()){
           int coordinata_X_centrale=this.getX()+this.getLarghezza_sprite()/2;
           int coordinata_Y_centrale=this.getY()+this.getAltezza_sprite()/2;
           int coordinata_sprite_X_centrale=sprite.getX()+sprite.getLarghezza_sprite()/2;
           int coordinata_sprite_Y_centrale=sprite.getY()+sprite.getAltezza_sprite()/2;
           if(Math.abs(coordinata_X_centrale-coordinata_sprite_X_centrale)<=(Math.abs(this.getLarghezza_sprite()/2+sprite.getLarghezza_sprite()/2))&&
               (Math.abs(coordinata_Y_centrale-coordinata_sprite_Y_centrale)<=Math.abs(this.getAltezza_sprite()/2+sprite.getAltezza_sprite()/2))){
               collisione=true;
               return collisione;}

           }


         return collisione;

    }

}
