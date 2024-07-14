package me.lukaos187.warpsandhomes.util;

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
import me.lukaos187.warpsandhomes.WarpsAndHomes;

import java.util.Random;

public class Messages {

    private final WarpsAndHomes plugin;

    public Messages(WarpsAndHomes plugin) {
        this.plugin = plugin;
    }


    public void checkConfigErrorNuke(){

        plugin.getServer().getLogger().info(
                "                                    \n" +
                "              _ ._  _ , _ ._             \n" +
                "            (_ ' ( `  )_  .__)           \n" +
                "          ( (  (    )   `)  ) _)         \n" +
                "         (__ (_   (_ . _) _) ,__)        \n" +
                "             `~~`\\ ' . /`~~`            \n" +
                "             ,::: ;   ; :::,             \n" +
                "            ':::::::::::::::'            \n" +
                " _______jgs______/_ __ \\________________\n" +
                "|                                       |\n" +
                "| [WarpsAndHomes] Check the config.yml! |\n" +
                "|_______________________________________|\n");

    }

    public void randomHelloMessage(){
        int randomNumber = new Random().nextInt(5);
        switch (randomNumber){
            case 0 -> hello0();
            case 1 -> hello1();
            case 2 -> hello2();
            case 3 -> hello3();
            case 4 -> hello4();
        }
    }

    private void hello0(){

        plugin.getServer().getLogger().info(
                "                                                        \n" +
                        " __^__                                        __^__  \n" +
                        "( ___ )--------------------------------------( ___ ) \n" +
                        " | / | Welcome to WarpsAndHomes - Have fun!!! | \\ | \n" +
                        " |___|                                        |___|  \n" +
                        "(_____)--------------------------------------(_____)"
        );


    }
    private void hello1(){
        plugin.getServer().getLogger().info(
                "                                    \n" +
                        " /\\ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! /\\ \n" +
                        "|! |                                        |! | \n" +
                        "|! | Welcome to WarpsAndHomes - Have fun!!! |! | \n" +
                        "|__|                                        |__| \n" +
                        "(__)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!(__)"
        );
    }
    private void hello2(){
        plugin.getServer().getLogger().info(
                "                                    \n" +
                        "                  \\\\\\///\n" +
                        "                 / _  _ \\\n" +
                        "               (| (.)(.) |)\n" +
                        ".------------.OOOo--()--oOOO.------------.\n" +
                        "|                                        |\n" +
                        "| Welcome to WarpsAndHomes - Have fun!!! |\n" +
                        "|                                        |\n" +
                        "'------------.oooO-----------------------'\n" +
                        "              (   )   Oooo.\n" +
                        "               \\ (    (   )\n" +
                        "                \\_)    ) /\n" +
                        "                      (_/"
        );
    }
    private void hello3(){
        plugin.getServer().getLogger().info(
                "                                                   \n" +
                        " __________________________________________\n" +
                        "/\\                                         \\\n" +
                        "\\_| Welcome to WarpsAndHomes - Have fun!!! |\n" +
                        "  |                                        |\n" +
                        "  |   _____________________________________|_\n" +
                        "   \\_/_______________________________________/"
        );
    }
    private void hello4(){
        plugin.getServer().getLogger().info(
                "                                                   \n" +
                        " __________________________________________\n" +
                        "/                                          \\\n" +
                        "|  Welcome to WarpsAndHomes - Have fun!!!  |\n" +
                        "\\____________________________________  __'\\\n" +
                        "                                     |/   \\\\\n" +
                        "                                      \\    \\\\  .\n" +
                        "                                           |\\\\/|\n" +
                        "                                           / \" '\\\n" +
                        "                                           . .   .\n" +
                        "                                          /    ) |\n" +
                        "                                         '  _.'  |\n" +
                        "                                         '-'/    \\"
        );
    }

    public void randomGoodbyeMessage(){
        int randomNumber = new Random().nextInt(5);
        switch (randomNumber){
            case 0 -> gb0();
            case 1 -> gb1();
            case 2 -> gb2();
            case 3 -> gb3();
            case 4 -> gb4();
        }
    }

    private void gb4() {
        plugin.getServer().getLogger().info(
                "                                 \n" +
                        " .+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.\n" +
                        "(                                 )\n" +
                        " )   WarpsAndHomes - Goodbye!    (\n" +
                        "(                                 )\n" +
                        " \"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\"+.+\""
        );
    }

    private void gb3() {
        plugin.getServer().getLogger().info(
                "                                 \n" +
                        " ___________________________\n" +
                        "/                           \\\n" +
                        "|  WarpsAndHomes - Goodbye! |\n" +
                        "\\                           /\n" +
                        " ---------------------------\n" +
                        "    \\\n" +
                        "     \\\n" +
                        "         .--.\n" +
                        "        |o_o |\n" +
                        "        |:_/ |\n" +
                        "       //   \\ \\\n" +
                        "      (|     | )\n" +
                        "     /'\\_   _/`\\\n" +
                        "     \\___)=(___/"
        );
    }

    private void gb2() {
        plugin.getServer().getLogger().info(
                "                                 \n" +
                        "                 .\n" +
                        "            .    |    .\n" +
                        "             \\   |   /\n" +
                        "         '.   \\  '  /   .'\n" +
                        "           '. .'```'. .'\n" +
                        "<>....:::::::`.......`:::::::...<>\n" +
                        "<>:                            :<>\n" +
                        "<>:  WarpsAndHomes - Goodbye!  :<>\n" +
                        "<>:                            :<>\n" +
                        "<>:............................:<> jgs\n" +
                        "<><><><><><><><><><><><><><><><><>"
        );
    }

    private void gb1() {
        plugin.getServer().getLogger().info(
                "                                 \n" +
                        "      ,\n" +
                        "  /\\^/`\\\n" +
                        " | \\/   |\n" +
                        " | |    |                               jgs\n" +
                        " \\ \\    /                             _ _\n" +
                        "  '\\\\//'                            _{ ' }_\n" +
                        "    ||     WarpsAndHomes - Goodbye!{ `.!.` }\n" +
                        "    ||                             ',_/Y\\_,'\n" +
                        "    ||  ,                            {_,_}\n" +
                        "|\\  ||  |\\                             |\n" +
                        "| | ||  | |                          (\\|  /)\n" +
                        "| | || / /                            \\| //\n" +
                        " \\ \\||/ /                              |//\n" +
                        "  `\\\\//`   \\   \\./    \\\\   \\./    \\ \\\\ |/ /\n" +
                        " ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        );
    }

    private void gb0() {
        plugin.getServer().getLogger().info(
                "                                 \n" +
                        "          __   _,--=\"=--,_   __\n" +
                        "         /  \\.\"    .-.    \"./  \\\n" +
                        "        /  ,/  _   : :   _  \\/` \\\n" +
                        "        \\  `| /o\\  :_:  /o\\ |\\__/\n" +
                        "         `-'| :=\"~` _ `~\"=: |\n" +
                        "            \\`     (_)     `/ jgs\n" +
                        "     .-\"-.   \\      |      /   .-\"-.\n" +
                        ".---{     }--|  /,.-'-.,\\  |--{     }---.\n" +
                        " )  (_)_)_)  \\_/`~-===-~`\\_/  (_(_(_)  (\n" +
                        "(       WarpsAndHomes - Goodbye!        )\n" +
                        " )                                     (\n" +
                        "'---------------------------------------'"
        );
    }

}
