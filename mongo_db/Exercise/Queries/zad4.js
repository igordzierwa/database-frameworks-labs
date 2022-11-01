var modifyUser = (userID, userName) => {
    db.user.updateOne(
        {"user_id": userID},
        {$set: {"name": userName}}
    )    
};

modifyUser("5Xh4Qc3rxhAQ_NcNtxLssQ", "igordzie");

db.user.find({name: "igordzie"})