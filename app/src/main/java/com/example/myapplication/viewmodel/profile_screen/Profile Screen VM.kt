package com.example.myapplication.viewmodel.profile_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.domain.ViewModel
import com.example.myapplication.domain.general_use_cases.MakeToastUseCase
import com.example.myapplication.domain.profile_screen.*
import com.example.myapplication.domain.sign_up_screen.use_cases.DateConverterUseCase
import com.example.myapplication.network.favourite_movies.FavouritesResponse
import com.example.myapplication.network.profile.ProfileResponse
import com.example.myapplication.screen.destinations.SignInScreenDestination
import com.example.myapplication.viewmodel.sign_up_screen.DatePickerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class ProfileScreenState {

    val emailData = mutableStateOf("test@example.com")
    val linkData = mutableStateOf("https://vk.com/chdMpx")

    // При клике на кнопку "Сохранить" на Profile Screen, в эту перепенную будет записываться linkData.
    // Она нужна для того, чтобы аватарка постоянно не перерисовывалась при изменении поля "Ссылка на аватарку"
    val showPictureByLink = mutableStateOf("")

    val nameData = mutableStateOf("Тест Тестович")
    val maleSexData = mutableStateOf(false)
    val femaleSexData = mutableStateOf(true)
    val dateData = DatePickerState("01.01.2022")
    val areFilledFields: Boolean
        get() = dateData.dateData.value.isNotEmpty() && emailData.value.isNotEmpty() && nameData.value.isNotEmpty() && (femaleSexData.value || maleSexData.value)

    private var profileResponse: ProfileResponse = ProfileResponse("", "", "", "", "", "", 0)

    suspend fun onClickProfile(context: Context, navigator: DestinationsNavigator) {
        try {
            val response: ProfileResponse = GetProfileUseCase().getProfile(context = context)
            profileResponse = response
            emailData.value = response.email
            if (response.avatarLink == null) {
                linkData.value = ""
            } else {
                linkData.value = response.avatarLink!!
            }
            nameData.value = response.name
            dateData.dateData.value =
                DateReverseConverter().toProfileFormat(date = response.birthDate)
            if (response.gender == 0) {
                maleSexData.value = true
                femaleSexData.value = false
            }
            showPictureByLink.value = linkData.value
        } catch (e: Exception) {
            if (e.message.toString().contains("avatar")) {
                Toast.makeText(
                    context,
                    "Срок действия вашего аутентификационного токена истёк.\nПожалуйста, войдите в ваш аккаунт заново",
                    Toast.LENGTH_LONG
                ).show()
                navigator.navigate(SignInScreenDestination)
                Log.i("errorList", e.message.toString())
            }
        }
    }

    suspend fun onClickSave(context: Context) {
        if (IsValidEmailUseCase().isValid(emailData.value)) {
            try {
                SaveProfileUseCase().saveProfile(
                    id = profileResponse.id,
                    nickName = if (profileResponse.nickName == null) "Error" else profileResponse.nickName.toString(),
                    email = emailData.value,
                    avatarLink = linkData.value,
                    name = nameData.value,
                    birthDate = DateConverterUseCase().toJsonFormat(
                        dateData.dateData.value.replace(
                            "/",
                            "."
                        )
                    ),
                    gender = if (maleSexData.value) 0 else 1,
                    context = context
                )
                showPictureByLink.value = linkData.value
                profileResponse.avatarLink = linkData.value
                Toast.makeText(
                    context,
                    "Данные сохранены",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                MakeToastUseCase().show(
                    context = context,
                    text = "Дата рождения не может являться днём, который ещё не наступил"
                )
                Log.i("errorList", e.message.toString())
            }
        } else {
            MakeToastUseCase().show(
                context = context,
                text = "Введённый e-mail не является валидным"
            )
        }
    }

    suspend fun onClickLogout(context: Context, navigator: DestinationsNavigator) {
        try {
            LogoutUseCase().logout(context)
            // Удаление некоторых данных, влияющих на отображение информации
            ViewModel.signInScreen.loginData.value = ""
            ViewModel.signInScreen.passwordData.value = ""
            ViewModel.mainScreen.favouriteMovies = FavouritesResponse(emptyList())
            ViewModel.mainScreen.isFavourites.value = false
            navigator.popBackStack(SignInScreenDestination, true)
            navigator.navigate(SignInScreenDestination)
        } catch (e: Exception) {
            MakeToastUseCase().show(
                context = context,
                text = "Ошибка при попытке выхода из аккаунта"
            )
        }
    }
}

@Composable
fun rememberProfileScreenState() = remember {
    ProfileScreenState()
}