package page.rightshift.rtst1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class InputController {
    public static void handleControls() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(Game.tick % 4 != 0) { return; }

            if(Game.money > (Unit.orders.size() * 1.1) + 2) {
                Vector2 clickPos = new Vector2(Gdx.input.getX() - 32, (Gdx.graphics.getHeight() - Gdx.input.getY()) - 32);

                Unit.orders.add(new MoveOrder(clickPos));
                Game.money -= (Unit.orders.size()+1 * 1.1) + 2;
            }
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            if(Game.money >= 5) {
                UnitUtils.addUnit();
                Game.money -= 5;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.F1)) {
            if(Game.money >= 30 * (1.02 * Game.money_upgrades) + 50) {
                Game.money -= 30 * (1.02 * Game.money_upgrades) + 50;
                Game.money_upgrades += 1;
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.F2)) {
            if(Game.money >= 50 * (1.015 * Game.speed_upgrades) + 50) {
                Game.money -= 50 * (1.015 * Game.speed_upgrades) + 50;
                Game.speed_upgrades += 1;
            }
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Game.simulation_enabled = !Game.simulation_enabled;
        }
    }
}
