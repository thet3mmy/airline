package page.rightshift.rtst1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Unit extends Actor {
    private Circle testCircle;
    public static LinkedList<UnitOrder> orders;
    public int currentOrder;
    public int stopsLeft;

    @Override
    public void think() {
        ticks++;

        if(ticks % 10 == 0) {
            if(orders.size() > currentOrder) {
                double angleRadians = Math.atan2(orders.get(currentOrder).targetpos.y - this.sprite.getY(),
                        orders.get(currentOrder).targetpos.x - this.sprite.getX());
                float angleDegree = (float) angleRadians * MathUtils.radiansToDegrees - 90;
                this.sprite.setRotation(angleDegree);
            }
        }

        if(ticks % 240 == 0) {
            stopsLeft--;
        }

        try {
            if(stopsLeft < 1) {
                Game.toDraw.remove(this);
                this.tex.dispose();
            }

            if (orders.get(currentOrder) != null) {
                UnitOrder order = orders.get(currentOrder);
                if(order != null) {
                    Vector2 goalPos;

                    goalPos = new Vector2(orders.get(currentOrder).targetpos.x + 16, orders.get(currentOrder).targetpos.y + 16);
                    direction = goalPos.sub(new Vector2(this.sprite.getX(), this.sprite.getY()));

                    direction.nor();

                    // test if we have basically reached the destination region
                    // and we can delete the order for this unit.
                    // do this in a rather expensive way using a rectangle.
                    // TODO: improve?

                    testCircle = new Circle();
                    testCircle.setRadius(32);
                    testCircle.setPosition(orders.get(currentOrder).targetpos.x + 32, orders.get(currentOrder).targetpos.y + 32);
                    if(testCircle.contains(new Vector2(sprite.getX(), sprite.getY()))) {
                        this.direction = new Vector2();
                        currentOrder++;
                        this.stopsLeft--;
                        Game.money += 1 * (1.0025 * Game.money_upgrades);
                    }

                }
            }

            if (direction != null && Game.simulation_enabled) {
                this.sprite.setPosition((float) (this.sprite.getX() + (direction.x * (2 + (1.0005 * Game.speed_upgrades)))), (float) (this.sprite.getY() + (direction.y * (2 * (1.01 * Game.speed_upgrades)))));
            }
        } catch(IndexOutOfBoundsException e) {
            if(orders.size() != 0 && currentOrder != 0) {
                currentOrder = 0;
            }
        }
    }

    Unit() {
        super(new Texture("unit1.png"));
        stopsLeft = 25;
    }

    Unit(UnitOrder order) {
        super(new Texture("unit1.png"));
    }
}
