/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.map;

/*import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.grafik.SpriteVisual;*/

/**
 *
 * @author paul.kuschfeldt
 */
public class PathUp extends Tile {

    public static int width;
    public static int height;

    //private SpriteVisual visual;

    //private static final Texture TEXblack = new Texture(Gdx.files.local("assets\\img\\map\\black.png"));
    //private static final Texture TEXwhite = new Texture(Gdx.files.local("assets\\img\\map\\white.png"));

    public PathUp(boolean b, int xBlock, int yBlock, int xTile, int yTile) {
        open = b;
        blockPositionX = xBlock;
        blockPositionY = yBlock;
        //tilePositionX = GAMESCREEN_SINGLETON.game.world.translateTileToCoordinate(xTile);
        //tilePositionY = GAMESCREEN_SINGLETON.game.world.translateTileToCoordinate(yTile);

        if (open) {
            //visual = new SpriteVisual(new Sprite(TEXwhite));
            //GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        } else {
            //visual = new SpriteVisual(new Sprite(TEXblack));
            //GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
        }
        //visual.setPosition(tilePositionX+ blockPositionX, tilePositionY+blockPositionY);
    }
}
