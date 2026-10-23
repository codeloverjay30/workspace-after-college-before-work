package com.jay30.annotationdemo3

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider


data class User(val name:String)

@Composable
fun UserProfile(user: User){
    Text(user.name)
}
class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values = sequenceOf(
        User("Elise"),
        User("Frank"),
        User("Julia")
    )
}

@Preview
@Composable
fun UserProfilePreview(
    @PreviewParameter(UserPreviewParameterProvider::class) user: User
) {
    UserProfile(user)
}

@Preview
@Composable
fun UserProfilePreview2(
    @PreviewParameter(UserPreviewParameterProvider::class, limit = 2) user: User
) {
    UserProfile(user)
}