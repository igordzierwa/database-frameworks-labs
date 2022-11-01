db.business.aggregate([
    {$match: 
        {$and : [
            {"categories": "Hotels"},
            {"attributes.Wi-Fi": "free"},
            {"stars": {$gte: 4.5}}
          ]
        }
    },
    {$group : {_id: "$state", count: {$sum: 1}}},
 ])