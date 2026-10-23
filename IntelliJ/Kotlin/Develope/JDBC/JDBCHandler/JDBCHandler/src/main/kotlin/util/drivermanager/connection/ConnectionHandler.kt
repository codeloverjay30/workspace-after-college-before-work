package org.example.util.drivermanager.connection

import org.example.util.drivermanager.info.ConnectionInfoEnum
import java.sql.Connection
import java.sql.DriverManager

class ConnectionHandler(
    val url:String = ConnectionInfoEnum.URL.value,
    val user:String = ConnectionInfoEnum.USER.value,
    val password:String = ConnectionInfoEnum.PASSWORD.value,
){
    val connection: Connection = DriverManager.getConnection(url,user,password)
}