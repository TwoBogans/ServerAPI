# ServerAPI

ServerAPI is a REST API for [2b2t.com.au](https://2b2t.com.au)  
Join the Discord https://discord.gg/australia

## Usage

For a usage and a list of active endpoints, please visit the self hosted documentation.   
https://api.2b2t.com.au/api-docs

### Examples

```bash
$ curl https://api.2b2t.com.au/v1/server
```
```json
{
  "name": "2b2tAU",
  "motd": "2b2t Australia",
  "version": "1.12.2-R0.1-SNAPSHOT",
  "tps": "19.3",
  "online": 64,
  "cpus": 8,
  "uptime": 4657204,
  "totalMemory": 23622320128,
  "maxMemory": 23622320128,
  "freeMemory": 3089104896
}
```

Or get a list of players that are currently online:

```bash
$ curl https://api.2b2t.com.au/v1/players
```
```json
[
  {
    "uuid": "a68b9c6e-1606-4b0c-934f-198300d77d2b",
    "displayName": "RemainingToest"
  }
]
```