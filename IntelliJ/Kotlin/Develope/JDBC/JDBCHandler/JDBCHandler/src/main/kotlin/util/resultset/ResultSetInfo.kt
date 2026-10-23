package org.example.util.resultset

import java.sql.ResultSet

class ResultSetInfo(
    val _affectedRowCount: Int = RowAffectedInfoEnum.EXCEPTION_AT_RUNTIME.value,
    val _resultSet: ResultSet? = null,
){
    /**
     * affectedRowCount:
     *
     * The number of affected row, it may be one of -2, -1, 0 or positive integer.
     *
     * It is set to -1 (i.e. RowAffectedInfoEnum.EXCEPTION_AT_RUNTIME.value) iff
     *
     * there is an exception at runtime of execution of query
     *
     * through such as `preparedStatement.executeQuery()` etc.
     *
     * where preparedStatement is an instance of java.sql.PreparedStatement.
     *
     * > [!WARNING]
     * >
     * > NOTE that
     * >
     * > 1. It is changed when execution of not only `preparedStatement.executeQuery()`
     * >
     * > but also `preparedStatement.executeUpdate()`
     * >
     * > where preparedStatement is an instance of java.sql.PreparedStatement.
     */
    var affectedRowCount: Int = _affectedRowCount
        set(value) {
            if (value == RowAffectedInfoEnum.EXCEPTION_AT_RUNTIME.value
                || value >= RowAffectedInfoEnum.NO_ROW_AFFECTED.value
            ) {
                field = value
            } else {
                field = RowAffectedInfoEnum.NO_SUCH_INFO.value
            }
        }

    /**
     * resultSet:
     *
     * > [!WARNING]
     * >
     * > It is changed to non-null value only when execution of `preparedStatement.executeQuery()`.
     * >
     * > It is changed to null when execution of `preparedStatement.executeUpdate()`.
     * >
     * > where preparedStatement is an instance of java.sql.PreparedStatement.
     * >
     * > It will not be changed in other cases.
     */
    var resultSet: ResultSet? = _resultSet

    /**
     * secondary constructor of ResultSetInfo class
     *
     * properties of this are all set to properties of ResultSetInfo.
     */
    constructor(resultInfo: ResultSetInfo) : this() {
        this.affectedRowCount = resultInfo.affectedRowCount
        this.resultSet = resultInfo.resultSet
    }
}