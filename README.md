# ServerAPI

ServerAPI is a REST API for [2b2t.au](https://2b2t.au)  
Join the Discord https://discord.gg/popbob

## Usage

For a usage and a list of active endpoints, please visit the self hosted documentation.   
https://api.2b2t.au/api-docs

### Examples

```bash
$ curl https://api.2b2t.au/v1/server
```
```json
{
  "name": "Dionysus",
  "version": "1.12.2-R0.1-SNAPSHOT",
  "uptime": "6 hours, 25 minutes, 3 seconds",
  "online": 28,
  "performance": {
    "tps": 19.87,
    "uptimeLong": 23103252,
    "totalMemory": 68719476736,
    "maxMemory": 68719476736,
    "freeMemory": 29102015144,
    "cpus": 12
  }
}
```
