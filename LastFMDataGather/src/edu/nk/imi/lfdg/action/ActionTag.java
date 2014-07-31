package edu.nk.imi.lfdg.action;

import java.util.ArrayList;

import edu.nk.imi.lfdg.constant.RequestConstant;
import edu.nk.imi.lfdg.dao.GatherDao;
import edu.nk.imi.lfdg.network.HttpClientHelper;

public class ActionTag {
	public void getAllTags()
	{
		GatherDao dao = new GatherDao();
		ArrayList<String> userNameList = dao.getAllUser();
		if( !userNameList .isEmpty())
		{
			for (String user : userNameList) {
				String url = RequestConstant.prefix + "user.getTopTags"
						+"&user="+ user
						+ "$limit=" + "10000"
						+"&"+RequestConstant.api_Key
						+"&"+RequestConstant.format;
				try {
					String result = HttpClientHelper.get(url);
					JSON
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
