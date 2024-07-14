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

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

/**This is the Translator class. It can translate your messages into the supported languages of the plugin.
 * @Parameter: YamlConfiguration -> the player_languages.yml file with the saved languages to the UUIDs.
 * TranslationManager -> the translationManager object containing all the supported languages.*/
public class Translator {

    private final YamlConfiguration languageConfig;
    private final TranslationManager translationManager;

    public Translator(YamlConfiguration languageConfig, TranslationManager translationManager) {
        this.languageConfig = languageConfig;
        this.translationManager = translationManager;
    }

    /**Sends a translated message to the player.
     * @Parameter: Player -> the recipient of the message which will get the message in his set language.
     * String -> the key of the langage.yml file under which your message is saved.*/
    public void translate(Player recipient, String messageKey){

        if (recipient == null){
            return;
        }
        UUID uuid = recipient.getUniqueId();

        String language;
        if (languageConfig.getString(uuid.toString()) != null){
            language = languageConfig.getString(uuid.toString());
        }
        else {
            language = "english";
        }

        YamlConfiguration languageFile = translationManager.getTranslation(language);

        translateMessage(languageFile, messageKey, recipient);

    }

    private void translateMessage(final YamlConfiguration languageFile, final String messageKey, final Player recipient) {

        if (languageFile.get(messageKey) != null){

            if (languageFile.isList(messageKey)){

                languageFile.getStringList(messageKey).forEach(message ->
                        recipient.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
            }else {

                recipient.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(languageFile.getString(messageKey))));

            }
        }else {
            recipient.sendMessage(ChatColor.DARK_GRAY + "[WarpsAndHomes] ... be happy^^");
        }

    }
}
