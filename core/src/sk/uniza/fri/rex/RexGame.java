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

public class RexGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private double yPos = 10;
    private boolean inJump = false;
    private boolean rising = true;
    private int jumpHeight = 150;
    
    private LinkedList<Prekazka> prekazky = new LinkedList<Prekazka>();
    private Random rnd = new Random();
    private BitmapFont font;

    @Override
    public void create() {
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
        
        //Input spracovanie
        if (!inJump && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            inJump = true;
            rising = true;
            yPos = 10;
        }
        
        if (inJump) {
            System.out.println("Jump");
            if (rising) {
                yPos = (yPos + delta * Math.sqrt(2*(jumpHeight - yPos)) * 40);
                if (yPos > jumpHeight) {
                    rising = false;
                    yPos = jumpHeight-1;
                }
            }
            else {
                yPos = (yPos - delta * Math.sqrt(2*(jumpHeight - yPos)) * 30);
                if (yPos < 10) {
                    inJump = false;
                    yPos = 10;
                }
            }
            System.out.println("pos " + yPos);
        }
        
        //Posun prekazok
        for (Prekazka prekazka : prekazky) {
            prekazka.x -= delta * 250;
        }
        
        //odstranenie starych prekazok
        while (prekazky.size() > 0 && prekazky.getFirst().x < 0) {            
            prekazky.removeFirst();
        }
        
        //Pridanie novych
        if (rnd.nextInt() < 10 && (prekazky.size() == 0 || prekazky.getLast().x < 200)) {
            prekazky.add(new Prekazka(Gdx.graphics.getWidth(), rnd.nextInt(2) + 1, rnd.nextBoolean()));
        }
        
//        batch.begin();
//        batch.draw(img, 0, 0);
//        batch.end();
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(10, (float) yPos, 40, 100);
        
        //Vykrelsenie prekazok
        for (Prekazka prekazka : prekazky) {
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(prekazka.x, 10, prekazka.width, prekazka.height);
        }
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
    
    public class Prekazka {
        int x;
        int width;
        int height;

        public Prekazka(int x, int length, boolean big) {
            this.x = x;
            this.width = 30 * length;
            this.height = big ? 60 : 30;
        }

        
    }
}