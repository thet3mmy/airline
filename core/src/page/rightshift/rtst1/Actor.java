package page.rightshift.rtst1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Actor {
    public Sprite sprite;
    public Texture tex;
    public Vector2 direction;
    public int ticks;

    public void think() {   // do any kind of thinking/processing for this actor, use @Override
        return;
    }

    Actor(Texture tex) {
        this.tex = tex;
        sprite = new Sprite(this.tex);
    }
}
