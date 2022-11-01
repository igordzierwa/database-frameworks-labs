//funkcja mapujaca
var map = function() {
    emit("votes", this.votes);
};
//funkcja redukujaca
var reduce = function(voteName, array) {
    var valueFunny = 0;
    var valueCool = 0;
    var valueUseful = 0;
    
    for(var i = 0; i < array.length; i++) {
        if(array[i].funny > 0) 
            valueFunny += 1;
        if(array[i].cool > 0 ) 
            valueCool += 1;
        if(array[i].useful > 0) 
            valueUseful += 1;
    }
    return {funny: valueFunny, cool: valueCool, useful: valueUseful};
 };
 
db.user.mapReduce(
    map,
    reduce,
    {
        out: "votes_summary"
    }    
   )
  
db.votes_summary.find({})