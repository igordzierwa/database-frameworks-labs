var findByCategory = (category) => {
    return db.business.find({categories: category})
}

findByCategory("Television Stations")

