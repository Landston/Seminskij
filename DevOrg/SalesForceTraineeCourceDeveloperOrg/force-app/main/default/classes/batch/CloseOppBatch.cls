public class CloseOppBatch implements Database.Batchable<sObject> {

    public String query;

    public CloseOppBatch(String query) {
      this.query = query;

    }

    public Database.QueryLocator start(Database.BatchableContext context ){
        System.debug(LoggingLevel.DEBUG, 'start CloseOppBatch');
        return Database.getQueryLocator(this.query);
    }

    public void execute(Database.BatchableContext context, List<Opportunity> opps){
        System.debug('execute CloseOppBatch');

        for(Opportunity oppItem : opps){
            oppItem.StageName = 'Closed Lost';
            oppItem.CloseDate = Date.today();
                   
            System.debug('execute CloseOppBatch');            
        }
        
        update opps;
    }

    
    public void finish(Database.BatchableContext context){
        
    }
}