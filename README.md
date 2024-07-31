# warps-and-homes
***
Discover ultimate freedom on your Minecraft server with our Warp & Home Plugin! Easily set warp points for players to 
instantly teleport to and create personal home points to return to your favorite spots anytime. Whether for exploration,
epic adventures, or just quick building - with this plugin, anything is possible!

_For more cool stuff_**:** Check out my [github profile](https://github.com/AwayAllay).

## Table of contents
***
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Commands](#commands)
    - [Game-Commands](#game-commands)
      - [Warp-Commands](#warp-commands)
      - [Home-Commands](#home-commands)
    - [Config-Commands](#config-commands)
  - [GUI](#gui)
  - [Config.yml](#configyml)
- [Current](#current)
- [License](#license)

## Features
***
**⏳Cooldowns**: Cooldowns for warping and a delay after taking damage before warping again.  
**🔒Permission**: Own permission to get access to the config-management commands.  
**📜Simple Commands**: Intuitive and easy-to-remember commands for quick teleportation.  
**⚙️Customizable**: Many configuration options to perfectly tailor the plugin to your server!  
**🎆 Warp Animations**: Cool warp animations with particles that can match player skin.  
**🔓 Private and Public Warps**:Private and public warps for personal or server-community usage.  
**🌐 Multiple Language Support**: Full support for multiple languages.  
**AND MUCH MORE**

## Requirements
***
- **Minecraft Server**: This plugin runs on Minecraft servers based on [Spigot](https://www.spigotmc.org/) or [Paper](https://papermc.io/).
- **Minecraft Version**: This is still in testing.
- **Java Version**: Requires Java 8 or higher.
- **Memory**: Recommended minimum of 2 GB RAM for the server.
### My Version is Not Supported

If you can't find a fitting version of this plugin for your server:

- Download the nearest version of the plugin to your server version; most of the time, this will also work.
- Create an issue in the [Issues tab](https://github.com/AwayAllay/warps-and-homes/issues).
- If you are a developer you can also create a pull request.

## Installation
***
1. Set up a Spigot/Bukkit/Paper server if you haven't already.
2. Download the plugin JAR file [here](https://github.com/AwayAllay).
3. Move the downloaded JAR file into the plugins folder of your server.
4. Reload the server by typing `rl confirm` into the server console.
5. Check if the plugin is correctly installed by typing `/pl` in the in-game chat or `pl` in the server console. It should appear in the shown list.
6. You're good to go!

## Usage
***
_Commands are available for homes, warps, language, and configurations, most also accessible through a user-friendly GUI version._
### Commands
There are two types of commands, commands that manage the config.yml ([Config-Commands](#config-commands)) 
and the commands for the players ([Game-Commands](#game-commands)).

#### Game-Commands
***
##### Warp-Commands
A list with short descriptions of the commands can be output in the chat using `/warp`.

**Command:** `/warp set <name> <isPrivate?> <displayItem> <description>`  
**Description:** _Sets a warp at the players current location._  
**Parameters:** 
- `<name>`: the name of the new warp.
- `<isPrivate?>`: _True_ -> warp is only visible to its owner. _False_ -> it is visible to anyone.
- `<displayItem>`: The item that appears for the warp in the warp GUI.
- `<description>`: Here you can write a short description about the warp,




##### Home-Commands
***
And commands for the homes stuff.

#### Config-Commands

And these commands only admins are allowed to use, because they directly affect the config.yml file.

### GUI
_Soon_
### Config.yml
Detailed information about the individual settings can be found as comments in the 
[config.yml](https://github.com/AwayAllay/warps-and-homes/blob/main/src/main/resources/config.yml) file. If you 
still have any questions or issues, please create a issue in the [issues tab](https://github.com/AwayAllay/warps-and-homes/issues).

## Current
***
**Currently working on supporting multiple languages!**
## License
***
WarpsAndHomes is free software under the GNU General Public License, version 3 ([GPL-3.0-only](https://www.gnu.org/licenses/gpl-3.0.html)).
The license can also be found in the files above under [LICENSE](https://github.com/AwayAllay/warps-and-homes/blob/main/LICENSE).

