package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Division;
import utils.DBUtil;

public class DivisionValidators {
    public static List<String> validators(Division d, Boolean code_duplicate_flag, Boolean name_duplicate_flag){
        List<String> errors = new ArrayList<String>();

        // 部署コードの入力チェック
        String code_errors = _validateCode(d.getCode(), code_duplicate_flag);
        if(!code_errors.equals("")){
            errors.add(code_errors);
        }

        // 部署名の入力チェック
        String name_errors = _validateName(d.getName(), name_duplicate_flag);
        if(!name_errors.equals("")){
            errors.add(name_errors);
        }

        return errors;

    }


    // 部署コードのチェック
    public static String _validateCode(String code, Boolean code_duplicate_flag){

        // 必須入力
        if(code == null || code.equals("")){
            return "部署コードを入力してください。";
        }

        // 重複チェック
        if(code_duplicate_flag){
            EntityManager em = DBUtil.createEntityManager();

            long division_code_count = (long)em.createNamedQuery("codeDuplicateCheck", Long.class)
                                                    .setParameter("code", code)
                                                    .getSingleResult();
            em.close();


            if(division_code_count > 0){
                return "この部署コードは既に登録されています。";
            }
        }

        return "";

    }

    // 部署名のチェック
    public static String _validateName(String name, Boolean name_duplicate_flag){

        // 必須入力
        if(name == null || name.equals("")){
            return "部署名を入力してください。";
        }

        // 重複チェック
        if(name_duplicate_flag){
            EntityManager em = DBUtil.createEntityManager();

            long division_name_count = (long)em.createNamedQuery("nameDuplicateCheck", Long.class)
                                                    .setParameter("name", name)
                                                    .getSingleResult();

            em.close();

            if(division_name_count > 0){
                return "この部署名は既に登録されています。";
            }

        }

        return "";
    }

}
