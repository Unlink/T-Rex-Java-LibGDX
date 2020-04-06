/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.rex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Unlink
 */
public class Prekazka extends Entita {

    public Prekazka(int x, int length, boolean big) {
        super(x, 10, 30*length, big ? 80 : 50);
    }

    @Override
    public void vykonajPohyb(float delta) {
        setX(getX() - delta*250);
    }

    @Override
    public void vykresli(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        super.vykresli(shapeRenderer);
    }
}
