({
    mergeCases : function(component, event) {
        const action = component.get('c.mergeCasesById');

        let parentId = component.get('v.parentCaseId');
        let childId = component.get('v.duplicateCaseId');
        
        action.setParams({
            parentCaseId : parentId,
            childCaseId : childId
        })
        action.setCallback(this, function(response){
            window.location.reload();
        });

        $A.enqueueAction(action);
    },
    
    notifyCaseOwner : function(component) {
        const action = component.get('c.notifyCaseOwner');

        let duplicateCaseId = component.get('v.duplicateCaseId');

        action.setParams({
            caseId : duplicateCaseId
        })
        
        $A.enqueueAction(action);
    }

})
