# JartexChatReporter
After reporting many players, I decided to automate the process a bit.

This program is able to create quick reports and sends then to jartex using https requests.
It helps uploading screenshots and can also delete then, so that the screenshot folder is not full with reports.

# Setup
Download the latest release. Drag the zip file in a external folder and unpack it.
Open the file `config.properties` and type in after `imgur_client_id=` your imgur client id.
Here is a tutorial how to get your imgur client id: <br>
[https://dubble.so/guides/how-to-get-imgur-client-id-purlxhv84a0m3mlsiak7]()

# Config
| Key                       | Description                                                                                           |
|---------------------------|-------------------------------------------------------------------------------------------------------|
| default_reporter_name     | This name is automatically inserted after sending                                                     |
| default_gamemode_selected | This gamemode is automatically inserted after sending. List of the available gamemodes is shown below |
| delete_file_after_report  | Should the screenshot be deleted automatically? (true/false)                                          |
| imgur_client_id           | The imgur client id                                                                                   |
<br>

| Gamemode          |
|-------------------|
| Minigames         |
| Practice          |
| Lobby             |
| Kitpvp            |
| Immortal Factions |
| Skyblock Dreams   |
| Skyblock Fantasy  |
| Prison            |
| Survivial         |
| Lifesteal         |
| Gens              |
