# Your Movie Catalogue

<img src="https://github.com/CumiTerbang/YourMovieCatalogue/blob/master/readme_assets/screenshot_1.png" width="200" height="355,56">
<img src="https://github.com/CumiTerbang/YourMovieCatalogue/blob/master/readme_assets/screenshot_2.png" width="200" height="355,56">
<img src="https://github.com/CumiTerbang/YourMovieCatalogue/blob/master/readme_assets/screenshot_3.png" width="200" height="355,56">


Welcome to my simple android kotlin project

in this project I try to make a movie cataloge app using [OMDb API](http://www.omdbapi.com/) 

Click [here](https://drive.google.com/file/d/1hfTtxqt7IB4BPQDB1EKHBLWP8oVY6gad/view?usp=sharing) to download the app


## Features
1. search information of your favorite movies. tv shows, etc.
2. Show the list of searched favorite movies. tv shows, etc.
3. Show the information of your favorite movies. tv shows, etc on detail page

## Supported Device
- Android device with minimum API 16 **(Jelly Bean)**

## Build App requirements
- Recomended using Android Studio 4.2.2
- Using Kotlin 1.5.10

## Instructions
1. Clone from this repository
    - Copy repository url
    - Open your Android Studio
    - New -> Project from Version Control..
    - Paste the url, click OK
2. Replace demo api key with your  [OMDb API](http://www.omdbapi.com/) api key [here](https://github.com/CumiTerbang/YourMovieCatalogue/tree/master/app/src/main/java/com/haryop/yourmoviecatalogue/utils/ConstantsObj.kt)
    - utils > ConstantsObj > OMDB_APIKEY
3. Prepare the Android Virtual Device or real device
4. Build and deploy the app module

## Code Design & Structure
This project is using MVVM design pattern. The project directory consist of 4 directories:
1. **data**: The M (Model) in MVVM. Where we perform data operations.
2. **di**: Dependency Injection directory with the help of Hilt.
3. **ui**: User Interface directory for Fragments and ViewModels helping to display data to the user.
4. **util**: Urilities directory for helper classes and functions.
