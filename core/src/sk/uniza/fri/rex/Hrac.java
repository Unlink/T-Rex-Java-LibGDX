/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.rex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Unlink
 */
public class Hrac extends Entita {

    private boolean inJump;
    private boolean rising;
    private final float jumpHeight = 150;

    public Hrac() {
        super(10, 10, 40, 100);
    }
    
    @Override
    public void vykresli(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void vykonajPohyb(float delta) {
        //Input spracovanie
        if (!inJump && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            inJump = true;
            rising = true;
            setY(10);
        }
        
        if (inJump) {
            System.out.println("Jump");
            if (rising) {
                setY((float) (getY() + delta * Math.sqrt(2*(jumpHeight - getY())) * 40));
                if (getY() > jumpHeight) {
                    rising = false;
                    setY(jumpHeight-1);
                }
            }
            else {
                setY((float) (getY() - delta * Math.sqrt(2*(jumpHeight - getY())) * 30));
                if (getY() < 10) {
                    inJump = false;
                    setY(10);
                }
            }
            System.out.println("pos " + getY());
        }
    }
    
}
