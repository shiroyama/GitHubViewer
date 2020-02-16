package us.shiroyama.githubviewer.domain.usecase.github

import android.text.TextUtils
import androidx.annotation.StringRes
import us.shiroyama.githubviewer.R
import javax.inject.Inject


class AccountValidator @Inject constructor() {
    fun validate(account: String?): Result {
        if (TextUtils.isEmpty(account)) {
            return Result(false, R.string.error_field_required)
        }
        val isValid = account!!.matches("[a-zA-Z0-9]+".toRegex())
        return if (isValid) Result(isValid, 0) else Result(isValid, R.string.error_invalid_account)
    }

    data class Result(
        val isValid: Boolean,
        @StringRes val errorRes: Int
    )
}