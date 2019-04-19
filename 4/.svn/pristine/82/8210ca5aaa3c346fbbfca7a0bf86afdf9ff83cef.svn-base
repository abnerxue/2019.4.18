package ck.preprocessorData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ck.po.CategoryLbl;

/**
 * <h3>预处理标签与类别中的规则</h3>
 * <h3>配伍管理和相互作用，重复用药(标签与类别)</h3>
 * @author LIUJUNWU
 * @version 1.0.0
 */
public class RuleCategoryLbl {
	
	public static void RuleCategoryLbl(Statement st) {
		ResultSet rs = null;
		try {
			//查询标签与类别中的规则
			rs = st.executeQuery("SELECT id, category_id, lbl_id, reaction_description, audit_level_id, type FROM t_category_lbl");
			List<CategoryLbl> CategoryLblList = new ArrayList<>();
			while (rs.next())
			{
				CategoryLbl categoryLbl = new CategoryLbl();
				categoryLbl.setId(rs.getInt(1));
				categoryLbl.setCategoryId(rs.getInt(2));
				categoryLbl.setLblId(rs.getInt(3));
				categoryLbl.setReactionDescription(rs.getString(4));
				categoryLbl.setAuditLevelId(rs.getInt(5));
				categoryLbl.setType(rs.getInt(6));
				CategoryLblList.add(categoryLbl);
			}
			RedisHelper redisHelper = new RedisHelper();
			for(CategoryLbl cl : CategoryLblList) {
				//根据标签ID查询出对应的药品ID
				rs = st.executeQuery("SELECT medcine_id FROM t_lbl_medcine WHERE label_id="  + cl.getLblId());
				List<Integer> medcineIds = new ArrayList<>();
				while (rs.next())
				{
					medcineIds.add(rs.getInt(1)); 
				}
				//根据类别ID递归查询出下面所有的类别ID
				rs = st.executeQuery("WITH dept AS ( SELECT id FROM t_category WHERE pid =" + cl.getCategoryId() + "UNION ALL SELECT d.id FROM t_category d INNER JOIN dept ON d.pid = dept.id) SELECT * FROM dept");
				List<Integer> categoryIds = new ArrayList<>();
				while (rs.next())
				{
					categoryIds.add(rs.getInt(1));
				}
				//根据类别ID查询出对应的药品ID
				for(Integer categoryId : categoryIds) {
					rs = st.executeQuery("SELECT id FROM t_medcine WHERE category = " + categoryId);
				}
				List<Integer> medcineIdForCategorys = new ArrayList<>();
				while (rs.next())
				{
					medcineIdForCategorys.add(rs.getInt(1)); 
				}
				//标签和类对应的药品互相一对一组合
				for(Integer medcineId : medcineIds) {
					for(Integer medcineIdForCategory : medcineIdForCategorys) {
					    List<ReviewResults> reviewResultsList = new ArrayList<>();
						ReviewResults reviewResults = new ReviewResults();
						reviewResults.setReactionDescription(cl.getReactionDescription());
						reviewResults.setAuditLevelId(cl.getAuditLevelId());
						reviewResults.setType(cl.getType());
						reviewResultsList.add(reviewResults);
						//查询redis中是否已存在这两个药品的相关数据
						List ResultList = (List) redisHelper.getObject(medcineId + "|" + medcineIdForCategory, medcineId + "|" + medcineIdForCategory);
						if(ResultList != null){//保留之前的数据并把这个规则的结果数据存上去
							ResultList.addAll(reviewResultsList);
							redisHelper.setObject(medcineId + "|" + medcineIdForCategory, medcineId + "|" + medcineIdForCategory, ResultList);
							redisHelper.setObject(medcineIdForCategory + "|" + medcineId, medcineIdForCategory + "|" + medcineId, ResultList);
						}else{//把这个规则的结果数据存上去
							redisHelper.setObject(medcineId + "|" + medcineIdForCategory, medcineId + "|" + medcineIdForCategory, reviewResultsList);
							redisHelper.setObject(medcineIdForCategory + "|" + medcineId, medcineIdForCategory + "|" + medcineId, reviewResultsList);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
//	public static void main(String[] args) {
//		try {
//			//获取SQLServer连接
//			Statement st = new ServerObj().getConnection().createStatement();
//			//预处理药品对药品中的规则
//			RuleCategoryLbl(st);
//			//关闭SQLServer连接
//			st.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//	}
}
