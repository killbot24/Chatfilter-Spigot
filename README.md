# Chatfilter-Spigot
This program is designed to work on 1.7.10-1.16.5 paper. It will monitor players useing  chat, Signs, Anvils, Messages. These will be either flaged as possible flag or a Mute.


### Possible Flags

Possible flags will add the player to a active watchlist and log the players message and source they were flaged for it will also not send this message. It will look like it has sent from the players side. This will be added to a player warning file generated in the warning folder. A player can be removed from this watchlist by either removeing them from the file or useing the command in game or via console /chatfilter unflag <Player>
  
### Mutes
Mutes are confirmed player hits this happens when any word is 100% detected in a players message. The player will then be muted their message will be logged. They will be told they are muted and given the link to appeal & told the reason.
  
  
### Config
  The config allows the setting of the url to be shown and the blocked words
  Word,Reason  
  Example "penguin, he have flipper" 
  This means anytime a player types penguin it will mute them for reason he have flipper
