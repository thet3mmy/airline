package page.rightshift.rtst1;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class UnitUtils {
    public static void addUnit(Vector2 pos) {
        Unit newUnit = new Unit();
        newUnit.sprite.setPosition(pos.x, pos.y);
        Game.toDraw.add(newUnit);
    }
}
