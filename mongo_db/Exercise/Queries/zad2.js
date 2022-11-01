var addReview = (user_id, review_id, business_id, stars, text) => {
    db.review.insertOne({
        "votes": {
            "funny": 0,
            "useful": 0,
            "cool": 0,
        },
        "user_id": user_id,
        "review_id": review_id,
        "stars": stars,
        "date": Date(),
        "text": text,
        "type": "review",
        "business_id": business_id
    })
};
addReview("123456", "1234567890", "vcNAWiLM4dR7D2nwwJ7nCA", 5, "test");
db.review.find({review_id: "1234567890"})
