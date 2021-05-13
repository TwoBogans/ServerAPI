# ServerAPI

ServerAPI is a REST API for Bukkit, Spigot, and PaperMC Minecraft servers. Designed for 2b2t.com.au

# Discord

Join the Discord https://discord.gg/australia

# Usage

For a list of endpoints visit https://api.2b2t.com.au/swagger

### Examples

```bash
$ curl https://api.2b2t.com.au/v1/server
```

```json
{
  "name": "Paper",
  "motd": "This is my MOTD",
  "version": "git-Paper-89 (MC: 1.15.2)",
  "bukkitVersion": "1.15.2-R0.1-SNAPSHOT",
  "health": {
    "cpus": 4,
    "uptime": 744,
    "totalMemory": 2010644480,
    "maxMemory": 2010644480,
    "freeMemory": 1332389360
  },
  "bannedIps": [],
  "bannedPlayers": [
    {
      "target": "phybros",
      "source": "Server"
    }
  ]
}
```

Or get a list of players that are currently online:

```bash
$ curl https://api.2b2t.com.au/v1/players
```

```json
[
  {
    "uuid": "55f584e4-f095-48e0-bb8a-eb5c87ffe494",
    "displayName": "phybros",
    "address": "localhost",
    "port": 58529,
    "exhaustion": 3.5640976,
    "exp": 0.45454547,
    "whitelisted": false,
    "banned": false,
    "op": true
  }
]
```