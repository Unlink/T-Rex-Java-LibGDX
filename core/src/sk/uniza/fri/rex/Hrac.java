/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.rex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Unlink
 */
public class Hrac extends Entita {

    private boolean inJump;
    private boolean rising;
    private final float jumpHeight = 150;

    private Sprite[] sprites;
    private int activeSprite = 0;
    private float spriteTimer = 0;

    private Texture texture;

    public Hrac(Texture texture) {
        super(null, 10, 10, 40, 100);
        this.texture = texture;
        sprites = new Sprite[]{
            new Sprite(texture, 1338 + 88 * 2, 2, 88, 94),
            new Sprite(texture, 1338 + 88 * 3, 2, 88, 94)
        };
        setSprite(sprites[0]);
    }

    @Override
    public void vykresli(SpriteBatch spriteBatch) {
        spriteBatch.setColor(Color.GREEN);
        super.vykresli(spriteBatch);
    }

    @Override
    public void vykonajPohyb(float delta) {
        //Input spracovanie
        if (!inJump && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            inJump = true;
            rising = true;
            setY(10);
            sprites = new Sprite[]{
                new Sprite(this.texture, 1338, 2, 88, 94),
                new Sprite(this.texture, 1338+88, 2, 88, 94)
            };
        }

        if (inJump) {
            System.out.println("Jump");
            if (rising) {
                setY((float) (getY() + delta * Math.sqrt(2 * (jumpHeight - getY())) * 40));
                if (getY() > jumpHeight) {
                    rising = false;
                    setY(jumpHeight - 1);
                }
            } else {
                setY((float) (getY() - delta * Math.sqrt(2 * (jumpHeight - getY())) * 30));
                if (getY() < 10) {
                    inJump = false;
                    setY(10);
                    sprites = new Sprite[]{
                        new Sprite(this.texture, 1338 + 88 * 2, 2, 88, 94),
                        new Sprite(this.texture, 1338 + 88 * 3, 2, 88, 94)
                    };
                }
            }
            System.out.println("pos " + getY());
        }

        spriteTimer += delta;
        if (spriteTimer > 0.2) {
            activeSprite = (activeSprite + 1) % sprites.length;
            setSprite(sprites[activeSprite]);
            spriteTimer = 0;
        }
    }

}
