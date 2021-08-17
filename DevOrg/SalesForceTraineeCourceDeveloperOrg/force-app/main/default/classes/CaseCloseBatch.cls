public class CaseCloseBatch implements Database.Batchable<sObject> {

    public String query;

    public CaseCloseBatch(String query) {
        this.query = query;
    }

    public Database.QueryLocator start(Database.BatchableContext context ){
             System.debug('start CaseCloseBatch');
        return Database.getQueryLocator(query);
    }

    public void execute(Database.BatchableContext context, List<Opportunity> opps){
           System.debug('execute CaseCloseBatch');

        for(Opportunity oppItem : opps){
            if(oppItem.CloseDate.month() == Date.today().month()){
                if(oppItem.StageName !=	'Closed Won' || oppItem.StageName != 'Closed Lost'){
                    oppItem.StageName = 'Closed Lost';
                    oppItem.CloseDate = Date.today();
                    
                   
                    System.debug('execute CaseCloseBatch');
                }
            }
        }
        
        update opps;
    }

    
    public void finish(Database.BatchableContext context){
        
    }
}