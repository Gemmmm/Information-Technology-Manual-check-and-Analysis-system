package textcluster;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.UserDaoImpl;
import model.User;

public  class Program
    {
	public static void main(String[] args) {
		try {
			Program.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       public static void run() throws IOException
        {
            //1銆佽幏鍙栨枃妗ｈ緭鍏�
          // String[] docs = getInputDocs("C:\\Users\\DELL\\Desktop\\fenleijuleitest\\src\\input2.txt");
    	String[] docs = getdbDocs();
            
            if (docs.length < 1)
            {
                System.out.println("娌℃湁鏂囨。杈撳叆");
                System.in.read();
              //  System.exit(0);
                return;
            }
            /*else{
            	for(String s:docs){
            		System.out.println(s);
            	}
            }*/

            //2銆佸垵濮嬪寲TFIDF娴嬮噺鍣紝鐢ㄦ潵鐢熶骇姣忎釜鏂囨。鐨凾FIDF鏉冮噸
            TFIDFMeasure tf = new TFIDFMeasure(docs, new Tokeniser());
            System.out.println(tf.get_numTerms());
          

            int K = 10; //鑱氭垚3涓仛绫�

            //3銆佺敓鎴恔-means鐨勮緭鍏ユ暟鎹紝鏄竴涓仈鍚堟暟缁勶紝绗竴缁磋〃绀烘枃妗ｄ釜鏁帮紝
            //绗簩缁磋〃绀烘墍鏈夋枃妗ｅ垎鍑烘潵鐨勬墍鏈夎瘝
            double[][] data = new double[docs.length][];
            int docCount = docs.length; //鏂囨。涓暟
            int dimension = tf.get_numTerms();//鎵�鏈夎瘝鐨勬暟鐩�
            for (int i = 0; i < docCount; i++)
            {
                for (int j = 0; j < dimension; j++)
                {
                    data[i] = tf.GetTermVector2(i); //鑾峰彇绗琲涓枃妗ｇ殑TFIDF鏉冮噸鍚戦噺
                }
            }

            //4銆佸垵濮嬪寲k-means绠楁硶锛岀涓�涓弬鏁拌〃绀鸿緭鍏ユ暟鎹紝绗簩涓弬鏁拌〃绀鸿鑱氭垚鍑犱釜绫�
            WawaKMeans kmeans = new WawaKMeans(data, K);
            //5銆佸紑濮嬭凯浠�
            kmeans.Start();

            //6銆佽幏鍙栬仛绫荤粨鏋滃苟杈撳嚭
            WawaCluster[] clusters = kmeans.getClusters();
            for(WawaCluster cluster : clusters){

                List<Integer> members = cluster.CurrentMembership;
                System.out.println("-----------------");
                for (int i : members)
                {
                	System.out.println(docs[i]);
                }

            
            }
            /*foreach (WawaCluster cluster in clusters)
            {
                List<int> members = cluster.CurrentMembership;
                Console.WriteLine("-----------------");
                foreach (int i in members)
                {
                    Console.WriteLine(docs[i]);
                }

            }*/
           // System.in.read();
           // Console.Read();
        }

        /// <summary>
        /// 鑾峰彇鏂囨。杈撳叆
        /// </summary>
        /// <returns></returns>
        private static String[] getInputDocs(String file)
        {
            List<String> ret = new ArrayList<String>();
            
            try
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                {
                    String temp;
                    while ((temp = br.readLine()) != null)
                    {
                        ret.add(temp);
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            String[] fileString=new String[ret.size()];
            return (String[]) ret.toArray(fileString);
        }
        private static String[] getdbDocs()
        {
        	List<String> ret = new ArrayList<String>();
        	UserDaoImpl userDaoImpl=new UserDaoImpl();
    		List<User> lists=new ArrayList<User>();
    		lists=userDaoImpl.load();
    		for (User user :lists)
    		{
    			ret.add(user.getId()+" "+user.getKeyword());
    		} 
    		
    	    String[] fileString=new String[ret.size()];
            return (String[]) ret.toArray(fileString);
        	
        }
    }
