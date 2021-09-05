public with sharing class ListUtils {
    public ListUtils() {
    }

    public static Map<Object,Object> getValueByKeyMapFromList(String keyName, String valueName,  List<SObject> sObjectItemList){

      Map<Object,Object> finalMap = new Map<Object,Object>();

      for(SObject item : sObjectItemList) {
          finalMap.put(item.get(keyName), item.get(valueName));
          
        }

      return finalMap;
    }
}