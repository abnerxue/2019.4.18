package ck.preprocessorData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ck.ServerObj;
import ck.po.CompatibilityInterplayMedcineLbl;
import ck.po.lblMedcine;

/**
 * <h3>预处理单个药品与标签中的规则</h3>
 * <h3>配伍管理和相互作用，重复用药(单个药品与标签)</h3>
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class RuleCompatibilityInterplayMedcineLbl {
	
	public static void ruleCompatibilityInterplayMedcineLbl() {
		Connection connection=new ServerObj().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String sql = "select id,medcine_id,lbl_id,reaction_description,audit_level_id,type,medcine_commonName from t_compatibility_interplay_medcine_lbl";
			//标签与药品结果集
			List<CompatibilityInterplayMedcineLbl> cimlList = new ArrayList<CompatibilityInterplayMedcineLbl>();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CompatibilityInterplayMedcineLbl ciml = new CompatibilityInterplayMedcineLbl();
				ciml.setId(rs.getInt(1));
				ciml.setMedcineId(rs.getInt(2));
				ciml.setLblId(rs.getInt(3));
				ciml.setReactionDescription(rs.getString(4));
				ciml.setAuditLevelId(rs.getInt(5));
				ciml.setType(rs.getInt(6));
				cimlList.add(ciml);
			}
			
			for(CompatibilityInterplayMedcineLbl compiml : cimlList) {
				List<ReviewResults> reviewResultsList = new ArrayList<>();//包含规则对象的集合
				ReviewResults reviewResults = new ReviewResults();
				reviewResults.setAuditLevelId(compiml.getAuditLevelId());
				reviewResults.setReactionDescription(compiml.getReactionDescription());
				reviewResults.setType(compiml.getType());
				reviewResultsList.add(reviewResults);//审核标准结果集
				
				pstmt = null;
				rs = null;
				int labelId = compiml.getLblId();
				List <lblMedcine> lalmedicineList = new ArrayList<>();
				String sql2 = "select id,medcine_id,label_id from t_lbl_medcine where label_id = ?";
				pstmt = connection.prepareStatement(sql2);
				pstmt.setInt(1, labelId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					lblMedcine lblmeicine = new lblMedcine();
					lblmeicine.setId(rs.getInt(1));
					lblmeicine.setMecineId(rs.getInt(2));
					lblmeicine.setLabelId(rs.getInt(3));
					lalmedicineList.add(lblmeicine);
				}
				RedisHelper redisHelper = new RedisHelper();
				for(lblMedcine lalmedicine : lalmedicineList) {
					if(compiml.getMedcineId()==lalmedicine.getMecineId()) {
						continue;
					}
					List ResultList2 = (List) redisHelper.getObject(compiml.getMedcineId() + "|" + lalmedicine.getMecineId(), lalmedicine.getMecineId() + "|" + compiml.getMedcineId());
					if(null != ResultList2) {
						ResultList2.addAll(reviewResultsList);
						redisHelper.setObject(compiml.getMedcineId() + "|" + lalmedicine.getMecineId(), lalmedicine.getMecineId() + "|" + compiml.getMedcineId(), ResultList2);
						redisHelper.setObject(lalmedicine.getMecineId() + "|" + compiml.getMedcineId(), compiml.getMedcineId() + "|" + lalmedicine.getMecineId(), ResultList2);
					}else {
						redisHelper.setObject(compiml.getMedcineId() + "|" + lalmedicine.getMecineId(), lalmedicine.getMecineId() + "|" + compiml.getMedcineId(), reviewResultsList);
						redisHelper.setObject(lalmedicine.getMecineId() + "|" + compiml.getMedcineId(), compiml.getMedcineId() + "|" + lalmedicine.getMecineId(), reviewResultsList);
					}
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
