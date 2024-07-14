/*
 * WarpsAndHomes - Minecraft plugin
 * Copyright (C) 2024 AwayAllay
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package me.lukaos187.warpsandhomes.util.translationUtils;

import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**This is the class that saves the set language of a player. It allows you to set a language of a player, or get the
 *  set language.
 * @Parameter: File dataFolder -> the object representing the plugin Folder in the server/plugins folder
 *  */
public class PlayerLanguageManager {

    private File languageFile;
    private YamlConfiguration languageConfig;

    public PlayerLanguageManager(File dataFolder) {//data Folder is the folder in the plugins folder with the plugin name.
        languageFile = new File(dataFolder, "player_languages.yml");
        languageConfig = YamlConfiguration.loadConfiguration(languageFile);
    }

    /**This sets the language in the player_languages.yml file to the given language.
     * @Important: the language must equal the name of the corresponding .yml file!
     * @Parameter: uuid -> the uuid which the language will be set for.
     *             language -> the language it will be set to.*/
    public void setPlayerLanguage(@Nonnull final UUID uuid, @Nonnull final String language){
        languageConfig.set(uuid.toString(), language);
        save(2);
    }

    /**This will return the corresponding language to the uuid set in the player_languages.yml file.
     * @Returns: String matching the set language.*/
    public String getPlayerLanguage(@Nonnull final UUID uuid){
        if (languageConfig.get(uuid.toString()) != null)
            return languageConfig.getString(uuid.toString());
        else
            return "english";
    }

    private void save(int savingTries) {
        try {
            languageConfig.save(languageFile);
        }catch (IOException e){ // if it fails it tries to save again for the given amount of tries.
            if (savingTries > 0){
                save(savingTries--);
            }
        }
    }
}
