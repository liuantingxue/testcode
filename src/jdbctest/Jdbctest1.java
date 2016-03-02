package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Jdbctest1 {
	
    /**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    public static void main(String[] args){
    	Connection con = null;// 创建一个数据库连接
        PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result1=null;// 创建一个结果集对象
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
        String user = "cxcjsbpt";// 用户名,系统默认的账户名
        String password = "cxcjsbpt";// 你安装时选设置的密码
        con = DriverManager.getConnection(url, user, password);// 获取连接
        
        List list1=HonourDAO.getInstance().findHonourEntity("select * from honour where declareid=1928",con);
       List list2=HonourDAO.getInstance().findHonourEntity("select * from honour where declareid=3375",con);
/*        for(int i = 0;i < list1.size(); i ++){
        	System.out.println(list1.get(i));
        	}
        for(int i = 0;i < list2.size(); i ++){
        	System.out.println(list2.get(i));
        }*/

      List diff=  getDiffrent6(list2,list1);
        for(int i = 0;i < diff.size(); i ++){
        	System.out.println(diff.get(i));
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
            // 注意关闭的顺序，最后使用的最先关闭
            if (result1 != null)
                result1.close();
            if (pre != null)
                pre.close();
            if (con != null)
                con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    }
    
    
    
    
    private static List<String> getDiffrent6(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
         List<String> diff = new ArrayList<String>();
         List<String> maxList = list1;
         List<String> minList = list2;
         if(list2.size()>list1.size())
         {
             maxList = list2;
             minList = list1;
         }
         Map<String,Integer> map = new HashMap<String,Integer>(maxList.size());
         for (String string : list1) {
             map.put(string, 1);
         }
         for (String string : minList) {
             if(map.get(string)!=null){
                 map.put(string, 2);//因为map中key/value是唯一的键值对，重复的string的value会被更新成2
                 continue;
             }
            // diff.add(string);
         }

         
         for(Map.Entry<String, Integer> entry:map.entrySet())
         {
             if(entry.getValue()==1)//相同的都是2了，1都是没有被覆盖的，也就是不相同的
             {
                 diff.add(entry.getKey());
             }
         }
        
  
         
         
        return diff;
        
    }

    
    
    
    
    
    
    
}
