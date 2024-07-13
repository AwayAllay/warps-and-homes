# warps-and-homes
_Description and features coming soon!_

**Check my github profile [here](https://github.com/AwayAllay).**

## Table of contents
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
_A detailed list of all features will be published as soon as the plugin is finished_

## Requirements

- **Minecraft Server**: This plugin runs on Minecraft servers based on [Spigot](https://www.spigotmc.org/) or [Paper](https://papermc.io/).
- **Minecraft Version**: This is still in testing.
- **Java Version**: Requires Java 8 or higher.
- **Memory**: Recommended minimum of 2 GB RAM for the server.
### My Version is Not Supported

If you can't find a fitting version of this plugin for your server:

- Download the nearest version of the plugin to your server version; most of the time, this will also work.
- Create an issue in the [Issues tab](https://github.com/AwayAllay/warps-and-homes/issues).

## Installation

1. Set up a Spigot/Bukkit/Paper server if you haven't already.
2. Download the plugin JAR file [here](https://github.com/AwayAllay).
3. Move the downloaded JAR file into the plugins folder of your server.
4. Reload the server by typing `rl confirm` into the server console.
5. Check if the plugin is correctly installed by typing `/pl` in the in-game chat or `pl` in the server console. It should appear in the shown list.
6. You're good to go!

## Usage
_Still in Development_
### Commands
_Coming Soon_
Just act as if there was a detailed, helpful text on the topic here. The actual text is still to come.

#### Game-Commands
Here will be a list of all the commands for the players. It will look like this:

`/command <cool-stuff>` - and a nice description.

##### Warp-Commands
_Every command that has to do with warping starts with `/warp`. 
You can also get an [overview](https://github.com/AwayAllay/warps-and-homes/blob/main/src/main/resources/warpCommands.png)
of all possible subcommands if you enter `/warp` in the chat._

**Command:** `/warp set <name> <isPrivate?> <displayItem> <description>`  
_Sets a warp at the players current location._  
**Parameters:** 
- `<name>`: the name of the new warp.
- `<isPrivate?>`: if true this warp can only be seen by its owner. If false it is public and can be seen by anyone.
- `<displayItem>`: The item that appears for the warp in the warp GUI.
- `<description>`: Here you can write a short description about the warp,

##### Home-Commands
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
_Currently working on making the code more understandable and shorter_
## License
WarpsAndHomes is free software under the GNU General Public License, version 3 ([GPL-3.0-only](https://www.gnu.org/licenses/gpl-3.0.html)).
The license can also be found in the files above under [LICENSE](https://github.com/AwayAllay/warps-and-homes/blob/main/LICENSE).

