
sealed class AppScreen(val route:String) {

    object Login:AppScreen(route = "login")
//    object SignUp:AppScreen(route = "signup")
    object LandingPage:AppScreen(route = "booklist")

}
