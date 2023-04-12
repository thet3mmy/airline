package page.rightshift.rtst1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class InputController {
    public static void handleControls() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

            if(Game.money > (Unit.orders.size() * 1.1) + 2) {
                Vector2 clickPos = new Vector2(Gdx.input.getX() - 32, (Gdx.graphics.getHeight() - Gdx.input.getY()) - 32);
                boolean isValid = true;

                for(UnitOrder o: Unit.orders) {
                    Circle c = new Circle();
                    c.setRadius(192);
                    c.setPosition(o.targetpos.x + 32, o.targetpos.y + 32);
                    if(c.contains(clickPos)) {isValid = false;}
                }

                if(!isValid) {return;} // it's not a valid place

                Unit.orders.add(new UnitOrder(clickPos));
                Game.money -= (Unit.orders.size()+1 * 1.1) + 2;

            }
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            if(Game.money >= 5) {
                UnitUtils.addUnit();
                Game.money -= 5;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.F1)) {
            int cost = (int)(30 * (1.02 * Game.money_upgrades) + 50);

            if(Game.money >= cost) {
                Game.money -= cost;
                Game.money_upgrades += 1;
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.F2)) {
            int cost = (int)(50 * (1.015 * Game.speed_upgrades) + 50);

            if(Game.money >= cost) {
                Game.money -= cost;
                Game.speed_upgrades += 1;
            }
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Game.simulation_enabled = !Game.simulation_enabled;
        }
    }
}
