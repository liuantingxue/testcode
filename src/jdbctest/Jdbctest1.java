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
     * ��ȡ����List�Ĳ�ͬԪ��
     * @param list1
     * @param list2
     * @return
     */
    public static void main(String[] args){
    	Connection con = null;// ����һ�����ݿ�����
        PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
        ResultSet result1=null;// ����һ�����������
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
        String user = "cxcjsbpt";// �û���,ϵͳĬ�ϵ��˻���
        String password = "cxcjsbpt";// �㰲װʱѡ���õ�����
        con = DriverManager.getConnection(url, user, password);// ��ȡ����
        
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
            // ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
            // ע��رյ�˳�����ʹ�õ����ȹر�
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
                 map.put(string, 2);//��Ϊmap��key/value��Ψһ�ļ�ֵ�ԣ��ظ���string��value�ᱻ���³�2
                 continue;
             }
            // diff.add(string);
         }

         
         for(Map.Entry<String, Integer> entry:map.entrySet())
         {
             if(entry.getValue()==1)//��ͬ�Ķ���2�ˣ�1����û�б����ǵģ�Ҳ���ǲ���ͬ��
             {
                 diff.add(entry.getKey());
             }
         }
        
  
         
         
        return diff;
        
    }

    
    
    
    
    
    
    
}
