package jdbctest;import java.util.*;import java.sql.*;/** * 此类由 WAFDAOBuilder2.2 生成 类描述:获得荣誉 数据访问对象 生成时间:2013/10/23 04:09:48 * 版权所有:太极计算机股份有限公司 */public class HonourDAO {	/**	 * HonourDAO 惟一实例	 */	private static HonourDAO instance;	/**	 * 获取HonourDAO的惟一实例	 * 	 * @return instance	 */	public static HonourDAO getInstance() {		if (instance == null) {			instance = new HonourDAO();		}		return instance;	}	public  List findHonourEntity(String sql,Connection conn) throws SQLException {		PreparedStatement pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);		ResultSet rs = pstmt.executeQuery();		ArrayList list = new ArrayList();		String honournameString=null;		while (rs.next()) {			honournameString=rs.getString("honourname");			list.add(honournameString);		}		rs.close();		pstmt.close();		return list;	}}//1234312