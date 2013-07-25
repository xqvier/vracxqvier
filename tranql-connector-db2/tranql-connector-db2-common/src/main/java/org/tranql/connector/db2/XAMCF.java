/*
 * Copyright (c) 2005 - 2013, Tranql project contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.tranql.connector.db2;

import java.sql.Connection;
import java.sql.SQLException;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.security.auth.Subject;
import javax.sql.XAConnection;

import com.ibm.db2.jcc.DB2XADataSource;
import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;
import org.tranql.connector.NoExceptionsAreFatalSorter;
import org.tranql.connector.jdbc.AbstractPSCachedXADataSourceMCF;
import org.tranql.connector.jdbc.ConnectionWrapper;
import org.tranql.connector.jdbc.ManagedXAConnection;

/**
 * ManagedConnectionFactory that wraps a DB2 EmbeddedXADataSource for use in-VM.
 *
 * @version $Revision: 878 $ $Date: 2013-04-05 21:20:17 +0200 (Fri, 05 Apr 2013) $
 */
public class XAMCF extends AbstractPSCachedXADataSourceMCF<DB2XADataSource> {
    private String password = "";

    /**
     * Default constructor for a DB2 Embedded XA DataSource.
     */
    public XAMCF() {
        super(new DB2XADataSource(), new NoExceptionsAreFatalSorter());
    }

    /**
     * Returns the name of the database. This name is used
     * as the database portion of the connection URL.
     *
     * @return the database name
     */
    public String getDatabaseName() {
        return xaDataSource.getDatabaseName();
    }

    /**
     * Specifies the name of the database to connect to. This name is used
     * as the database portion of the connection URL.
     *
     * @param name the database name
     */
    public void setDatabaseName(String name) {
        xaDataSource.setDatabaseName(name);
    }

    /**
     * Return the user name used to establish the connection.
     *
     * @return the user name used to establish the connection
     */
    public String getUserName() {
        return xaDataSource.getUser();
    }

    /**
     * Set the user name used establish the connection.
     * This value is used if no connection information is supplied by the application
     * when attempting to create a connection.
     *
     * @param user the user name used to establish the connection; may be null
     */
    public void setUserName(String user) {
        xaDataSource.setUser(user);
    }

    /**
     * Return the password credential used to establish the connection.
     *
     * @return the password credential used to establish the connection
     */

    public String getPassword() {
        return password; //log.fatal("GetPassword has been invoked")
        //    return DB2XADataSource.getPassword(new java.util.Properties());
    }

    /**
     * Set the user password credential establish the connection.
     * This value is used if no connection information is supplied by the application
     * when attempting to create a connection.
     *
     * @param pwd the password credential used to establish the connection; may be null
     */
    public void setPassword(String pwd) {
        this.password = pwd;
        xaDataSource.setPassword(pwd);
    }


    /**
     * Returns the name of the remote server providing RDBMS connectivity.
     *
     * @return the host name of the remote database server.
     */

    public String getServerName() {
        return xaDataSource.getServerName();
    }

    /**
     * Used to specify the name of the server that provides database services.
     *
     * @param serverName The DNS or IP address of the host name to connect to
     */
    public void setServerName(String serverName) {
        xaDataSource.setServerName(serverName);
    }


    /**
     * Returns the port number used to connect to the
     * remote database system.
     *
     * @return the port number
     */
    public Integer getPortNumber() {
        return new Integer(xaDataSource.getPortNumber());
    }

    /**
     * Specifies the port number to connect to for a remote database
     * server.
     *
     * @param portNumber the port number to connect to (DB2 default is 50000)
     */
    public void setPortNumber(Integer portNumber) {
        xaDataSource.setPortNumber(portNumber.intValue());
    }

    /**
     * Returns driver type that is current in use.  This will be either
     * a type 2 or type 4.
     *
     * @return the driver type
     */
    public Integer getDriverType() {
        return new Integer(xaDataSource.getDriverType());
    }

    /**
     * Indicates which driver type connections on this datasource will use.
     * Possible Values are 2 or 4.
     *
     * @param driverType the driver type to use.  DB2 default is Type 2.
     */
    public void setDriverType(Integer driverType) {
        xaDataSource.setDriverType(driverType.intValue());
    }


    /**
     * Returns the current traceFile name used for tracing data.
     *
     * @return the driver type
     */
    public String getTraceFile() {
        return xaDataSource.getTraceFile();
    }

    /**
     * Sets the name of the flat file to dump trace records into.
     *
     * @param traceFile Name of the file to place trace records
     */
    public void setTraceFile(String traceFile) {
        if (traceFile == null || traceFile.trim().length() == 0) {
            return;
        }
        xaDataSource.setTraceFile(traceFile);
    }

    /**
     * Returns the current setting of the traceFileAppend flag.
     *
     * @return current setting of the TraceFileAppend value.
     */
    public Boolean getTraceFileAppend() {
        return xaDataSource.getTraceFileAppend();
    }

    /**
     * Specifies whether to append to or overwrite the file that
     * is specified by the traceFile property. The data type of this
     * property is boolean. The
     * <p/>
     * default is false, which means that the file that is specified
     * by the traceFile property is overwritten.
     *
     * @param traceFileAppend Boolean indicator to specify if the file should be appended.
     */
    public void setTraceFileAppend(Boolean traceFileAppend) {
        xaDataSource.setTraceFileAppend(traceFileAppend);
    }

    /**
     * Can cursors defined WITH HOLD be opened on an XA connection?
     */
    public Boolean getDowngradeHoldCursorsUnderXa() {
        return xaDataSource.getDowngradeHoldCursorsUnderXa();
    }

    public void setDowngradeHoldCursorsUnderXa(Boolean downgradeHoldCursors) {
        xaDataSource.setDowngradeHoldCursorsUnderXa(downgradeHoldCursors);
    }

    /**
     * Can cursors remain open after a commit operation?
     */
    public Integer getResultSetHoldability() {
        return new Integer(xaDataSource.getResultSetHoldability());
    }

    public void setResultSetHoldability(Integer resultSetHoldability) {
        xaDataSource.setResultSetHoldability(resultSetHoldability.intValue());
    }
    
    public Integer getProgressiveStreaming(){
    	return new Integer(xaDataSource.getProgressiveStreaming());
    }
    
    public void setProgressiveStreaming(Integer progressiveStreaming){
    	xaDataSource.setProgressiveStreaming(progressiveStreaming.intValue());
    }
    
    public void setFullyMaterializeInputStreams(Boolean fullyMaterializeInputStreams){
    	xaDataSource.setFullyMaterializeInputStreams(fullyMaterializeInputStreams);
    }
    
    public Boolean getFullyMaterializeInputStreams(){
    	return xaDataSource.getFullyMaterializeInputStreams();
    }
    
    public void setFullyMaterializeLobData(Boolean fullyMaterializeLobData){
    	xaDataSource.setFullyMaterializeLobData(fullyMaterializeLobData);    	
    }
    
    public Boolean getFullyMaterializeLobData(){
    	return xaDataSource.getFullyMaterializeLobData();
    }
    
    public void setCurrentSQLID(String currentSQLID){
    	xaDataSource.setCurrentSQLID(currentSQLID);
    }
    
    public String getCurrentSQLID(){
    	return xaDataSource.getCurrentSQLID();
    }
    
    
}
