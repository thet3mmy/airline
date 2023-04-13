package page.rightshift.rtst1;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIRender {
    public static final BitmapFont font = new BitmapFont();
    public static final SpriteBatch fontBatch = new SpriteBatch();

    public static void draw() {
        fontBatch.begin();

        font.draw(fontBatch, "$" + Game.money, 16, 18);
        font.draw(fontBatch, "money upgrades (F1): " + Game.money_upgrades + " ($" + Math.ceil((30 * (1.02  * Game.money_upgrades) + 50)) + ")", 175, 18);
        font.draw(fontBatch, "speed upgrades (F2): " + Game.speed_upgrades + " ($" + Math.ceil((50 * (1.015 * Game.speed_upgrades) + 50)) + ")", 400, 18);
        font.draw(fontBatch, "simulation enabled: " + Game.simulation_enabled, 20, 472);
        font.draw(fontBatch, "airport cost: $" + Math.ceil(((Unit.orders.size() * 1.1) + 2)), 200, 472);
        font.draw(fontBatch, "planes: " + Game.toDraw.size(), 350, 472);

        fontBatch.end();
    }
}
