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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;

public class Translator {

    private final PlayerLanguageManager playerLanguageManager;

    public Translator(PlayerLanguageManager playerLanguageManager) {
        this.playerLanguageManager = playerLanguageManager;
    }

    /**Sends a translated message to the player.
     * @Parameter: Player -> the recipient of the message which will get the message in his set language.
     * String -> the key of the message you want to send to the player.*/
    public void translate(Player recipient, String messageKey, Object... args){

        if (recipient == null){
            return;
        }
        UUID uuid = recipient.getUniqueId();
        Locale language = playerLanguageManager.getPlayerLanguage(uuid);

        ResourceBundle messages = ResourceBundle.getBundle("me.lukaos187.warpsandhomes.messages", language, WarpsAndHomes.getPlugin().getClass().getClassLoader());

        sendMessage(recipient, messageKey, messages, 2, args);
    }

    private void sendMessage(final Player recipient, final String messageKey, final ResourceBundle messages,
                             int sendingTries, Object... args) {

        try{

            String message = messages.getString(messageKey);

            if (args != null){

                String formatted = MessageFormat.format(message, args);
                recipient.sendMessage(ChatColor.translateAlternateColorCodes('&', formatted));//Allows placeholders such as {0} {1}

            }else {
                recipient.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }

        }catch (NullPointerException | MissingResourceException | ClassCastException e){
            if (sendingTries > 0){
                sendMessage(recipient, messageKey, messages, sendingTries--);
            }
        }
    }
}
