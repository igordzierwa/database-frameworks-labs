//a
db.business.distinct("city").sort()

//b
db.review.find({date: {$gte:'2011-01-01'}}).count()

//c
db.business.find({open: false}, {name: 1, full_address: 1, stars: 1})  

//d
db.user.find({$or: [{"votes.funny": 0}, {"votes.useful": 0}]}).sort({name: 1})

//e
db.business.aggregate([
    {$match: {date: {$gte: '2012-01-01', $lte: '2012-12-31'}}},
    {$group: {_id: "$business_id", count:{$sum: 1}}},
    {$sort: {count: 1}}
])

//f
db.review.aggregate([
    {$group: {_id: "$business_id", averageStars: {$avg: "$stars"}}},
    {$match: {averageStars: {$gte: 4}}}
])

//g
db.business.deleteMany({stars: 2.0})