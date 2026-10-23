package com.jay30.customizeannotationdemo1.vo

// WRONG since an annotation argument must be a compile-time constant.
// @HealthyExamReportStandard(leukocyteIndexUpperBound = 20U, leukocyteIndexLowerBound = 2.toUInt())

@HealthyExamReportStandard(leukocyteIndexUpperBound = 20U, leukocyteIndexLowerBound = 2U)
class HealthyExamReports {
}