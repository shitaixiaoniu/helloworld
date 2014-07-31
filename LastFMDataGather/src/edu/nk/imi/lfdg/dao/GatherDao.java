package edu.nk.imi.lfdg.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.nk.imi.lfdg.constant.DBConstant;

public class GatherDao {

	public ArrayList<String> getAllUser() {
    system.out.prinltn("hah");
		ArrayList<String> userNameList = new ArrayList<String>();
		try {
			MongoClient mongoClient = new MongoClient(DBConstant.DBIP,
					DBConstant.DBPort);
			DB db = mongoClient.getDB(DBConstant.DBName);
			DBCollection coll = db.getCollection(DBConstant.Table_User);
			DBCursor cursor = coll.find(null, new BasicDBObject("age", true));
			
			try {
				while (cursor.hasNext()) {
					DBObject obj=cursor.next();
					String name = obj.get("userName").toString();
					userNameList.add(name);
					System.out.println("name"+name);
				}
			} finally {
				cursor.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userNameList;
	}
}
