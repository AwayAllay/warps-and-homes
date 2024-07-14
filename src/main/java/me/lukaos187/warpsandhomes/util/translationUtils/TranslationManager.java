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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**The TranslationManager loads all the language.yml files, copies them into the dataFolder and saves them in a
 * Map<String, YamlConfiguration> translations.
 * @Parameter: File dataFolder -> the object representing the plugin Folder in the server/plugins folder*/
public class TranslationManager {

    /**Saved language.yml files to the corresponding language name.*/
    private final Map<String, YamlConfiguration> translations = new HashMap<>();

    public TranslationManager(File dataFolder) {
        loadTranslations(dataFolder, "english.yml");
        loadTranslations(dataFolder, "german.yml");
    }

    private void loadTranslations(final File dataFolder, String fileName) {
        File file = new File(dataFolder, "languages/" + fileName);
        if (file.exists()){
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            String language = fileName.replace(".yml", "");

            translations.put(language, config);
        }
    }

    /**Returns a YamlConfiguration with all the messages in the given language.
     * @Parameter: String language -> the language corresponding to the given string.
     * @Returns: YamlConfiguration with all the messages in the language.
     * english.yml if language is not found.*/
    public YamlConfiguration getTranslation(String language) {
        if (translations.containsKey(language)) {
            return translations.get(language);
        }else {
            return translations.get("english");
        }
    }
}
