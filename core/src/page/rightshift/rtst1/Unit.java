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

    @Override
    public void think() {
        ticks++;

        try {
            if (orders.get(currentOrder) != null) {
                calculateDirection(orders.get(currentOrder));
            }

            if (direction != null && Game.simulation_enabled) {
                this.sprite.setPosition((float) (this.sprite.getX() + (direction.x * (2 * (1.01 * Game.speed_upgrades)))), (float) (this.sprite.getY() + (direction.y * (2 * (1.01 * Game.speed_upgrades)))));
            }
        } catch(IndexOutOfBoundsException e) {
            if(orders.size() != 0 && currentOrder != 0) {
                orders = new LinkedList<>();
                currentOrder = 0;
            }
        }
    }

    private void calculateDirection(UnitOrder order) {
        if(order != null) {
            Vector2 goalPos;

            goalPos = new Vector2(orders.get(currentOrder).targetpos.x + 16, orders.get(currentOrder).targetpos.y + 16);
            direction = goalPos.sub(new Vector2(this.sprite.getX(), this.sprite.getY()));

            direction.nor();

            double angleRadians = Math.atan2(orders.get(currentOrder).targetpos.y - this.sprite.getY(),
                    orders.get(currentOrder).targetpos.x - this.sprite.getX());
            float angleDegree = (float)angleRadians * MathUtils.radiansToDegrees - 90;
            this.sprite.setRotation(angleDegree);

            // test if we have basically reached the destination region
            // and we can delete the order for this unit.
            // do this in a rather expensive way using a rectangle.
            // TODO: improve?

            testCircle = new Circle();
            testCircle.setRadius(32);
            testCircle.setPosition(orders.get(currentOrder).targetpos.x + 32, orders.get(currentOrder).targetpos.y + 32);
            if(testCircle.contains(new Vector2(sprite.getX(), sprite.getY()))) {this.direction = new Vector2(); currentOrder++; Game.money += 10 * (1.01 * Game.money_upgrades);}

        }
    }

    public void giveOrder(UnitOrder order) {
        orders.add(order);
        calculateDirection(order);
    }

    Unit() {
        super(new Texture("unit1.png"));
    }

    Unit(UnitOrder order) {
        super(new Texture("unit1.png"));
    }
}
