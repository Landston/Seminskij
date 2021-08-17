({
    closeWindow : function(component, event, helper) {
        helper.closeWindow(component);
    },
    init : function(component, event, helper){
        window.addEventListener("keydown", function (event) {
            if (event.key === "Escape") {
                helper.closeWindow(component);
            } 
    },true)},

    mergeCase: function(component, event, helper) {
        helper.closeWindow(component);
        helper.mergeCase(component, event);    
    }
}
)
