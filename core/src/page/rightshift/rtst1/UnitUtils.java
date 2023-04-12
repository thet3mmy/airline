package page.rightshift.rtst1;

import java.util.Random;

public class UnitUtils {
    public static void addUnit() {
        Unit newUnit = new Unit();
        newUnit.sprite.setPosition(new Random().nextInt(640),new Random().nextInt(480));
        Game.toDraw.add(newUnit);
    }
}
