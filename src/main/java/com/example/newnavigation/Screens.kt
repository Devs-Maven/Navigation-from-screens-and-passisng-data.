package com.example.newnavigation

    sealed class Screens(val route: String){
        object HomeScreen : Screens("HomeScreen")
        object SecondScreen : Screens("SecondScreen")
    }

