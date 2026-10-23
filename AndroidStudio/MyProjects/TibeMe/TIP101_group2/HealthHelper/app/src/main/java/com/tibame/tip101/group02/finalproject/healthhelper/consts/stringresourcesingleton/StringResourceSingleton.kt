package com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tibame.tip101.group02.finalproject.healthhelper.R

object StringResourceSingleton {

    private var _OK_OPTION_TEXT: String = ""
    val OK_OPTION_TEXT: String
        get() = _OK_OPTION_TEXT

    private var _CANCEL_OPTION_TEXT: String = ""
    val CANCEL_OPTION_TEXT: String
        get() = _CANCEL_OPTION_TEXT

    private var _CONFIRM_OPTION_TEXT: String = ""
    val CONFIRM_OPTION_TEXT: String
        get() = _CONFIRM_OPTION_TEXT

    private var _DISMISS_OPTION_TEXT: String = ""
    val DISMISS_OPTION_TEXT: String
        get() = _DISMISS_OPTION_TEXT

    private var _HAMBURGER_ICON: String = ""
    val HAMBURGER_ICON: String
        get() = _HAMBURGER_ICON

    private var _SEARCH_ICON: String = ""
    val SEARCH_ICON: String
        get() = _SEARCH_ICON

    private var _SHARE_ICON: String = ""
    val SHARE_ICON: String
        get() = _SHARE_ICON

    private var _ADD_NEW_ITEM_ICON: String = ""
    val ADD_NEW_ITEM_ICON: String
        get() = _ADD_NEW_ITEM_ICON

    private var _DELETE_AN_ITEM_ICON: String = ""
    val DELETE_AN_ITEM_ICON: String
        get() = _DELETE_AN_ITEM_ICON

    private var _CLEAR_ICON: String = ""
    val CLEAR_ICON: String
        get() = _CLEAR_ICON

    private var _INFO_ICON: String = ""
    val INFO_ICON: String
        get() = _INFO_ICON

    private var _FB_ICON: String = ""
    val FB_ICON: String
        get() = _FB_ICON

    private var _MESSENGER_ICON: String = ""
    val MESSENGER_ICON: String
        get() = _MESSENGER_ICON

    private var _IG_ICON: String = ""
    val IG_ICON: String
        get() = _IG_ICON

    private var _TWITTER_ICON: String = ""
    val TWITTER_ICON: String
        get() = _TWITTER_ICON

    private var _DISCORD_ICON: String = ""
    val DISCORD_ICON: String
        get() = _DISCORD_ICON

    private var _DIET_APP_TITLE: String = ""
    val DIET_APP_TITLE: String
        get() = _DIET_APP_TITLE

    private var _SEARCH_HINT_TITLE: String = ""
    val SEARCH_HINT_TITLE: String
        get() = _SEARCH_HINT_TITLE

    private var _SHARABLE_APP_TITLE: String = ""
    val SHARABLE_APP_TITLE: String
        get() = _SHARABLE_APP_TITLE

    private var _SEARCH_ALGORITHM_NAME: String = ""
    val SEARCH_ALGORITHM_NAME: String
        get() = _SEARCH_ALGORITHM_NAME

    private var _SEARCH_ALGORITHM_INTRO: String = ""
    val SEARCH_ALGORITHM_INTRO: String
        get() = _SEARCH_ALGORITHM_INTRO

    private var _SEARCH_LABEL: String = ""
    val SEARCH_LABEL: String
        get() = _SEARCH_LABEL

    private var _DIARY_DIET_NAME_LABEL: String = ""
    val DIARY_DIET_NAME_LABEL: String
        get() = _DIARY_DIET_NAME_LABEL

    private var _DIARY_DIET_DATE_LABEL: String = ""
    val DIARY_DIET_DATE_LABEL: String
        get() = _DIARY_DIET_DATE_LABEL

    private var _DIARY_DIET_TIME_LABEL: String = ""
    val DIARY_DIET_TIME_LABEL: String
        get() = _DIARY_DIET_TIME_LABEL

    private var _DIARY_DIET_FOOD_LABEL: String = ""
    val DIARY_DIET_FOOD_LABEL: String
        get() = _DIARY_DIET_FOOD_LABEL

    private var _ERROR_INVALID_INPUT: String = ""
    val ERROR_INVALID_INPUT: String
        get() = _ERROR_INVALID_INPUT

    private var _INVALID_TEXTFIELD_VALUE_FOR_NAME_AND_DATE_AND_TIME: String = ""
    val INVALID_TEXTFIELD_VALUE_FOR_NAME_AND_DATE_AND_TIME: String
        get() = _INVALID_TEXTFIELD_VALUE_FOR_NAME_AND_DATE_AND_TIME

    @Composable
    fun initResources(){

        // option text (used in dialog)
        _OK_OPTION_TEXT = stringResource(R.string.ok_option_text)
        _CANCEL_OPTION_TEXT = stringResource(R.string.cancel_option_text)
        _CONFIRM_OPTION_TEXT = stringResource(R.string.confirm_option_text)
        _DISMISS_OPTION_TEXT = stringResource(R.string.dismiss_option_text)

        // icon (usually used with contentDescription in built-in icon such as `Icons` class)
        _HAMBURGER_ICON = stringResource(R.string.hamburger_icon)
        _SEARCH_ICON = stringResource(R.string.search_icon)
        _SHARE_ICON = stringResource(R.string.share_icon)
        _ADD_NEW_ITEM_ICON = stringResource(R.string.add_new_item_icon)
        _CLEAR_ICON = stringResource(R.string.clear_icon)
        _DELETE_AN_ITEM_ICON = stringResource(R.string.delete_an_item_icon)
        _INFO_ICON = stringResource(R.string.info_icon)

        _FB_ICON = stringResource(R.string.fb_icon)
        _MESSENGER_ICON = stringResource(R.string.messenger_icon)
        _IG_ICON = stringResource(R.string.ig_icon)
        _TWITTER_ICON = stringResource(R.string.twitter_icon)
        _DISCORD_ICON = stringResource(R.string.discord_icon)

        // title
        _DIET_APP_TITLE = stringResource(R.string.diet_app_title)
        _SEARCH_HINT_TITLE = stringResource(R.string.search_hint_title)
        _SHARABLE_APP_TITLE = stringResource(R.string.sharable_app_title)

        _SEARCH_ALGORITHM_NAME = stringResource(R.string.search_algorithm_name)
        _SEARCH_ALGORITHM_INTRO = stringResource(R.string.search_algorithm_intro)

        // label (used in textfield components)
        _SEARCH_LABEL = stringResource(R.string.search_label)
        _DIARY_DIET_NAME_LABEL = stringResource(R.string.diary_diet_name_label)
        _DIARY_DIET_DATE_LABEL = stringResource(R.string.diary_diet_date_label)
        _DIARY_DIET_FOOD_LABEL = stringResource(R.string.diary_diet_food_label)

        // error message
        _ERROR_INVALID_INPUT = stringResource(R.string.error_invalid_input)
        _INVALID_TEXTFIELD_VALUE_FOR_NAME_AND_DATE_AND_TIME = stringResource(R.string.invalid_textfield_value_for_name_and_date_and_time)
    }
}