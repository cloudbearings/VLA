package csnap_vla;

public class SaxParserImplementation {  
  public static void main(String args[]){  
   
   
   FindNodes nodeFind = new FindNodes();
   FindRules ruleFind = new FindRules();
   String[][] nodeString2 = nodeFind.getNodes("How to measure voltage?");
   
   int next = 0;
   String nodeString[] = new String[11];
   for(int i = 0; i < nodeString2.length;i=i+1){
	   for(int j = 0; j< nodeString2[i].length; j = j+1){
		   if (nodeString2[i][j] != null){
			   nodeString[next] = nodeString2[i][j];
			   next = next + 1;
			   //System.out.println("keyword: " + nodeString2[i][j]);
		   }
		   if (nodeString2[i][j] == null){
			   nodeString2[i][j] = "no" + (j+1);
		   }
	   }
   }
   String printString[] = ruleFind.getRules(nodeString2);
   System.out.println(printString[0]);
   System.out.println(printString[1]);
   System.out.println(printString[2]);

   //System.out.println(printString[1]);
   //System.out.println(ruleFind.getRules(nodeString2));
  }
}
