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

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
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
     * @Parameter: Uuid -> the uuid which the language will be set for.
     *             Locale -> the language it will be set to.*/
    public void setPlayerLanguage(@Nonnull final UUID uuid, @Nonnull final Locale language){
        String localeString = language.toLanguageTag().replace('-', '_');
        languageConfig.set(uuid.toString(), localeString);
        save(2);
    }

    /**This will return the corresponding language to the uuid set in the player_languages.yml file.
     * @Returns: Locale matching the set language.*/
    public Locale getPlayerLanguage(@Nonnull final UUID uuid){
        if (languageConfig.get(uuid.toString()) != null) {

            try {
                String localeString = languageConfig.getString(uuid.toString());
                return new Locale(Objects.requireNonNull(localeString));

            }catch (NullPointerException e){
                return getDefaultLocale();
            }
        }
        else {
            return getDefaultLocale();
        }
    }

    /**This will return the default language set in the config.yml as a locale.
     * @Returns: Locale matching the language in the config.yml, Locale.ENGLISH as default.*/
    public Locale getDefaultLocale(){
        if (WarpsAndHomes.getPlugin().getConfig().getString("default-language") != null) {
            String localeString = WarpsAndHomes.getPlugin().getConfig().getString("default-language");
            Locale locale;
            try {
                locale = Locale.forLanguageTag(localeString.replace('_', '-'));
            }catch (NullPointerException e){
                WarpsAndHomes.getPlugin().getLogger().info(WarpsAndHomes.PLUGIN_PREFIX + "No valid language in config.yml.");
                return Locale.ENGLISH;
            }
            return locale;

        }else {
            return Locale.ENGLISH;
        }
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
