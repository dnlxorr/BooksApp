
sealed class AppScreen(val route:String) {
    object Login:AppScreen(route = "login")
    object LandingPage:AppScreen(route = "landing_page")

}
