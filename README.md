# SAEGG-Game-Utility
CS370 Game Utility Project

This repo is for the CS370 Fall 2019 project, Group SAEGG&CO.

## Project Structure
- Remember to create a new activity whenever necessary
- Name new activities in the form `meaningfulNameActivity`
- Android Studio builds a layout by default on activity creation, but ensure the layout is named after its according Activity.
    - The layout for the above activity would be `activity_meaningful_name`
- Make use of the resource files in values (strings, dimens, etc). This is good Android practice.

## Instructions to run backend server:
1. Install XAMPP.
2. Start a mySQL and Apache Server
3. Open browser and type `http://localhost/phpmyadmin/index.php`
    - [This video](https://www.youtube.com/watch?v=f5nRZ2JffuA) was helpful reference. Likely similar setup for other OS.
4. Click the Databases tab towards the top left corner and create a new Database.
    - Make Database name `review_database`
    - Leave the drop-down menu as default
    - click Create
5. Create a table named `user` with 8 columns
    - For reference, [make it exactly like this](https://imgur.com/a/5tKwkLL) (note any checkmarks and other values)
6. Once this is complete, open the SAEGG project in Android Studio
7. Open the Terminal in Android Studio (tab typically located at the bottom of the IDE)
8. Enter the command `node index.js`

Note: After working for a while, I was getting weird errors. I checked the port running after the
    "node index.js" command had stopped running, likely because of a loss of internet connection.
    If this happens, just run the command again. You may have to log back into phpMyAdmin too.

Todo: find easier way to share local databases
