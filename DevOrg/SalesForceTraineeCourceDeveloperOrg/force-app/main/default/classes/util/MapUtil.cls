public with sharing class MapUtil {
  
    public static Map<String,String> getValueByKeyMapFromList(String keyName, String valueName,  List<SObject> sObjectItemList){
      try{
        Map<String,String> finalMap = new Map<String,String>();

        if(sObjectItemList.isEmpty()) {
          return finalMap;
        }

        for(SObject item : sObjectItemList) {
            finalMap.put(String.valueOf(item.get(keyName)), String.valueOf(item.get(valueName)));
          }
  
        return finalMap;
      } catch (Exception e){
        return new Map<String,String>();
      }
      }

      public static Map<String, Account> getAccountValueByStringKeyMapFromList(String keyName, List<Account> sObjectItemList){
        try{
          Map<String, Account> finalMap = new Map<String, Account>();

          for(Account item : sObjectItemList){
              finalMap.put(String.valueOf(item.get(keyName)), item);
          }

          return finalMap;

        } catch(Exception e) {
          return new Map<String,Account>();
        }
      }

      public static Map<String, Lead> getLeadValueByStringKeyMapFromList(String keyName, List<Lead> sObjectItemList){
        try{
          Map<String, Lead> finalMap = new Map<String, Lead>();

          for(Lead item : sObjectItemList){
            finalMap.put(String.valueOf(item.get(keyName)), item);
        
          }

          return finalMap;

        } catch(Exception e) {
          return new Map<String,Lead>();
        }
      }
}