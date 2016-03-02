package test1;  
  
import java.awt.Color;  
  
public class ColorTest {  
    public static void main(String[] args) {  
        System.out.println(toHexFromColor(Color.BLUE));  
        System.out.println(toColorFromString(toHexFromColor(Color.BLUE)));  
    }  
    /** 
     * Color����ת�����ַ��� 
     * @param color Color���� 
     * @return 16������ɫ�ַ��� 
     * */  
    private static String toHexFromColor(Color color){  
        String r,g,b;  
        StringBuilder su = new StringBuilder();  
        r = Integer.toHexString(color.getRed());  
        g = Integer.toHexString(color.getGreen());  
        b = Integer.toHexString(color.getBlue());  
        r = r.length() == 1 ? "0" + r : r;  
        g = g.length() ==1 ? "0" +g : g;  
        b = b.length() == 1 ? "0" + b : b;  
        r = r.toUpperCase();  
        g = g.toUpperCase();  
        b = b.toUpperCase();  
        su.append("0xFF");  
        su.append(r);  
        su.append(g);  
        su.append(b);  
        //0xFF0000FF  
        return su.toString();  
    }  
    /** 
     * �ַ���ת����Color���� 
     * @param colorStr 16������ɫ�ַ��� 
     * @return Color���� 
     * */  
    public static Color toColorFromString(String colorStr){  
        colorStr = colorStr.substring(4);  
        Color color =  new Color(Integer.parseInt(colorStr, 16)) ;  
        //java.awt.Color[r=0,g=0,b=255]  
        return color;  
    }  
}  