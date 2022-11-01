var mapf = function() {
    emit(this.business_id, 1);
};

var reducef = function(key, value) {
    return value.length;      
};

db.tip.mapReduce(
    mapf,
    reducef,
    {out: "tip_count"}
)

db.getCollection("tip_count").find({}).sort({value: -1})