db.students.insert(
    {
        "imie": "Igor",
        "nazwisko": "Dzierwa",
        "obecność": true,
        "ocena": null,
        "aktualna data": Date(),
        "zaliczone przedmioty": [
            "AISD",
            "Technika cyfrowa",
            "Sieci komputerowe"
        ]
    }   
)

db.students.find({})    
    