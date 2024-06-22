# JartexChatReporter
After reporting many players, I decided to automate the process a bit.

This program is able to create quick reports and sends then to jartex using http requests.
It helps to upload screenshots to an image uploading service and can also delete then, so that the screenshot folder is not full with reports.

# Setup
Download the latest release. Drag the zip file in a external folder and unpack it.
Now open the start file and then a gui shows up. 
Log in (via credentials or web cookies) and then you are ready.

## Imgur
Currently, you can only use [Freeimage.host](https://Freeimage.host) to upload images. 
That's because you need for other services api keys but not for freeimage.
Here I am going to explain how to set up these services.
<br>
<br>
Open the file `config.properties` and type in after `imgur_client_id=` your imgur client id.
Here is a tutorial how to get your imgur client id: <br>
[https://dubble.so/guides/how-to-get-imgur-client-id-purlxhv84a0m3mlsiak7](https://dubble.so/guides/how-to-get-imgur-client-id-purlxhv84a0m3mlsiak7)

## Imgbb
Create an Imgbb account and log in. Go to [https://api.imgbb.com](https://api.imgbb.com) and copy your api key.  
Open the file `config.properties` and type in after `imgbb_api_key=` your api key.

# Config
| Key                       | Description                                                                                           |
|---------------------------|-------------------------------------------------------------------------------------------------------|
| default_reporter_name     | This name is automatically inserted after sending                                                     |
| default_gamemode_selected | This gamemode is automatically inserted after sending. List of the available gamemodes is shown below |
| delete_file_after_report  | Should the screenshot be deleted automatically? (true/false)                                          |
| default_upload_service    | The default upload service. This is imgur, imgbb or Free Image Host                                   |
| login_xf_session          | The xf_session cookie. Can be retrived by browser extension                                           |
| login_xf_user             | The xf_user cookie. Can be retrived by browser extension                                              |
| imgur_client_id           | The imgur client id                                                                                   |           
| imgbb_api_key             | The imgbb api key                                                                                     |
| time_limit                | The maximum time it should take to send a report (in seconds)                                         |
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
