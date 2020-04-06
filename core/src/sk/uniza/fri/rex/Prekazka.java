/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.rex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Unlink
 */
public class Prekazka extends Entita {

    public Prekazka(Texture texture, int x, int length, boolean big) {
        super(new Sprite(texture, 446, 2, 34, 70), x, 10, 30*length, big ? 80 : 50);
    }

    @Override
    public void vykonajPohyb(float delta) {
        setX(getX() - delta*250);
    }

    @Override
    public void vykresli(SpriteBatch spriteBatch) {
        spriteBatch.setColor(Color.BLACK);
        super.vykresli(spriteBatch);
    }
}
