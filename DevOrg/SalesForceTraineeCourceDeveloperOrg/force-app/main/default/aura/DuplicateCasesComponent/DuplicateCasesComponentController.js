({
    myAction : function(component, event, helper) {
        const action = component.get('c.getDuplicateCases');
        const id = component.get('v.recordId');

        action.setParams({
            caseId: id
        });
        
        action.setCallback(this, function(response){
            var returnValue = response.getReturnValue();
   
            component.set('v.duplicateCases', returnValue);
        }); 

        $A.enqueueAction(action);
    },
    
    doInit: function(component, event, helper){
    }
})
