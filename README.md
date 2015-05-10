# ClashHomes
ClashHomes Minecraft/Bukkit with basic MultiHome Support

Permissions
===========
`ch.basic.home` - Allow Home Teleportation

`ch.basic.sethome` - Allow Setting of a Home

`ch.basic.spawn` - Allow Teleporting to Spawn

`ch.admin.setspawn` - Gives permission for `/setspawn` command

`ch.multi` - Allow setting of unlimited homes

`ch.basic.list` - List Homes

Todo List
=========
* ~~Basic Home Support~~ (done)

* ~~Basic Spawn Point Support~~ (done)

* ~~Multi-Homes~~ (done)

* ~~List Homes~~ (done)

* ~~Delete Homes~~ (done)

* Limit Homes

* Admin Commands

Commands
========
`/setspawn` - Set Spawn for Current World

`/spawn` - Teleport to spawn for Current World

`/sethome [homename]` - Set Home for Current Player [Home Name is for those with `ch.multi`]

`/delhome [homename]` - Delete Own User's Home or `homename` [Home Name is for those with `ch.multi`]

`/home [homename]` - Teleport to default home or to given home name

`/homes` - List Homes for Current Player

Warning
=======
If you use this plugin, be warned that the File Backend is UNTESTED and may not work. Use the MySQL Backend for best comparability.