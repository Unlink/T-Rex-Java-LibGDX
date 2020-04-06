package sk.uniza.fri.rex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.LinkedList;
import java.util.Random;
import javax.management.MBeanAttributeInfo;

public class RexGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    
    private Entita hrac;
    private ManazerPrekazok prekazky;

    @Override
    public void create() {
        hrac = new Hrac();
        prekazky = new ManazerPrekazok();
        font = new BitmapFont();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        hrac.vykonajPohyb(delta);
        prekazky.posunPrekazky(delta);
        
//        batch.begin();
//        batch.draw(img, 0, 0);
//        batch.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        hrac.vykresli(shapeRenderer);
        prekazky.vykresli(shapeRenderer);
        shapeRenderer.end();
    
        batch.begin();
        font.draw(batch, Gdx.graphics.getFramesPerSecond()+"fps", 10, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}