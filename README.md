# ActionBarDisplayX
A Minecraft Spigot plugin which allows server administrators to display a message on the player's HUD at the Action Bar which updates at a configurable rate, if you would like a timestamp.

## config.yml
```yaml
# If you choose to append the timestamp, don't forget to reset or choose a different colour code
message: "Edit this message in plugins/ActionBarDisplayX/config.yml"

# Append SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z") to the end of
append-timestamp: false

# Values below are unable to be changed while the server is running

# Is the plugin enabled?
enabled: true

# How often (in ticks) should the message update
period: 20

# How long (in ticks) should the message be delayed
delay: 0

# Change how the timestamp looks
# For more information: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/text/SimpleDateFormat.html
timestamp-format: "dd.MM.yyyy 'at' HH:mm:ss z"
```
