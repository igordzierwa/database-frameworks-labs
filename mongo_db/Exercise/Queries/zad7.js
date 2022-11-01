//addClientFunction
var addClient = (imie, nazwisko, pesel) => {
    db.Klient.insertOne({
        "imie": imie,
        "nazwisko": nazwisko,
        "pesel": pesel,
        "zakupy": []        
    })
}

addClient("Jan", "Kowalski", "97021002999");
addClient("Robert", "Nowak", "99023001222");

//addShoppingFunction
var addShopping = (nazwaSklepu, adresSKlepu, wartoscZakupow) => {
    db.Zakupy.insertOne({
        "date": new Date(),
        "nazwaSklepu": nazwaSklepu,
        "adresSklepu": adresSKlepu,
        "wartoscZakupow": wartoscZakupow,
        "przedmioty": []
    })
}

addShopping("Zabka", "Dobrego Pasterza 103", 100);
addShopping("Lidl", "Czarnowiejska 90", 50);

//addProductFunction

//addProductToShopping


//addShoppingToClient
var addShoppingToClient = (clientID, shoppingID) => {
    db.Klient.update(
        {_id: ObjectId(clientID)},
        {$push: {zakupy: new ObjectId(shoppingID)}}
    )
};

addShoppingToClient("5fc03650be1dc57df7ff7ded", "5fc039fdbe1dc57df7ff7def");
addShoppingToClient("5fc03650be1dc57df7ff7dee", "5fc039fdbe1dc57df7ff7df0");

