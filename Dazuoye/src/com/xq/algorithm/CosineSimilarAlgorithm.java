package com.xq.algorithm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>Title:</p>
 * <p>Description: 浣欏鸡鑾峰彇鏂囩珷鐩镐技鎬�
 * </p>
 * @createDate锛�2013-8-26
 * @author xq
 * @version 1.0
 */
public class CosineSimilarAlgorithm {

	/**
	 * 
	* @Title: cosSimilarityByFile
	* @Description: 鑾峰彇涓や釜鏂囦欢鐩镐技鎬�
	* @param @param firstFile
	* @param @param secondFile
	* @param @return    
	* @return Double   
	* @throws
	 */
	public static Double cosSimilarityByFile(String firstFile,String secondFile){
		try{
			Map<String, Map<String, Integer>> firstTfMap=TfIdfAlgorithm.wordSegCount(firstFile);
			Map<String, Map<String, Integer>> secondTfMap=TfIdfAlgorithm.wordSegCount(secondFile);
			if(firstTfMap==null || firstTfMap.size()==0){
				throw new IllegalArgumentException("firstFile not found or firstFile is empty! ");
			}
			if(secondTfMap==null || secondTfMap.size()==0){
				throw new IllegalArgumentException("secondFile not found or secondFile is empty! ");
			}
			Map<String,Integer> firstWords=firstTfMap.get(firstFile);
			Map<String,Integer> secondWords=secondTfMap.get(secondFile);
			if(firstWords.size()<secondWords.size()){
				Map<String, Integer> temp=firstWords;
				firstWords=secondWords;
				secondWords=temp;
			}
			return calculateCos((LinkedHashMap<String, Integer>)firstWords, (LinkedHashMap<String, Integer>)secondWords);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}
	
	/**
	 * 
	* @Title: cosSimilarityByString
	* @Description: 寰楀埌涓や釜瀛楃涓茬殑鐩镐技鎬�
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	public static Double cosSimilarityByString(String first,String second){
		try{
			Map<String, Integer> firstTfMap=TfIdfAlgorithm.segStr(first);
			Map<String, Integer> secondTfMap=TfIdfAlgorithm.segStr(second);
			if(firstTfMap.size()<secondTfMap.size()){
				Map<String, Integer> temp=firstTfMap;
				firstTfMap=secondTfMap;
				secondTfMap=temp;
			}
			return calculateCos((LinkedHashMap<String, Integer>)firstTfMap, (LinkedHashMap<String, Integer>)secondTfMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}

	/**
	 * 
	* @Title: calculateCos
	* @Description: 璁＄畻浣欏鸡鐩镐技鎬�
	* @param @param first
	* @param @param second
	* @param @return    
	* @return Double   
	* @throws
	 */
	private static Double calculateCos(LinkedHashMap<String, Integer> first,LinkedHashMap<String, Integer> second){
		
		List<Map.Entry<String, Integer>> firstList = new ArrayList<Map.Entry<String, Integer>>(first.entrySet());
		List<Map.Entry<String, Integer>> secondList = new ArrayList<Map.Entry<String, Integer>>(second.entrySet());
		//璁＄畻鐩镐技搴�  
        double vectorFirstModulo = 0.00;//鍚戦噺1鐨勬ā  
        double vectorSecondModulo = 0.00;//鍚戦噺2鐨勬ā  
        double vectorProduct = 0.00; //鍚戦噺绉�  
        int secondSize=second.size();
		for(int i=0;i<firstList.size();i++){
			if(i<secondSize){
				vectorSecondModulo+=secondList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
				vectorProduct+=firstList.get(i).getValue().doubleValue()*secondList.get(i).getValue().doubleValue();
			}
			vectorFirstModulo+=firstList.get(i).getValue().doubleValue()*firstList.get(i).getValue().doubleValue();
		}
	   return vectorProduct/(Math.sqrt(vectorFirstModulo)*Math.sqrt(vectorSecondModulo));
	}
	
	public static void main(String[] args){
		String str1="他他他";
		String str2="我是你";
		
		
		
		Double result=cosSimilarityByString(str1,
				str2);
		System.out.println("相似度："+result);
	}
}
