package com.example.ply.wdplycollect.activity.search;

import java.util.List;

/**
 * Created by ljzyuhenda on 16/7/23.
 */

/**
 * currentPage	int	搜索当前页数
 * total	long	搜索到的总记录数
 * id	int	试题id
 * fragment	string	高亮字段
 * from	string	试题来源
 * type	int	试题类型
 */
public class ExerciseBean {
    public String code;
    public ExerciseData data;

    public class ExerciseData {
        public String currentPage;
        public String total;
        public List<ExerciseInfoBean> results;
    }

    public class ExerciseInfoBean {
        public int id;
        public String fragment;
        public String from;
        public String type;
    }
}
