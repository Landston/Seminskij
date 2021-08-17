public with sharing class ProductService {
    public ProductService() {

    }

    @AuraEnabled(cacheable = true)
    public static List<Product2> getProducts(){
        try {
            List<Product2> products = new List<Product2>([SELECT ID, Name, Year__c, Price__c
                                                                    FROM Product2  
                                                                    WHERE IsActive = true  ]);
            System.debug(LoggingLevel.INFO, products);
            return products;
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static List<Id> getProductsIdList(){
        try {
            Map<Id, Product2> productById = new Map<Id, Product2>([SELECT ID FROM Product2 WHERE isActive = true]);
            
            return new List<id>(productById.keySet());
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static List<Id> getProductByName(String name){
        try {
            String input = '%' + name + '%';
            Map<Id, Product2> productById = new Map<Id, Product2>([SELECT ID 
                                                                            FROM Product2 
                                                                            WHERE isActive = true 
                                                                            AND Name LIKE : input]);
            System.debug(productById.keySet());
            return new List<id>(productById.keySet());
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static List<id> getProductByPrice(Integer price){
        try {
            Map<Id, Product2> productById = new Map<Id, Product2>([SELECT ID 
                                                                            FROM Product2 
                                                                            WHERE isActive = true 
                                                                            AND Price__c <=: price]);
            System.debug(productById.keySet());
            return new List<id>(productById.keySet());
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static String getContentVersionId(String productId){
        try {
            List<ContentDocumentLink> links = [SELECT  ContentDocumentId 
                                                    FROM ContentDocumentLink   
                                                    WHERE LinkedEntityId =: productId ];
            List<Id> contentDocIds = new List<Id>();

            for(ContentDocumentLink item : links){
                contentDocIds.add(item.ContentDocumentId);
            }
            
            List<ContentVersion> content = [SELECT Id 
                                                    FROM ContentVersion 
                                                    WHERE ContentDocumentId IN: contentDocIds];

            return content.isEmpty() ? null : content[0].Id;

        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }
}