import java.text.DecimalFormat;

public class a {
	public static String[] getTextList(String args) {
		String str1="问哦，数据否为。水电费金额，访问偶然间。";
		String[] list1=str1.split("。");
		String[] all=new String[50];
		int k=0;
		for (int i = 0; i < list1.length; i++) {
			String[] ss=list1[i].split("，");
			for (int j = 0; j < ss.length; j++) {
				all[k]=ss[j];
				k++;
			}
		}
		String[] total=new String[k];
		for (int i = 0;  all[i]!=null; i++) {
			total[i]=all[i];
			System.out.println(all[i]);
		}
		System.out.println(total.length);
		for (int i = 0; i<k; i++) {
			System.out.println(total[i]);
		}
		return total;
	}
	public static void main(String[] args) {
		getTextList("");
	}
}
