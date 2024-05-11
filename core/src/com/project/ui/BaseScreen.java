package com.project.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class BaseScreen extends Group {
    protected final UiApp app;
    protected final Table table = new Table();
    public static float defaultPad;
    public float dur;


    protected BaseScreen(UiApp app) {
        this.app = app;
        this.dur = app.defaultDur;
        defaultPad = Math.round(Math.max(app.height, app.width) * .02f);
        table.defaults().pad(defaultPad);
        table.setSize(app.width, app.height);
        this.addActor(table);
    }

    public BaseScreen show() {
        return this;
    }

    protected void screenOut() {
        float xPos = -app.width;
        MoveToAction action = Actions.moveTo(xPos, 0f, dur);
        addAction(action);
    }

    public void hide() {
    }
}
