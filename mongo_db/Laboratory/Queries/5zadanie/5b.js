db.business.aggregate([
    { $match: {"categories": "Restaurants"} },
    { $group: {_id: "$city", count: {$sum: 1} } },
    { $sort: { count : -1} }
 ])
    