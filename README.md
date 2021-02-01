# Tweather

Tweather is an Android app that displays the weather for San Francisco using the Twitter Code Challenge API
  - Loads the current weather in the "Today" tab.
  - Load the weather for the next 5 days in the "5 Days" tab.
  - For the weather, we display the temperature in Celsius and Fahrenheit, the wind speed in meters per second and a cloud icon if the cloudiness percentage is greater than 50%.
  - If an error has occurred, user will be notified via a Toast message.

## Development

  - This app is written 100% in Kotlin. There are no Java files.
  - Demonstrated use of Kotlin extensions.
  - Used Jetpack libraries, Hilt for dependency injection and MVVM design pattern
  - Coroutines were used to make parallel network requests in the background. A new request isn't made after orientation changes.
  - When fetching the weather for the next 5 days in parallel, if 1 call fails, an error is intentionally thrown. We only want to show the weather for all 5 days or none at all.
  - Core functionality unit tests have been pushed as per requirements. Espresso and Mockito tests can be added at a later time if time permits.

### Libraries

Tweather uses a number of open source projects:

* [Coroutines] - Async
* [Hilt/Dagger] - Dependency Injection
* [Retrofit] - Networking
* [Timber] - Logging
* [ViewBinding] - View Binding

### TODOs/Improvements (Extras/Improvements that were not developed due to time constraints):
- [ ] Use [NetworkCallbacks] for apps that target API 29+. Also listen for connectivity to retry call fails without needing retry button.
- [ ] Make the UI even prettier
