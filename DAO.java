package com.wang.myDB;

/**
 * Interface to access data from database
 * @author wang
 *
 */
public interface DAO {

	public void connect() throws Exception;
	public void close() throws Exception;
}
