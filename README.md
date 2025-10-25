### English

> DebugStick lets players safely modify block properties with fine-grained permissions to prevent misuse.

### Svenska

> DebugStick låter spelare säkert ändra blockegenskaper med detaljerade behörigheter för att förhindra missbruk.

### Suomi

> DebugStick sallii pelaajien muokata lohkojen ominaisuuksia turvallisesti käyttöoikeuksien avulla väärinkäytön estämiseksi.
---

**Version:** 1.4.0

**Author:** Kanuunankuula [@If_Master]

**API:** Paper / Folia 1.21+

**Description:** Advanced debug stick plugin with granular permission control.

---

## Overview

**DebugStick** is a powerful yet safe alternative to the vanilla Minecraft debug stick.
It allows server owners to grant limited and specific permissions to players for modifying block properties, without the risk of abuse.

Players can interact with blocks using the debug stick, but their abilities are restricted by permission nodes, ensuring that only authorized modifications are possible.

---

## Features

* Granular permission control for every editable block property
* Fully compatible with **Paper 1.20–1.21+** and **Folia**
* Lightweight and optimized for performance
* Safe for survival or creative servers
* `/cds` help overview and reload support
* Complete configuration for flexible setups

---

## Commands

| Command              | Description                               | Permission          | Default |
| -------------------- | ----------------------------------------- | ------------------- | ------- |
| `/debugstick`        | Gives the player a Debug Stick            | `debugstick.use`    | OP      |
| `/cds`               | Displays all DebugStick commands and help | `debugstick.help`   | true    |
| `/debugstick reload` | Reloads the plugin configuration          | `debugstick.reload` | OP      |

Aliases: `/ds`, `/dstick`

---

## Permission Nodes

The plugin provides over **60 granular permissions** for different block properties.

| Permission                                            | Description                                          | Default |
| ----------------------------------------------------- | ---------------------------------------------------- | ------- |
| `debugstick.use`                                      | Allows using the DebugStick                          | OP      |
| `debugstick.command`                                  | Allows spawning a DebugStick with `/debugstick`      | OP      |
| `debugstick.help`                                     | Allows viewing the help menu                         | true    |
| `debugstick.reload`                                   | Allows reloading the plugin                          | OP      |
| `debugstick.property.*`                               | Allows modifying all block properties                | OP      |
| `debugstick.property.waterlogged`                     | Allows editing waterlogging                          | OP      |
| `debugstick.property.facing`                          | Allows editing facing direction                      | OP      |
| `debugstick.property.axis`                            | Allows editing axis                                  | OP      |
| `debugstick.property.lit`                             | Allows toggling light sources                        | OP      |
| `debugstick.property.rotation`                        | Allows rotating directional blocks                   | OP      |
| `debugstick.property.power`                           | Allows editing redstone power level                  | OP      |
| `debugstick.property.half`                            | Allows editing top/bottom half (stairs, doors, etc.) | OP      |
| `debugstick.property.open`                            | Allows toggling open/closed states                   | OP      |
| *(...and many more — see [[plugin.yml]([url](https://github.com/If-Master/DebugStick/blob/main/src/main/resources/plugin.yml))] for full list)* |                                                      |         |

---

## Configuration

The plugin generates a configuration file at:

```
plugins/DebugStick/config.yml
```

You can customize:

* Which block properties are allowed for editing
* Permission defaults
* Display messages and filters

Use `/debugstick reload` after editing the configuration file to apply changes.

---

## Installation

1. Download the latest **DebugStick.jar** release
2. Place it in your server’s `plugins/` folder
3. Start or restart your Paper or Folia server
4. (Optional) Edit `config.yml` to customize behavior
5. Grant appropriate permissions to your players

---

## Example Usage

* A builder may be given access only to `debugstick.property.facing` and `debugstick.property.axis`
* An admin may have full access with `debugstick.property.*`
* Regular players can be given limited property permissions for decorative editing

---

## Credits

Developed by **Kanuunankuula**
[@If_Master]
© 2025 — All Rights Reserved

---

## License

This plugin and its source code are the exclusive property of **Kanuunankuula**.
You may **not** redistribute, modify, or use any part of this source code for **personal or commercial gain**.
You may, however, use the compiled plugin freely on your Minecraft server.
