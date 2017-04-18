package ics.yudzeen.abstracto.screens.stack.apps.postfix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.apps.ApplicationsMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Game over screen
 */

class GameOverScreen extends AbstractoScreen {

    public static final String TAG = GameOverScreen.class.getName();

    private Image backgroundImage;

    private Label gameOverLabel;

    private ImageButton retryButton;
    private Label retryLabel;

    private ImageButton exitButton;
    private Label exitLabel;

    public GameOverScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initGameOverLabel();
        initRetryButton();
        initRetryLabel();
        initExitButton();
        initExitLabel();
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.TAN);
        pixmap.fill();

        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initGameOverLabel() {
        gameOverLabel = LabelFactory.createLabel("GAME OVER!", assets.fonts.defaultVeryBig, Color.WHITE);
        gameOverLabel.setPosition(GameConstants.WIDTH/2-gameOverLabel.getWidth()/2,
                GameConstants.HEIGHT/2-gameOverLabel.getHeight()/2);
    }

    private void initRetryButton() {
        retryButton = ButtonFactory.createImageButton(assets.games.retry_icon);
        retryButton.setPosition(GameConstants.WIDTH/2-retryButton.getWidth()-30, 100);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onRetryButtonClicked();
            }
        });
    }

    private void initRetryLabel() {
        retryLabel = LabelFactory.createLabel("RETRY", assets.fonts.defaultBig, Color.WHITE);
        retryLabel.setPosition(retryButton.getX()+retryButton.getWidth()/2-retryLabel.getWidth()/2,
                retryButton.getY()-retryLabel.getHeight()-10);
    }

    private void initExitButton() {
        exitButton = ButtonFactory.createImageButton(assets.games.exit_icon);
        exitButton.setPosition(GameConstants.WIDTH/2+30, 100);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onExitButtonClicked();
            }
        });
    }

    private void initExitLabel() {
        exitLabel = LabelFactory.createLabel("EXIT", assets.fonts.defaultBig, Color.WHITE);
        exitLabel.setPosition(exitButton.getX()+(exitButton.getWidth()-exitLabel.getWidth())/2,
                exitButton.getY()-exitLabel.getHeight()-10);
    }

    private void onRetryButtonClicked() {
        game.setScreen(new PostfixExpressionGameScreen(game));
    }

    private void onExitButtonClicked() {
        game.setScreen(new ApplicationsMapScreen(game));
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        stage.addActor(backgroundImage);

        stage.addActor(gameOverLabel);

        stage.addActor(retryButton);
        stage.addActor(retryLabel);

        stage.addActor(exitButton);
        stage.addActor(exitLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }

}
