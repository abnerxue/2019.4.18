package ck.preprocessorData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ck.ServerObj;
import ck.po.CompatibilityLblLbl;
import ck.po.lblMedcine;

/**
 * <h3>预处理标签与标签中的规则</h3>
 * <h3>配伍管理和相互作用，重复用药(标签与标签)</h3>
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class RuleCompatibilityLblLbl {
	public static void ruleCompatibilityInterplayMedcineLbl() {
		Connection connection=new ServerObj().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//标签与标签结果集
			List<CompatibilityLblLbl> compllList = new ArrayList<CompatibilityLblLbl>();
			String sql = "select id,lbl_id,lbl_pair_id,reaction_description,audit_level_id,type from t_compatibility_lbl_lbl";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CompatibilityLblLbl compatibilityLblLbl = new CompatibilityLblLbl();//单条标签与标签对象
				compatibilityLblLbl.setId(rs.getInt(1));
				compatibilityLblLbl.setLblId(rs.getInt(2));
				compatibilityLblLbl.setLblPairId(rs.getInt(3));
				compatibilityLblLbl.setReactionDescription(rs.getString(4));
				compatibilityLblLbl.setAuditLevelId(rs.getInt(5));
				compatibilityLblLbl.setType(rs.getInt(6));
				compllList.add(compatibilityLblLbl);
			}
			
			for(CompatibilityLblLbl comp : compllList) {
				List<ReviewResults> reviewResultsList = new ArrayList<>();//包含规则对象的集合
				ReviewResults reviewResults = new ReviewResults();
				reviewResults.setAuditLevelId(comp.getAuditLevelId());
				reviewResults.setReactionDescription(comp.getReactionDescription());
				reviewResults.setType(comp.getType());
				reviewResultsList.add(reviewResults);//审核标准结果集
				
				//获取主标签下的药品结果集
				pstmt = null;
				rs = null;
				int labelId = comp.getId();
				List <lblMedcine> lalmedicineList01 = new ArrayList<>();//主标签下的药品结果集
				String sql2 = "select id,medcine_id,label_id from t_lbl_medcine where label_id = ?";
				pstmt = connection.prepareStatement(sql2);
				pstmt.setInt(1, labelId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					lblMedcine lblmeicine = new lblMedcine();
					lblmeicine.setId(rs.getInt(1));
					lblmeicine.setMecineId(rs.getInt(2));
					lblmeicine.setLabelId(rs.getInt(3));
					lalmedicineList01.add(lblmeicine);
				}
				
				//获取副标签下的药品结果集
				pstmt = null;
				rs = null;
				int labelPair = comp.getLblPairId();
				List <lblMedcine> lalmedicineList02 = new ArrayList<>();//副标签下的药品结果集
				String sql3 = "select id,medcine_id,label_id from t_lbl_medcine where label_id = ?";
				pstmt = connection.prepareStatement(sql3);
				pstmt.setInt(1, labelPair);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					lblMedcine lblmeicine = new lblMedcine();
					lblmeicine.setId(rs.getInt(1));
					lblmeicine.setMecineId(rs.getInt(2));
					lblmeicine.setLabelId(rs.getInt(3));
					lalmedicineList02.add(lblmeicine);
				}
				
				//主标签与副标签下的药品一一匹配
				RedisHelper redisHelper = new RedisHelper();
				for(lblMedcine lblm : lalmedicineList01) {
					for(lblMedcine lblmPair : lalmedicineList02) {
						if(lblm.getMecineId()==lblmPair.getMecineId()) {
							continue;
						}
						List ResultList = (List) redisHelper.getObject(lblm.getMecineId() + "|" + lblmPair.getMecineId(),lblmPair.getMecineId() + "|" + lblm.getMecineId());
						if(null != ResultList) {
							ResultList.addAll(reviewResultsList);
							redisHelper.setObject(lblm.getMecineId() + "|" + lblmPair.getMecineId(),lblmPair.getMecineId() + "|" + lblm.getMecineId(), ResultList);
							redisHelper.setObject(lblmPair.getMecineId() + "|" + lblm.getMecineId(), lblm.getMecineId() + "|" + lblmPair.getMecineId(), ResultList);
						}else {
							redisHelper.setObject(lblm.getMecineId() + "|" + lblmPair.getMecineId(),lblmPair.getMecineId() + "|" + lblm.getMecineId(),reviewResultsList);
							redisHelper.setObject(lblmPair.getMecineId() + "|" + lblm.getMecineId(), lblm.getMecineId() + "|" + lblmPair.getMecineId(), reviewResultsList);
						}
					
					
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
