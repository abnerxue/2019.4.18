package ck.preprocessorData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import ck.po.CompatibilityInterplay;
/**
 * <h3>预处理药品对药品中的规则</h3>
 * <h3>配伍管理和相互作用,重复用药表(药品对药品)</h3>
 * @author LIUJUNWU
 * @version 1.0.0
 */
public class RuleCompatibilityInterplay {
	
	@SuppressWarnings("all")
	public static void RuleCompatibilityInterplay(Statement st) {
		ResultSet rs = null;
		try {
			//查询药品对药品中的规则
			rs = st.executeQuery("SELECT id, medcine_pair_id, reaction_description, medcine_id, audit_level_id, type, medcine_pair_commonName, medcine_commonName FROM t_compatibility_interplay");
			List<CompatibilityInterplay> compatibilityInterplayList = new ArrayList<>();
			while (rs.next())
			{
				CompatibilityInterplay compatibilityInterplay = new CompatibilityInterplay();
				compatibilityInterplay.setId(rs.getInt(1));
				compatibilityInterplay.setMedcinePairId(rs.getInt(2));
				compatibilityInterplay.setReactionDescription(rs.getString(3));
				compatibilityInterplay.setMedcineId(rs.getInt(4));
				compatibilityInterplay.setAuditLevelId(rs.getInt(5));
				compatibilityInterplay.setType(rs.getInt(6));
				compatibilityInterplayList.add(compatibilityInterplay);
			}
			RedisHelper redisHelper = new RedisHelper();
			for(CompatibilityInterplay cip : compatibilityInterplayList) {
			    List<ReviewResults> reviewResultsList = new ArrayList<>();
				ReviewResults reviewResults = new ReviewResults();
				reviewResults.setReactionDescription(cip.getReactionDescription());
				reviewResults.setAuditLevelId(cip.getAuditLevelId());
				reviewResults.setType(cip.getType());
				reviewResultsList.add(reviewResults);
				//查询redis中是否已存在这两个药品的相关数据
				List ResultList = (List) redisHelper.getObject(cip.getMedcinePairId() + "|" + cip.getMedcineId(), cip.getMedcinePairId() + "|" + cip.getMedcineId());
				if(ResultList != null){//保留之前的数据并把这个规则的结果数据存上去
					ResultList.addAll(reviewResultsList);
					redisHelper.setObject(cip.getMedcinePairId() + "|" + cip.getMedcineId(), cip.getMedcinePairId() + "|" + cip.getMedcineId(), ResultList);
					redisHelper.setObject(cip.getMedcineId() + "|" + cip.getMedcinePairId(), cip.getMedcineId() + "|" + cip.getMedcinePairId(), ResultList);
				}else{//把这个规则的结果数据存上去
					redisHelper.setObject(cip.getMedcinePairId() + "|" + cip.getMedcineId(), cip.getMedcinePairId() + "|" + cip.getMedcineId(), reviewResultsList);
					redisHelper.setObject(cip.getMedcineId() + "|" + cip.getMedcinePairId(), cip.getMedcineId() + "|" + cip.getMedcinePairId(), reviewResultsList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
