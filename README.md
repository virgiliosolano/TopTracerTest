# TopTracer Test

 This project consist in a sample app to consume Giphy API and get the most trending giphy and show in the app.

## Structure

- The project starts with MainActivity and use Android Navigation
- It was organized in 3 packages:
    - ui: Files responsible for the user interface and viewmodel
    - shared: extension functions to help in the code
    - repository: contains all classes responsible to handler a network call and the api for giphy

## Use your own Giphy API KEY
- Create your localproperties.gradle file and add the attribute giphy.apiKey=YOUR_API_KEY. Access https://developers.giphy.com/