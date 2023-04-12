package page.rightshift.rtst1;

import com.badlogic.gdx.math.Vector2;

public class UnitOrder {
    public int type;
    public Vector2 targetpos;
    public Unit targetunit;

    UnitOrder(Vector2 tPos) {
        targetpos = tPos;
        targetunit = null;
        type = 1;
    }

    UnitOrder(Unit tUnit) {
        targetpos = null;
        targetunit = tUnit;
        type = 2;
    }
}
