package pl.wincenciuk.eurosimulator.presentation.navigation

enum class AppScreens {
    SplashScreen,
    LoginScreen,
    GroupStageScreen,
    PlayoffScreen;

    companion object{
        fun fromRoute(route: String?): AppScreens = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            GroupStageScreen.name -> GroupStageScreen
            PlayoffScreen.name -> PlayoffScreen
            else -> throw java.lang.IllegalArgumentException("Route $route is not recognized.")
        }
    }
}