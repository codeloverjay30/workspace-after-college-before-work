package com.tibame.tip101.exercise.exer5_01.stringresources

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tibame.tip101.exercise.exer5_01.R

object StringResourcesSingleton {
    var OK : String = ""
    var CANCEL : String = ""
    var CONFIRM : String = ""
    var DISMISS : String = ""
    var ADD : String = ""
    var DIET : String = ""
    var DIARY : String = ""
    var NAME : String = ""
    var DIET_DIARY_NAME : String = ""
    var SELECT : String = ""
    var FOOD : String = ""
    var SELECT_FOOD : String = ""
    var EARLIEST : String = ""
    var LATEST : String = ""
    var SORT : String = ""
    var FOR : String = ""
    var DATE : String = ""
    var SORT_FOR_DATE : String = ""
    var SORT_FOR_NAME : String = ""
    var SEARCH : String = ""
    var AND : String = ""
    var OR : String = ""
    var NOT : String = ""
    var ERROR: String = ""
    var NOT_VALID_DATE_OR_TIME_OR_STRING: String = ""
    var OPEN_DATE_PICKER_DIALOG : String = ""
    var OPEN_TIME_PICKER_DIALOG : String = ""

    @Composable
    fun initResource() {
        OK = stringResource(R.string.ok)
        CANCEL = stringResource(R.string.cancel)
        CONFIRM = stringResource(R.string.confirm)
        DISMISS = stringResource(R.string.dismiss)
        ADD = stringResource(R.string.add)
        DIET = stringResource(R.string.diet)
        DIARY = stringResource(R.string.diary)
        NAME = stringResource(R.string.name)
        DIET_DIARY_NAME = "$DIET $DIARY $NAME"
        SELECT = stringResource(R.string.select)
        FOOD = stringResource(R.string.food)
        SELECT_FOOD = "$SELECT $FOOD"
        EARLIEST = stringResource(R.string.earliest)
        LATEST = stringResource(R.string.latest)
        SORT = stringResource(R.string.sort)
        FOR = stringResource(R.string.forwhat)
        DATE = stringResource(R.string.date)
        SORT_FOR_DATE = "$SORT $FOR $DATE"
        SORT_FOR_NAME = "$SORT $FOR $NAME"
        SEARCH = stringResource(R.string.search)
        AND = stringResource(R.string.and)
        OR = stringResource(R.string.or)
        NOT = stringResource(R.string.not)

        ERROR = stringResource(R.string.error)
        NOT_VALID_DATE_OR_TIME_OR_STRING = stringResource(R.string.not_valid_date_or_time_or_string)

        OPEN_DATE_PICKER_DIALOG =
            "${stringResource(R.string.open)} ${stringResource(R.string.date_picker)} ${
                stringResource(R.string.dialog)}"

        OPEN_TIME_PICKER_DIALOG =
            "${stringResource(R.string.open)} ${stringResource(R.string.time_picker)} ${stringResource(R.string.dialog)}"
    }
}