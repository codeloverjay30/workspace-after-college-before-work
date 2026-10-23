package com.example.dietapp.util.queryhandling.clausemaker.wheresmaker

import com.example.dietapp.util.queryhandling.clausemaker.conditionsmaker.ConditionsBuilder

/**
 * WheresBuilder
 *
 * @constructor A builder that create a complex condition (which may consists of a few simple conditions ) about MySQL.
 *              It can be concatenated after `WHERE` clause
 */

class WheresBuilder(): ConditionsBuilder() {

}