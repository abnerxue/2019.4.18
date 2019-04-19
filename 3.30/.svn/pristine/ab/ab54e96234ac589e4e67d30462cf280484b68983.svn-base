package ck.preprocessorData;

import java.sql.SQLException;
import java.sql.Statement;
import ck.ServerObj;

/**
 * <h3>数据预处理类</h3>
 * 
 * @author LIUJUNWU
 * @version 1.0.0
 */
public class PreprocessorServer {
	public static void main(String[] args) {
		try {
			//获取SQLServer连接
			Statement st = new ServerObj().getConnection().createStatement();
			//预处理药品对药品中的规则
			RuleCompatibilityInterplay.RuleCompatibilityInterplay(st);
			//预处理标签对类别中的规则
			RuleCategoryLbl.RuleCategoryLbl(st);
			//预处理药品对标签
			RuleCompatibilityInterplayMedcineLbl.ruleCompatibilityInterplayMedcineLbl();
			//预处理标签对标签
			RuleCompatibilityLblLbl.ruleCompatibilityInterplayMedcineLbl();
			//关闭SQLServer连接
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
