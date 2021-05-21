/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gamingroom.gui.solitaire;
import com.codename1.io.Preferences;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
/**
 *
 * @author Dah
 */
public class ToggleCommand extends Command {
    private FontImage unchecked, checked;
    private boolean state;
    private String persistenceKey;
    public ToggleCommand(String name, boolean defaultState, String persistenceKey) {
        super(name);
        this.persistenceKey = persistenceKey;
        Style style = UIManager.getInstance().getComponentStyle("LabelIcon");
        unchecked = FontImage.create("\ue911", style);
        checked = FontImage.create("\ue912", style);
        state = Preferences.get(persistenceKey, defaultState);
        if(state) {
            setIcon(checked);
        } else {
            setIcon(unchecked);
        }
    }

    @Override
    public final void actionPerformed(ActionEvent evt) {
        state = !state;
        Preferences.set(persistenceKey, state);
        if(state) {
            setIcon(checked);
        } else {
            setIcon(unchecked);
        }
        onToggle(state);
    }
    
    public void onToggle(boolean newVal) {}
    
}
