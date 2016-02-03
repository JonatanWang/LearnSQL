package com.wang.myDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


/**
 * Implementation of AlbumDAO
 * @author wang
 *
 */
public class DerbyAlbumDAO implements AlbumDAO{
	
	/**
	 * Data fields
	 */
	private Connection connection; 
	private QueryRunner dbAccess = new QueryRunner();
	private static final List<Album> EMPTY = new ArrayList<>();
	
	String user = "me"; String passwd = "me";
	String database = "myDB";
	String server= "jdbc:mysql://localhost:3306/" + database + "?UseClientEnc=UTF8";
	

	/**
	 * Connect to the database
	 */
	@Override
	public void connect() throws Exception {
		
	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(server, user,passwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * Close the database access
	 */
	@Override
	public void close() throws Exception {
		connection.close();
		
	}

	/**
	 * Insert a new album into the T_album
	 */
	@Override
	public long insertAlbum(Album album) {
		try{
			long id = dbAccess.update(connection, "insert into Album(title, genre, score, released) values(?,?,?,?)", 
					album.getTitle(), album.getGenre(), album.getScore(), album.getReleased());
			
			return id;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1L;	// Return -1 for error;
	}
	/**
	 * Insert an new Artist into the T_Artist
	 * 
	 */
	@Override
	public void insertArtist(String[] names, String[] nations) {
		for (int i = 0; i < names.length; i ++) {
		try {
			
			dbAccess.update(connection, "insert into Artist(name, nation) values(?,?)", 
					names[i], nations[i]);
			
			//return id;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		/**return -1L;*/}
	}
	
	/**
	 * Search the uniqueID of the newly inserted album, in order to combine the corresponding artists
	 * @param album
	 * @return
	 * @throws SQLException
	 */
	public int searchUniqueID(Album album) throws SQLException{
		int uniqueID;
		
		uniqueID = (dbAccess.query(connection, "select * from Album where title like ?", new BeanListHandler<Album>(Album.class), album.getTitle())).get(0).getUniqueID();
		System.out.println(uniqueID);
		return uniqueID;
	}
	
	/**
	 * Insert an uniqueID from T_Album & (several) corresponding id:s of T_artist
	 */
	public void insertCopyright(Album album, String[] names) throws SQLException{
		
		int uniqueID = searchUniqueID(album);
		
		for(int i = 0; i < names.length; i ++) {
		try{
			// Here will be some loops to find the uniqueID & corresponding artist.id :s
		    
		    int id = (dbAccess.query(connection, "select * from Artist where name like ?", new BeanListHandler<Artist>(Artist.class), names[i])).get(0).getId();
		    System.out.println(id);
		    	
			dbAccess.update(connection, 
					"insert into Copyright(uniqueID, id) values(?,?)", 
					
					uniqueID, id);
		
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
			}
		
	}

	/**
	 * Update the album table
	 */
	@Override
	public boolean updateAlbum(Album album) {
		try{
			dbAccess.update(connection, "update Album set title = ?, artists=?, released=?, genre=?, score=? where uniqueID=?",
					album.getTitle(),album.getName(),album.getReleased(),album.getGenre(),album.getScore(),album.getUniqueID() );
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Search albums+artists according to 5 types of data
	 */
	@Override
	public List<Album> findAlbumByProperty(AlbumSearchType searchType,
			Object value) {
		
		// Generic methods
		String whereClause = "";
		String valueClause = "";
		
		switch(searchType){
		
		case ID:
			whereClause = "album.uniqueID = ?";
			valueClause = value.toString();
			break;
		case ARTIST:
			whereClause = "name LIKE ?";
			valueClause = "%" + value.toString() + "%";
			break;
		case SCORE:
			whereClause = "score = ?";
			valueClause = value.toString();
			break;
		case TITLE:
			whereClause = "title LIKE ?";
			valueClause = "%"+ value.toString() + "%";
			break;
		case GENRE:
			whereClause = "genre LIKE ?";
			valueClause = "%" + value.toString() + "%";
			break;
		default:
			System.out.println("Unknow search type");
			break;
		
		}
		
		try{
			/**
			 * It is a MUST to add " " after the last "and" i mySQL syntax!!!!
			 */
			return dbAccess.query(connection, "select album.uniqueID, title,genre,score,released,name,nation "
					+ "from (artist left join copyright on copyright.id=artist.id), Album "
					+ "where album.uniqueID=copyright.uniqueID and " + whereClause, 
					new BeanListHandler<Album>(Album.class), valueClause);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return EMPTY;
	}

	/**
	 * Search all the albums+artists in the database
	 */
	@Override
	public List<Album> findAll() {
		try {
			return dbAccess.query(connection, "select album.uniqueID, title, genre, score, released, name, nation "
					+ "from (artist left join copyright on copyright.id=artist.id), Album "
					+ "where copyright.uniqueID=album.uniqueID order by album.uniqueID", 
					new BeanListHandler<Album>(Album.class));
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
		
	}

}
