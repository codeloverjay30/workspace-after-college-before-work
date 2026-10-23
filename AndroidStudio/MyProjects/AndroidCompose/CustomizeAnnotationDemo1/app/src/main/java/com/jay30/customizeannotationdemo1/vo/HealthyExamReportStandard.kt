package com.jay30.customizeannotationdemo1.vo

@HealthyInfo(height = 20.0 , weight = 20.0)
annotation class HealthyExamReportStandard(
    val leukocyteIndexUpperBound: UInt,
    val leukocyteIndexLowerBound: UInt,
){
}