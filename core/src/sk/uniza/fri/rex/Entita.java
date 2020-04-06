/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.rex;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Unlink
 */
public abstract class Entita {
    
    private float x;
    private float y;
    private int width;
    private int height;

    public Entita(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public abstract void vykonajPohyb(float delta);
    
    public void vykresli(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
    }
}
