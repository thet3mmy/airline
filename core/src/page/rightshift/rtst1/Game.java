package page.rightshift.rtst1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;

public class Game extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static OrthographicCamera cam;
	public static Texture circleTex;
	public static Texture backdrop;

	public static LinkedList<Unit> toDraw;
	public static LinkedList<UnitOrder> c_circles;
	public static BitmapFont font;
	public static long money;
	public static int money_upgrades;
	public static int speed_upgrades;
	public static int tick;

	public static boolean simulation_enabled;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		circleTex = new Texture("circle.png");
		backdrop = new Texture("bg.png");
		font = new BitmapFont();

		money = 32;
		money_upgrades = 1;
		speed_upgrades = 1;
		simulation_enabled = false;

		toDraw = new LinkedList<>();
		c_circles = new LinkedList<>();
		Unit.orders = new LinkedList<>();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		c_circles = (LinkedList<UnitOrder>) Unit.orders.clone();
		InputController.handleControls();
		tick++;

		batch.begin();

		batch.draw(backdrop, 0, 0);

		font.draw(batch, "$" + money, 16, 18);
		font.draw(batch, "money upgrades (F1): " + money_upgrades + " ($" + (30 * (1.02  * Game.money_upgrades) + 50) + ")", 175, 18);
		font.draw(batch, "speed upgrades (F2): " + speed_upgrades + " ($" + (50 * (1.015 * Game.speed_upgrades) + 50) + ")", 400, 18);
		font.draw(batch, "simulation enabled: " + simulation_enabled, 20, 472);
		font.draw(batch, "airport cost: $" + ((Unit.orders.size() * 1.1) + 2), 200, 472);
		font.draw(batch, "planes: " + toDraw.size(), 350, 472);

		for(Actor a: toDraw) {
			a.sprite.draw(batch);
			a.think();
		}

		try {
			if(c_circles.size() > 0) {
				for(int dc = 0; dc < c_circles.size(); dc++) {
					if(c_circles.get(dc) != null) {
						batch.draw(circleTex, c_circles.get(dc).targetpos.x, c_circles.get(dc).targetpos.y);
						font.draw(batch, String.valueOf(dc), c_circles.get(dc).targetpos.x + 32, c_circles.get(dc).targetpos.y + 32);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
