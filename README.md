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
  - Unit tests were created using Mockito.
  - UI tests were created using Espresso.

### Third Party Libraries

Tweather uses a number of open source projects:

* [Hilt/Dagger] - Dependency Injection
* [Espresso] - UI Tests
* [Mockito] - Unit Tests
* [Retrofit] - Networking

### TODOs (Unfinished due to time constraints):
- [ ] *Finish writing and commit/push test cases
- [ ] *Add a retry button to retry fetching if network call fails
- [ ] Create a resource provider to pull strings from strings.xml for the error messages
- [ ] Handle errors more gracefully and throw specific exceptions rather than a generic Exception
- [ ] Use [NetworkCallbacks] for apps that target API 29+. Also listen for connectivity to retry call failed
- [ ] Make the UI even prettier

Note: *Planning to spend another hour or two to finish these 2 items.


