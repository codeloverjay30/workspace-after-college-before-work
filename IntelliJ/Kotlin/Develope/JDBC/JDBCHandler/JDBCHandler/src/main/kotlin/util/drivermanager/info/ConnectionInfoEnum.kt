package org.example.util.drivermanager.info

enum class ConnectionInfoEnum(val value:String) {
    JDBC(value = "jdbc"),
    SQL_ENGINE(value = "mysql"),
    IPV4(value = "35.194.200.2"),
    PORT(value = "3306"),
    DATABASE_NAME(value = "healthy"),
    USER(value = "dev"),
    PASSWORD(value = "0000"),
    URL(value = "${ConnectionInfoEnum.JDBC.value}" + ":" +
            "${ConnectionInfoEnum.SQL_ENGINE.value}" + "://" +
            "${ConnectionInfoEnum.IPV4.value}" + ":" +
            "${ConnectionInfoEnum.PORT.value}" + "/" +
            "${ConnectionInfoEnum.DATABASE_NAME.value}"),
}