public  abstract class TriggerHandler {

    public TriggerHandler() {
            }

    public  void Run(System.TriggerOperation triggerEvent){
        switch on triggerEvent {
        
            when BEFORE_INSERT {
                beforeInsert();
        
            }
            when AFTER_INSERT {
                afterInsert();
            }
            when BEFORE_UPDATE {
               beforeUpdate();
            }
            when AFTER_UPDATE {
                afterUpdate();
            }
            when BEFORE_DELETE {
                beforeUpdate();
            }
            when AFTER_DELETE {
               afterDelete();
            }
            when AFTER_UNDELETE {
                afterUndelete();
            }
        }

    }

    protected abstract void beforeInsert();

    protected abstract void beforeUpdate();
    
    protected abstract void afterInsert();
    
    protected abstract void afterUpdate();

    protected abstract void beforeDelete();

    protected abstract void afterDelete();

    protected abstract void afterUndelete();
    
}