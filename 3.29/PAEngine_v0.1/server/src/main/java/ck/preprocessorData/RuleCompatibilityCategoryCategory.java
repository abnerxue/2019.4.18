package ck.preprocessorData;

import ck.po.Category;
import ck.po.CompatibilityCategoryCategory;
import ck.po.Medcine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>预处理类别与类别中的规则</h3>
 * <h3>配伍管理和相互作用，重复用药(类别与类别)</h3>
 * @version 1.0.0
 */
public class RuleCompatibilityCategoryCategory {

    /**
     * 根绝类别与类别的关系，把药品与药品的关系放入缓存中
     * @param st
     */
    public static void run(Statement st){

        //查询类别与类别表中的所有数据
        List<CompatibilityCategoryCategory> compatibilityCategoryCategories = selectCompatibilityCategoryCategories(st);
        //遍历类别关系列表
        for(CompatibilityCategoryCategory ccc : compatibilityCategoryCategories){
            //递归查询类别之下的类别
            List<Integer> categoryIdList = new ArrayList<>();
            recursionCategory(st,ccc.getCategoryId(),categoryIdList);
            //递归查询对比类别之下的类别
            List<Integer> categoryPairIdList = new ArrayList<>();
            recursionCategory(st,ccc.getCategoryPairId(),categoryPairIdList);
            //查询类别下的药品
            List<Medcine> medicineList = getMedicineByCategoryList(st,categoryIdList);
            //查询对比类别下的商品
            List<Medcine> medicinePairList = getMedicineByCategoryList(st,categoryPairIdList);
            //加入缓存中
            for(Medcine medicine : medicineList){
                for (Medcine medicinePair : medicinePairList){
                    ReviewResults reviewResults = new ReviewResults();
                    reviewResults.setReactionDescription(ccc.getReactionDescription());
                    reviewResults.setAuditLevelId(ccc.getAuditLevelId());
                    reviewResults.setType(ccc.getType());
                    insertMedicineRule(medicine.getId(),medicinePair.getId(),reviewResults);
                }
            }
        }
    }

    /**
     * 药品关系放入缓存中
     * @param medicineId
     * @param medicinePairId
     * @param reviewResults
     */
    public static void insertMedicineRule(int medicineId, int medicinePairId, ReviewResults reviewResults) {
        List<ReviewResults> reviewResultsList = new ArrayList<>();
        reviewResultsList.add(reviewResults);
        //查询redis中是否已存在这两个药品的相关数据
        List ResultList = (List) RedisHelper.getObject(medicinePairId + "|" + medicineId, medicinePairId + "|" + medicineId);
        if(ResultList != null){//保留之前的数据并把这个规则的结果数据存上去
            ResultList.addAll(reviewResultsList);
            RedisHelper.setObject(medicinePairId + "|" + medicineId, medicinePairId + "|" + medicineId, ResultList);
            RedisHelper.setObject(medicineId + "|" + medicinePairId, medicineId + "|" + medicinePairId, ResultList);
        }else{//把这个规则的结果数据存上去
            RedisHelper.setObject(medicinePairId + "|" + medicineId, medicinePairId + "|" + medicineId, reviewResultsList);
            RedisHelper.setObject(medicineId + "|" + medicinePairId, medicineId + "|" + medicinePairId, reviewResultsList);
        }
    }

    /**
     * 查询类别下的药品
     * @param categoryIdList
     * @return
     */
    public static List<Medcine> getMedicineByCategoryList(Statement st,List<Integer> categoryIdList) {
        ResultSet rs;
        List<Medcine> medicineList = new ArrayList<>();
        try {
            //查询类别与类别表中的所有数据
            String sql = "SELECT * FROM t_medcine where category in ( -1";
            for (Integer categoryId : categoryIdList){
                sql += "," + categoryId;
            }
            sql += ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Medcine medicine = new Medcine();
                medicine.setId(rs.getInt(1));
                medicineList.add(medicine);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return medicineList;
    }

    /**
     * 查询所有类别与类别关系
     * @param st
     * @return
     */
    public static List<CompatibilityCategoryCategory> selectCompatibilityCategoryCategories(Statement st){
        ResultSet rs;
        List<CompatibilityCategoryCategory> compatibilityCategoryCategories = new ArrayList<>();
        try {
            //查询类别与类别表中的所有数据
            rs = st.executeQuery("SELECT * FROM t_compatibility_category_category");
            while (rs.next()) {
                CompatibilityCategoryCategory compatibilityCategoryCategory = new CompatibilityCategoryCategory();
                compatibilityCategoryCategory.setCategoryId(rs.getInt(2));
                compatibilityCategoryCategory.setCategoryPairId(rs.getInt(3));
                compatibilityCategoryCategory.setReactionDescription(rs.getString(4));
                compatibilityCategoryCategory.setAuditLevelId(rs.getInt(5));
                compatibilityCategoryCategory.setType(rs.getInt(6));
                compatibilityCategoryCategories.add(compatibilityCategoryCategory);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return compatibilityCategoryCategories;
    }

    /**
     * 递归查询当前分类下的所有分类ID
     * @param st
     * @param categoryId
     * @param categoryIdList
     */
    public static void recursionCategory(Statement st,int categoryId, List<Integer> categoryIdList) {
        categoryIdList.add(categoryId);
        ResultSet rs;
        try {
            //查询当前类别之下类别
            rs = st.executeQuery("SELECT * FROM t_category where pid = " + categoryId);
            List<Category> categoryArrayList = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                categoryArrayList.add(category);
            }
            for (Category category : categoryArrayList){
                recursionCategory(st,category.getId(),categoryIdList);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
